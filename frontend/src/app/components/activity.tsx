import { useState } from 'react';
import { CheckMark } from '../components/checkmark';

export function Activity({ name, tags }: { name: string; tags: string[] }) {
  const [isChecked, setIsChecked] = useState(false);
  return (
    <div
      onClick={() => setIsChecked(!isChecked)}
      className='flex flex-col flex-wrap bg-light-grey h-[200] w-[200] rounded-[20px] p-6 justify-between'
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
    </div>
  );
}
