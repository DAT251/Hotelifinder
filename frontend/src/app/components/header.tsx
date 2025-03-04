import Link from 'next/link';
import * as React from 'react';

export function Header() {
  return (
    <div className='m-15'>
      <Link href='/'>
        <h1 className='text-left'>Hotelifinder</h1>
      </Link>
    </div>
  );
}
