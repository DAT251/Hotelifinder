'use client';

import Link from 'next/link';
import { VenueCard } from '@/components/venueCard';
import { Header } from '@/components/header';
import { useEffect, useState } from 'react';
import { Venue } from '../schema/venue';
import { useSearchParams, useRouter } from 'next/navigation';

export default function ActivityPage() {
  const [venues, setVenues] = useState<Venue[]>([]);
  const [selectedVenues, setSelectedVenues] = useState<Venue[]>([]);
  const searchParams = useSearchParams();
  const router = useRouter();
  const city = searchParams.get('city'); // Get city from URL

  useEffect(() => {
    if (!city) {
      router.push('/'); // Redirect to homepage if no city is provided
      return;
    }

    const fetchData = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/v1/venues?city=${encodeURIComponent(city)}`);
        if (!response.ok) {
          throw new Error('Failed to fetch data');
        }
        const result = await response.json();
        setVenues(result);
      } catch (error) {
        console.error('Error fetching venues:', error);
      }
    };
    fetchData();
  }, [city]); // Only runs when city changes

  const handleVenueSelect = (venue: Venue) => {
    setSelectedVenues(prev => {
      if (prev.includes(venue)) {
        return prev.filter(v => v !== venue);
      } else {
        return [...prev, venue];
      }
    });
  };

  return (
    <div className='flex flex-col h-screen justify-center'>
                    isChecked={selectedVenues.includes(venue)}
                    onClick={() => handleVenueSelect(venue)}
      <Header />
      <div className='flex flex-col items-center'>
        <h1 className='text-2xl font-bold text-center mt-4'>Venues in {city}</h1>
        <div className='grid grid-cols-3 gap-14'>
          {venues.length > 0 ? (
            venues.map((venue, k) => (
              <VenueCard
                key={k}
                name={venue.name}
                tags={venue.tags}
                imageUrl={venue.imageURL}
              />
            ))
          ) : (
            <p className='col-span-3 text-center text-gray-500'>No venues found in {city}.</p>
          )}
          <div className='col-span-3' />
          <Link
            className='col-start-3 bg-blue self-end text-center content-center rounded-[20] h-12 drop-shadow-xl'
            href={`/result?city=${city}`}
          >
            Confirm
          </Link>
        </div>
      </div>
  );
}