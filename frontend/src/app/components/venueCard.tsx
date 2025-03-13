import { useState } from 'react';
import { CheckMark } from './checkmark';
import imagePlaceholder from '@/app/assets/image-placeholder.svg';
import Image from 'next/image';

export function VenueCard({
  name,
  tags,
  imageUrl,
}: {
  name: string;
  tags: string[];
  imageUrl: string;
}) {
  const [isChecked, setIsChecked] = useState(false);

  return (
    <div
      onClick={() => setIsChecked(!isChecked)}
      className='flex flex-col relative flex-wrap bg-light-grey h-[200] w-[200] rounded-[20px] p-6 justify-between'
    >
      <div className='flex flex-row justify-between'>
        <div className='flex flex-col gap-2'>
          {tags.map((tag, i) => (
            <div key={i} className='bg-dark-grey rounded-[10px] px-2 w-min'>
              {tag}
            </div>
          ))}
        </div>
        <CheckMark isChecked={isChecked} height={30} />
      </div>
      <h2>{name}</h2>
      <Image
        className='absolute self-center'
        loader={() => imagePlaceholder}
        src={imageUrl.length > 0 ? imageUrl : imagePlaceholder}
        alt={imageUrl.length > 0 ? name : 'Image placeholder'}
        width={50}
        height={50}
      />
    </div>
  );
}
