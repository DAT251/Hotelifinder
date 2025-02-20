import * as React from 'react';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';

export function Header() {
  return (
    <div className='m-15 flex flex-col gap-2 items-center'>
      <h1 className='text-center'>Hotelifinder</h1>
      <div className='flex flex-row'>
        <ButtonGroup
          className='SearchBG'
          variant='text'
          aria-label='Basic button group'
          color='inherit'
          size='small'
        >
          <Button className='Button'>Bergen</Button>
          <Button className='Button'>Feb 19-26</Button>
          <Button className='Button'>2 guests</Button>
          <Button className='Button'>Search</Button>
        </ButtonGroup>
        {/* language + login */}
        <div className='flex absolute right-10'>
          <ButtonGroup
            className='ButtonGroup'
            variant='text'
            aria-label='Basic button group'
            color='inherit'
            size='small'
          >
            <Button className='Button'>Language</Button>
            <Button className='Button'>User</Button>
          </ButtonGroup>
        </div>
      </div>
      <div className='flex flex-row gap-2'>
        {/* labels & filters */}
        <Button className='Button' color='inherit' size='small'>
          Prioritize Public Transportation Over Activity
        </Button>
        <ButtonGroup
          className='ButtonGroup'
          variant='text'
          aria-label='Basic button group'
          color='inherit'
          size='small'
        >
          <Button className='Button'>Yes</Button>
          <Button className='Button'>No</Button>
        </ButtonGroup>
        <Button className='Button' size='small'>
          Preferred activities
        </Button>
        <Button className='Filter' size='small'>
          Hiking
        </Button>
        <Button className='Filter' size='small'>
          Shopping activities
        </Button>
        <Button className='Filter' size='small'>
          Filters
        </Button>
      </div>
    </div>
  );
}
