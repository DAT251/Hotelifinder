import Link from 'next/link';

export default function Activities() {
  return (
    <div className='flex flex-col justify-around items-center'>
      <div>
        <p>Map</p>
      </div>
      <Link href='/results'>Results</Link>
    </div>
  );
}
