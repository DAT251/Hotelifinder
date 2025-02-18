import { Header } from './components/header';

export default function Home() {
  return (
    <div className='flex flex-col justify-around items-center'>
      <Header />
      <div className='flex gap-8'>
        <div>
          <p>liste med hoteller</p>
          <p>liste med hoteller</p>
          <p>liste med hoteller</p>
        </div>
        <div>
          <p>kart</p>
        </div>
      </div>
      {/* footer? */}
    </div>
  );
}
