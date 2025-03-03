import Link from 'next/link';
import { Header } from './components/header';

import searchbar from './assets/searchbar.svg';
import Image from 'next/image';

export default function HomePage() {
  return (
    <div className='flex flex-col'>
      <Header />
      <div className='flex flex-col items-center gap-8'>
        <div>
          <p>Where do you want to go?</p>
          <Image src={searchbar} height='50' alt='' />
        </div>
        <Link href='/activity'>Activities</Link>
      </div>
      {/* footer? */}
      <Link href='/activites'>Activities</Link>
    </div>
  );
}
