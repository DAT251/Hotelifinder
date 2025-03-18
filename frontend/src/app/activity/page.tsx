'use client';

import Link from 'next/link';
import { Activity } from '../components/activity';
import { Header } from '../components/header';
import venues from '../assets/venues.json';

export default function ActivityPage() {
  return (
    <div className='flex flex-col h-screen justify-center'>
      <Header />
      <div className='flex flex-col items-center'>
        <div className='grid grid-cols-3 gap-14'>
          {venues.venues.map((venue, k) => (
            <Activity key={k} name={venue.title} tags={venue.tags} />
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
