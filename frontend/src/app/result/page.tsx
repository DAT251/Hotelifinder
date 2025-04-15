'use client';

import { Header } from '@/components/header';
import MapComponent from '@/components/map';
import { Suspense, useEffect, useState } from 'react';
import HotelRecommendations from '@/components/hotelRecommendations';

type HotelData = {
    name: string;
    location: {
        latitude: number;
        longitude: number;
    };
    address: {
        postalCode: string;
        streetName: string;
        streetNumber: number;
    };
};

export default function ResultPage() {
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const [selectedVenues, setSelectedVenues] = useState([]);
  const [recommendedHotels, setRecommendedHotels] = useState<HotelData[]>([]);


    useEffect(() => {
    const storedVenues = localStorage.getItem("selectedVenues");
    if (storedVenues) {
      setSelectedVenues(JSON.parse(storedVenues));
    }
  }, []);

  return (
    <div className='flex flex-col h-screen justify-center'>
      <Header />
        <div className='flex flex-row justify-around items-center h-full'>
            {/* left side */}
            <div className='flex flex-col gap-y-8'>
                <HotelRecommendations onHotelsFetched={(hotels) => setRecommendedHotels(hotels)} />
            </div>
            {/* right side */}
            <div>
                <Suspense fallback={<div>Loading map...</div>}>
                    <MapComponent recommendedHotels={recommendedHotels.map(hotel => ({
                        name: hotel.name,
                        lat: hotel.location.latitude,
                        lng: hotel.location.longitude
                    }))} />
                </Suspense>
            </div>
        </div>
        {/* <Link href='/booking'>Booking</Link> */}
    </div>
  );
}
