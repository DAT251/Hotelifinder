/* eslint-disable @typescript-eslint/no-explicit-any */

'use client';
import React, { useState, useEffect } from 'react';
import { APIProvider, Map, Marker, InfoWindow, useMap } from '@vis.gl/react-google-maps';
import { useSearchParams } from 'next/navigation';
import { Venue } from '../schema/venue';
import Image from 'next/image';


const api_key = process.env.NEXT_PUBLIC_API_KEY;
const zoom = 12;
const center = { lat: 59.9139, lng: 10.7522 };

interface MapContentProps {
  selectedVenues: Venue[];
}

const MapContent = ({ selectedVenues  }: MapContentProps) => {
  const [hotels, setHotels] = useState<any[]>([]);
  const [selectedPoint, setSelectedPoint] = useState<{ name: string; lat: number; lng: number } | null>(null);
  const [showInitialMarkers, setShowInitialMarkers] = useState(true);
  const [selectedHotel, setSelectedHotel] = useState<any | null>(null);
  const [geocodedVenues, setGeocodedVenues] = useState<{ name: string; lat: number; lng: number }[]>([]);
  const map = useMap();
  const [directionsRenderers, setDirectionsRenderers] = useState<any[]>([]);
  const [directionsService, setDirectionsService] = useState<any>(null);
  const [transportLabels, setTransportLabels] = useState<any[]>([]);


  useEffect(() => {
    if (!map) return;

    const handleTilesLoaded = () => {
      if (window.google && window.google.maps) {
        setDirectionsService(new window.google.maps.DirectionsService());
      }
    };

    map.addListener('tilesloaded', handleTilesLoaded);

    return () => {
      window.google.maps.event.clearListeners(map, 'tilesloaded');
    };
  }, [map]);


  useEffect(() => {
    const geocodeVenues = async () => {
      const geocoded = await Promise.all(selectedVenues.map(async (venue: Venue) => {
        const address = `${venue.address.streetName} ${venue.address.streetNumber}, ${venue.address.postalCode}`;
        const response = await fetch(`https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${api_key}`);
        const data = await response.json();
        if (data.results && data.results.length > 0) {
          const location = data.results[0].geometry.location;
          return { name: venue.name, lat: location.lat, lng: location.lng };
        }
        return null;
      }));
      setGeocodedVenues(geocoded.filter(venue => venue !== null));
    };

    geocodeVenues();
  }, [selectedVenues]);


  useEffect(() => {
    if (map && showInitialMarkers && geocodedVenues.length > 0) {
      fitMapToBounds(geocodedVenues);
    }
  }, [map, showInitialMarkers, geocodedVenues]);

  const handleMarkerClick = (point: { name: string; lat: number; lng: number }) => {
    setSelectedPoint(point);
    setShowInitialMarkers(false);
    searchHotels(point);
  };

  const searchHotels = (point: { lat: number; lng: number }) => {
    if (!window.google || !window.google.maps || !window.google.maps.places) {
      console.error('Google Maps Places library not loaded');
      return;
    }

    const map = new window.google.maps.Map(document.createElement('div'));
    const service = new window.google.maps.places.PlacesService(map);

    const request = {
      location: new window.google.maps.LatLng(point.lat, point.lng),
      radius: 500,
      type: 'lodging',
    };

    service.nearbySearch(request, (results, status) => {
      if (status === window.google.maps.places.PlacesServiceStatus.OK && results) {
        setHotels(results);
        fitMapToBounds(results)
      } else {
        console.error('Places API error:', status);
      }
    });
  };

  const fitMapToBounds = (locations: any[]) => {
    if (!map || locations.length === 0) return;

    const bounds = new window.google.maps.LatLngBounds();

    locations.forEach((location) => {
      if (location.geometry && location.geometry.location) {
        bounds.extend({
          lat: location.geometry.location.lat(),
          lng: location.geometry.location.lng(),
        });
      } else if (location.lat && location.lng) {
        bounds.extend({
          lat: location.lat,
          lng: location.lng,
        });
      }
    });

    map.fitBounds(bounds);
  };

  const resetMap = () => {
    setSelectedPoint(null);
    setHotels([]);
    setShowInitialMarkers(true);
    setSelectedHotel(null);
    directionsRenderers.forEach(renderer => renderer.setMap(null));
    setDirectionsRenderers([]);
    transportLabels.forEach(label => label.setMap(null));
    setTransportLabels([]);

    if(map){
      map.setCenter(center);
      map.setZoom(zoom)
    }
  };

  const handleHotelClick = (hotel: any) => {
    if (!window.google || !window.google.maps || !window.google.maps.places) {
      console.error('Google Maps Places library not loaded');
      return;
    }

    directionsRenderers.forEach(renderer => renderer.setMap(null));
    setDirectionsRenderers([]);
    transportLabels.forEach(label => label.setMap(null));
    setTransportLabels([]);

    const map = new window.google.maps.Map(document.createElement('div'));
    const service = new window.google.maps.places.PlacesService(map);

    const request = {
      placeId: hotel.place_id,
      fields: [
        'name', 'geometry','rating', 'formatted_address', 'photos', 'website', 'formatted_phone_number', 'reviews'
      ]
    };

    service.getDetails(request, (place, status) => {
      if (status === window.google.maps.places.PlacesServiceStatus.OK && place) {
        setSelectedHotel(place);
      } else {
        console.error('Places API error:', status);
      }
    });
  };

  const showPublicTransportRoute = () => {
    if (!selectedHotel || !window.google || !window.google.maps) {
      console.error('Google Maps library not loaded');
      return;
    }

    directionsRenderers.forEach(renderer => renderer.setMap(null));
    setDirectionsRenderers([]);
    setSelectedHotel(null);

    transportLabels.forEach(label => label.setMap(null));
    setTransportLabels([]);

    const newDirectionsRenderers: any[] = [];
    const newTransportLabels: any[] = [];

    geocodedVenues.forEach((venue) => {
      const request = {
        origin: { lat: selectedHotel.geometry.location.lat(), lng: selectedHotel.geometry.location.lng() },
        destination: { lat: venue.lat, lng: venue.lng },
        travelMode: window.google.maps.TravelMode.TRANSIT,
      };

      directionsService.route(request, (result: any, status: any) => {
        if (status === window.google.maps.DirectionsStatus.OK) {
          const directionsRenderer = new window.google.maps.DirectionsRenderer({
            directions: result,
            map: map,
            panel: document.getElementById("directionsPanel") as HTMLElement,
          });

          const route = result.routes[0];
          const duration = route.legs[0].duration.text;


          const overviewPath = route.overview_path;
          const midpointIndex = Math.floor(overviewPath.length / 2);
          const midpoint = overviewPath[midpointIndex];

          const label = new window.google.maps.InfoWindow({
            content: `<div style="color: black; font-weight: bold;">${duration}</div>`,
            position: midpoint,
          });

          label.open(map);
          newTransportLabels.push(label);

          newDirectionsRenderers.push(directionsRenderer);
          setDirectionsRenderers(newDirectionsRenderers);
          setTransportLabels(newTransportLabels);
        } else {
          console.error('Directions request failed due to ' + status);
        }
      });
    });
  };




  return (
      <>
        <Map
            style={{ width: '60vw', height: '50vh', maxWidth: '800px' }}
            defaultCenter={center}
            defaultZoom={zoom}
            gestureHandling={'greedy'}
            disableDefaultUI={false}
        >

          {showInitialMarkers &&
              geocodedVenues.map((venue, index) => (
                  <Marker
                      key={index}
                      position={{ lat: venue.lat, lng: venue.lng }}
                      onClick={() => handleMarkerClick(venue)}
                  />
              ))}

          {hotels.map((hotel, index) => (
              <Marker
                  key={index}
                  position={{
                    lat: hotel.geometry.location.lat(),
                    lng: hotel.geometry.location.lng(),
                  }}
                  title={hotel.name}
                  icon={{
                    url: "http://localhost:8080/images/img.png",
                    scaledSize: new window.google.maps.Size(50, 50),

                  }}
                  onClick={() => handleHotelClick(hotel)}
              />
          ))}

          {selectedHotel && selectedHotel.geometry && selectedHotel.geometry.location && (
              <InfoWindow
                  position={{
                    lat: selectedHotel.geometry.location.lat(),
                    lng: selectedHotel.geometry.location.lng(),
                  }}
                  onCloseClick={() => setSelectedHotel(null)}
              >
                <div style={{color: "black"}}>
                  <h3 style={{fontWeight: 'bold'}}>{selectedHotel.name}</h3>
                  <p>Rating: {selectedHotel.rating || 'N/A'}</p>
                  <p>Address: {selectedHotel.formatted_address}</p>
                  {selectedHotel.formatted_phone_number && <p>Phone: {selectedHotel.formatted_phone_number}</p>}
                  {selectedHotel.website && (
                      <p>
                        <a href={selectedHotel.website} target="_blank" rel="noopener noreferrer" style={{ textDecoration: 'underline', color: 'blue' }}>
                          Visit Website
                        </a>
                      </p>
                  )}
                  {selectedHotel.photos && selectedHotel.photos.length > 0 && (
                      <Image
                          src={selectedHotel.photos[0].getUrl({ maxWidth: 200 })}
                          alt="Hotel"
                          width={200}
                          height={150}
                          style={{ marginTop: "10px" }}
                      />
                  )}
                  {selectedHotel.reviews && selectedHotel.reviews.length > 0 && (
                      <div>
                        <h4>Recent Reviews:</h4>
                        <p>&quot;{selectedHotel.reviews[0].text}&quot;</p>
                        <p>- {selectedHotel.reviews[0].author_name}</p>
                      </div>
                  )}

                </div>
              </InfoWindow>
          )}
        </Map>
        {selectedPoint && (
            <div>
              <button
                  onClick={resetMap}
                  style={{
                    marginTop: '10px',
                    padding: '10px 20px',
                    fontSize: '16px',
                    cursor: 'pointer',
                  }}
              >
                Reset Map
              </button>

              <button
                  onClick={showPublicTransportRoute}
                  style={{
                    marginTop: '10px',
                    padding: '10px 20px',
                    fontSize: '16px',
                    cursor: 'pointer',
                  }}
              >
                Show Public Transport Route to My Venues
              </button>
            </div>

        )}
      </>
  );
};

export default function MapComponent() {
  const searchParams = useSearchParams();
  const venuesParam = searchParams.get('venues');
  const selectedVenues = venuesParam ? JSON.parse(venuesParam) : [];

  return (
      <div>
        <APIProvider apiKey={api_key as string} libraries={['places']}>
          <MapContent selectedVenues={selectedVenues} />
        </APIProvider>
      </div>
  );
}