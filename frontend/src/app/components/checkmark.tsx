import Image from 'next/image';
import check from '../assets/checkmark.svg';
import greenEllipse from '../assets/greenEllipse.svg';
import greyEllipse from '../assets/greyEllipse.svg';

export function CheckMark({
  isChecked,
  height,
}: {
  isChecked: boolean;
  height: number;
}) {
  return (
    <div className='relative'>
      {isChecked ? (
        <div>
          <Image
            className='absolute z-[1]'
            src={check}
            height={height}
            alt='checkmark'
          />
          <Image src={greenEllipse} height={height} alt='green ellipse' />
        </div>
      ) : (
        <div>
          <Image src={greyEllipse} height={height} alt='grey ellipse' />
        </div>
      )}
    </div>
  );
}
