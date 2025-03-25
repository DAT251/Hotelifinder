'use client';

import { Suspense, useEffect, useState } from 'react';
import Link from 'next/link';
import { VenueCard } from '@/components/venueCard';
import { Header } from '@/components/header';
import { Venue } from '../schema/venue';
import { useSearchParams, useRouter } from 'next/navigation';

function ActivityPageContent() {
  const [venues, setVenues] = useState<Venue[]>([]);
  const [selectedVenues, setSelectedVenues] = useState<Venue[]>([]);
  const searchParams = useSearchParams();
  const router = useRouter();
  const city = searchParams.get('city');

  useEffect(() => {
    if (!city) {
      router.push('/');
      return;
    }

    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/v1/venues?city=${encodeURIComponent(city)}`
        );
        if (!response.ok) throw new Error('Failed to fetch data');
        const result = await response.json();
        setVenues(result);
      } catch (error) {
        console.error('Error fetching venues:', error);
      }
    };

    fetchData();
  }, [city, router]);

  const handleVenueSelect = (venue: Venue) => {
    setSelectedVenues(prev =>
      prev.includes(venue) ? prev.filter(v => v !== venue) : [...prev, venue]
    );
  };

  return (
    <div className='flex flex-col h-screen justify-center'>
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
                isChecked={selectedVenues.includes(venue)}
                onClick={() => handleVenueSelect(venue)}
              />
            ))
          ) : (
            <p className='col-span-3 text-center text-gray-500'>No venues found in {city}.</p>
          )}
          <div className='col-span-3' />
          <Link
            className='col-start-3 bg-blue self-end text-center content-center rounded-[20] h-12 drop-shadow-xl'
            href={`/result?city=${city}`}
            onClick={() => {
              localStorage.setItem('selectedVenues', JSON.stringify(selectedVenues));
            }}
          >
            Confirm
          </Link>
        </div>
      </div>
    </div>
  );
}

// Final Exported Page
export default function ActivityPage() {
  return (
    <Suspense fallback={<div>Loading activity page...</div>}>
      <ActivityPageContent />
    </Suspense>
  );
}
