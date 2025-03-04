import Link from 'next/link';
import { Activity } from '../components/activity';
import { Header } from '../components/header';

export default function ActivityPage() {
  return (
    <div className='flex flex-col'>
      <Header />
      <div className='flex flex-col items-center'>
        <div className='grid grid-cols-3 gap-14'>
          <Activity name={'Fana Golfbane'} tags={['Golf']} />
          <Activity name={'Lagunen'} tags={['Shopping', 'Bowling', 'Kino']} />
          <Activity
            name={'Vestkanten'}
            tags={['Shopping', 'Badeland', 'Bowling']}
          />
        </div>
        <Link href='/result'>Confirm</Link>
      </div>
    </div>
  );
}
