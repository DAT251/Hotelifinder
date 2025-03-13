'use client';

import Link from 'next/link';
import { VenueCard } from '../components/venueCard';
import { Header } from '../components/header';
import { useEffect, useState } from 'react';

export default function ActivityPage() {
  const [venues, setVenues] = useState<Venue[]>([]);
  const server = process.env.NEXT_PUBLIC_SERVER;

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(server + '/venues');
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

  return (
    <div className='flex flex-col'>
      <Header />
      <div className='flex flex-col items-center'>
        <div className='grid grid-cols-3 gap-14'>
          {venues.map((venue, k) => (
            <VenueCard
              key={k}
              name={venue.name}
              tags={venue.tags}
              imageUrl={venue.imageURL}
            />
          ))}
          <div className='col-span-3' />
          <Link
            className='col-start-3 bg-blue self-end text-center content-center rounded-[20] h-12 drop-shadow-xl'
            href='/result'
          >
            Confirm
          </Link>
        </div>
      </div>
    </div>
  );
}
