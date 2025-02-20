import { Header } from './components/header';
import { Hotel } from './components/hotel';

export default function Home() {
  return (
    <div className='flex flex-col justify-around items-center'>
      <Header />
      <div className='flex gap-8'>
        <div className='flex flex-col gap-2'>
          <Hotel
            name='Bergen Børs Hotel'
            numOfStars={4.5}
            description='Formal hotel with restaurant/bar'
            freeBreakfast={true}
            freeWiFi={true}
          />
          <Hotel
            name='Opus XVI, sn SLH Hotel'
            numOfStars={4.6}
            description='Genteel hotel with dining & event space'
            freeBreakfast={true}
            freeWiFi={true}
          />
          <Hotel
            name='Skostredet Hotel'
            numOfStars={4.3}
            description='Formal hotel with restaurant/bar'
            freeBreakfast={false}
            freeWiFi={false}
          />
        </div>
        <div>
          <p>Map</p>
        </div>
      </div>
      {/* footer? */}
    </div>
  );
}
