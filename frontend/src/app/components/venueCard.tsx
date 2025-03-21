import { CheckMark } from './checkmark';
import imagePlaceholder from '@/assets/image-placeholder.svg';
import Image from 'next/image';

export function VenueCard({
                            name,
                            tags,
                            imageUrl,
                            isChecked,
                            onClick,
                          }: {
  name: string;
  tags: string[];
  imageUrl: string;
  isChecked: boolean;
  onClick: () => void;
}) {
  return (
      <div
          onClick={onClick}
          className='flex flex-col relative flex-wrap bg-light-grey h-[200] w-[200] rounded-[20px] p-6 justify-between'
      >
        <div className='flex flex-row justify-between z-30'>
          <div className='flex flex-col gap-2'>
            {tags.map((tag, i) => (
                <div key={i} className='bg-dark-grey rounded-[10px] px-2 w-min'>
                  {tag}
                </div>
            ))}
          </div>
          <CheckMark isChecked={isChecked} height={30} />
        </div>
        <h2 className='z-30 bg-light-grey/80 p-0.5 rounded-[20] text-center'>
          {name}
        </h2>
        {imageUrl.length > 0 ? (
            // eslint-disable-next-line @next/next/no-img-element
            <img
                className='absolute top-0 self-center h-[200] w-[200] object-cover rounded-[20px] z-20'
                src={imageUrl}
                alt={name}
            />
        ) : (
            <Image
                className='absolute self-center z-20'
                src={imagePlaceholder}
                alt='image placeholder'
            />
        )}
      </div>
  );
}