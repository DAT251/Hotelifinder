'use client';
import Link from 'next/link';
import { Header } from '@/components/header';
import Hotel from '@/components/hotel';
import MapComponent from '@/components/map';
import { Suspense } from 'react';

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
      <div className='flex flex-row justify-around items-center'>
        {/* left side */}
        <div className='flex flex-col gap-y-8'>
          <Hotel name='Fana Hotel' />
          <Hotel name='Sentrum Hotel' />
          <Hotel name='Vestkanten Motel' />
        </div>
        {/* right side */}
        <div>
            <Suspense fallback={<div>Loading map...</div>}>
                <MapComponent />
            </Suspense>
        </div>
      </div>
      <Link href='/booking'>Booking</Link>
    </div>
  );
}
