import Link from 'next/link';
import * as React from 'react';
import logo from '@/assets/hotelifinder-text-logo.png';
import Image from 'next/image';

export function Header() {
  return (
    <div className='absolute m-15 top-0 left-0'>
      <Link href='/'>
        <Image src={logo} alt='Hotelifinder logo' height={50} />
      </Link>
    </div>
  );
}
