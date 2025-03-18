import Link from 'next/link';
import * as React from 'react';

export function Header() {
  return (
    <div className='absolute m-15 top-0 left-0'>
      <Link href='/'>
        <h1 className='text-left'>Hotelifinder</h1>
      </Link>
    </div>
  );
}
