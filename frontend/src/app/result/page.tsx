'use client';
import Link from 'next/link';
import { Header } from '@/components/header';
import Hotel from '@/components/hotel';
import MapComponent from '@/components/map';
import { Suspense, useEffect, useState } from 'react';
import HotelRecommendations from '@/components/hotelRecommendations';


export default function ResultPage() {
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  const [selectedVenues, setSelectedVenues] = useState([]);
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
                <HotelRecommendations/>
            </div>
            {/* right side */}
            <div>
                <Suspense fallback={<div>Loading map...</div>}>
                    <MapComponent/>
                </Suspense>
            </div>
        </div>
        {/* <Link href='/booking'>Booking</Link> */}
    </div>
  );
}
