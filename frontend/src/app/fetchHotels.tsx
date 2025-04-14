import {Client, TravelMode} from '@googlemaps/google-maps-services-js';
import {writeFileSync} from 'fs';

/*
    Class for fetching hotels with distance information to venues. Results will be published in json file. Dont run excessively.
 */


interface Venue {
    name: string;
    location: {
        latitude: number;
        longitude: number;
    };
    address: {
        streetName: string;
        streetNumber: string;
        postalCode: string;
    };
}

interface Hotel {
    name: string;
    location: {
        lat: number;
        lng: number;
    };
    address: {
        streetNumber: string;
        streetName: string;
        postalCode: string;
    };
    rating?: number;
    distances: Record<string, DistanceInfo>;
}

interface DistanceInfo {
    distance_text: string;
    distance_meters: number;
    duration_text: string;
    duration_seconds: number;
}

const VENUES: Venue[] = [
    {
        name: "Oslo Sentrum",
        location: {
            latitude: 59.9108748,
            longitude: 10.7507367
        },
        address: {
            streetName: "Jernbanetorget",
            streetNumber: "1",
            postalCode: "0154"
        }
    },
    {
        name: "Holmenkollen",
        location: {
            latitude: 59.9647125,
            longitude: 10.6665728
        },
        address: {
            streetName: "Kongeveien",
            streetNumber: "40",
            postalCode: "0747"
        }
    },
    {
        name: "Frogner",
        location: {
            latitude: 59.922676,
            longitude: 10.7055405
        },
        address: {
            streetName: "Frogner Plass",
            streetNumber: "1",
            postalCode: "0266"
        }
    },
    {
        name: "Ullevål",
        location: {
            latitude: 59.9485577,
            longitude: 10.7328228
        },
        address: {
            streetName: "Sognsveien",
            streetNumber: "75",
            postalCode: "0855"
        }
    },
    {
        name: "Tøyenbadet",
        location: {
            latitude: 59.921,
            longitude: 10.776
        },
        address: {
            streetName: "Helgesens gate",
            streetNumber: "90",
            postalCode: "0563"
        }
    },
    {
        name: "Aker Brygge",
        location: {
            latitude: 59.90918,
            longitude: 10.73056
        },
        address: {
            streetName: "Bryggegata",
            streetNumber: "9",
            postalCode: "0250"
        }
    }
];

const API_KEY = String(process.env.NEXT_PUBLIC_API_KEY);
const OSLO_LOCATION = { lat: 59.9139, lng: 10.7522 };
const RADIUS = 10000;
const MIN_RATING = 3;

const client = new Client({});

async function main() {
    try {
        console.log('🚀 Starting hotel search in Oslo...');

        // Step 1: Find hotels with rating > 3
        const hotels = await findHotels();
        console.log(`🏨 Found ${hotels.length} hotels meeting criteria`);

        // Step 2: Calculate distances to all venues
        await calculateDistances(hotels);

        // Step 3: Save results
        saveResults(hotels);

        console.log('✅ Successfully completed all operations');
    } catch (error) {
        console.error('❌ Error in main process:', error);
    }
}

async function findHotels(): Promise<Hotel[]> {
    const hotels: Hotel[] = [];
    let nextPageToken: string | undefined;
    let attemptCount = 0;
    const maxAttempts = 3; // Google returns max 60 results (3 pages × 20)

    do {
        try {
            const response = await client.placesNearby({
                params: {
                    location: OSLO_LOCATION,
                    radius: RADIUS,
                    type: 'lodging',
                    key: API_KEY,
                    ...(nextPageToken ? { pagetoken: nextPageToken } : {})
                }
            });

            console.log(`\n📄 Page ${attemptCount + 1} results:`);
            console.log(response.data.results.map(r => r.name).join(', '));

            // Process current page results
            for (const place of response.data.results) {
                if (place.rating === undefined || place.rating >= MIN_RATING) {
                    const details = await client.placeDetails({
                        params: {
                            place_id: place.place_id!,
                            fields: ['address_component', 'rating'],
                            key: API_KEY
                        }
                    });

                    const address = extractAddress(details.data.result?.address_components || []);

                    hotels.push({
                        name: place.name!,
                        location: place.geometry!.location,
                        address,
                        rating: place.rating,
                        distances: {}
                    });
                }
            }

            nextPageToken = response.data.next_page_token;
            attemptCount++;

            // Critical: Delay before next page request
            if (nextPageToken) {
                console.log('⏳ Waiting 2 seconds for next page...');
                await new Promise(resolve => setTimeout(resolve, 2000));
            }

        } catch (error) {
            console.error(`Error on page ${attemptCount + 1}:`, error);
            break;
        }
    } while (nextPageToken && attemptCount < maxAttempts);

    return hotels;
}

function extractAddress(components: any[]): { streetNumber: string; streetName: string; postalCode: string } {
    const address = {
        streetNumber: '',
        streetName: '',
        postalCode: ''
    };

    for (const component of components) {
        if (component.types.includes('street_number')) {
            address.streetNumber = component.long_name;
        } else if (component.types.includes('route')) {
            address.streetName = component.long_name;
        } else if (component.types.includes('postal_code')) {
            address.postalCode = component.long_name;
        }
    }

    return address;
}

async function calculateDistances(hotels: Hotel[]): Promise<void> {
    try {
        for (const hotel of hotels) {
            console.log(`📊 Calculating distances for ${hotel.name}...`);

            // Process venues in batches of 10 (API limit)
            for (let i = 0; i < VENUES.length; i += 10) {
                const batch = VENUES.slice(i, i + 10);

                const response = await client.distancematrix({
                    params: {
                        origins: [`${hotel.location.lat},${hotel.location.lng}`],
                        destinations: batch.map(v => `${v.location.latitude},${v.location.longitude}`),
                        mode: TravelMode.transit,
                        key: API_KEY
                    }
                });

                // Store distance information
                for (let j = 0; j < batch.length; j++) {
                    const element = response.data.rows[0].elements[j];
                    if (element.status === 'OK') {
                        hotel.distances[batch[j].name] = {
                            distance_text: element.distance.text,
                            distance_meters: element.distance.value,
                            duration_text: element.duration.text,
                            duration_seconds: element.duration.value
                        };
                    }
                }

                // Add delay to avoid rate limiting
                await new Promise(resolve => setTimeout(resolve, 1000));
            }
        }
    } catch (error) {
        console.error('Error calculating distances:', error);
        throw error;
    }
}

function saveResults(hotels: Hotel[]) {
    const result = {
        metadata: {
            generated_at: new Date().toISOString(),
            hotel_count: hotels.length,
            venue_count: VENUES.length
        },
        hotels: hotels.map(hotel => ({
            ...hotel,
            // Convert distances to array for easier processing
            distances: Object.entries(hotel.distances).map(([venueName, distance]) => ({
                venue: venueName,
                ...distance
            }))
        }))
    };

    writeFileSync('oslo_hotels_with_distances.json', JSON.stringify(result, null, 2));
    console.log('💾 Results saved to oslo_hotels_with_distances.json');
}

main().catch(console.error);