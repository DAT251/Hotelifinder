import Image from 'next/image';
import logo from '@/assets/hotelifinder-logo.png';
import Link from 'next/link';

export default function HomePage() {
  return (
    <Link href={'/search'}>
      <div className='flex flex-col h-screen justify-center items-center'>
        <div className='pt-6 bg-dark-blue rounded-[50]'>
          <Image src={logo} alt='Hotelifinder logo' height={400} />
        </div>
      </div>
    </Link>
  );
}
