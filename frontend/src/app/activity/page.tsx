'use client';

import Link from 'next/link';
import { VenueCard } from '@/components/venueCard';
import { Header } from '@/components/header';
import { useEffect, useState } from 'react';
import { Venue } from '@/schema/venue';

export default function ActivityPage() {
  const [venues, setVenues] = useState<Venue[]>([]);
  const [selectedVenues, setSelectedVenues] = useState<Venue[]>([]);
  const server = "http://localhost:8080/api/v1" + '/venues';

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(server);
        if (!response.ok) {
          throw new Error('Failed to fetch data');
        }
        const result = await response.json();
        setVenues(result);
      } catch (error) {
        throw new Error('Error ' + error);
      }
    };
    fetchData();
  }, [server]);

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
        <Header />
        <div className='flex flex-col items-center'>
          <div className='grid grid-cols-3 gap-14'>
            {venues.map((venue, k) => (
                <VenueCard
                    key={k}
                    name={venue.name}
                    tags={venue.tags}
                    imageUrl={venue.imageURL}
                    isChecked={selectedVenues.includes(venue)}
                    onClick={() => handleVenueSelect(venue)}
                />
            ))}
            <div className='col-span-3' />
            <Link
                className='col-start-3 bg-blue self-end text-center content-center rounded-[20] h-12 drop-shadow-xl'
                href={{
                  pathname: '/result',
                  query: { venues: JSON.stringify(selectedVenues) },
                }}
            >
              Confirm
            </Link>
          </div>
        </div>
      </div>
  );
}