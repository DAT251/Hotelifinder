import { Header } from '../components/header';

export default function BookingPage() {
  return (
    <div className='flex flex-col h-screen justify-center'>
      <Header />
      <div className='flex flex-row justify-around'>
        {/* left side */}
        <div>
          <p>How many guests?</p>
        </div>
        {/* right side */}
        <div>
          <p>Booking</p>
          <div className='flex'>
            <p>Hotel</p>
            <p>Activities</p>
          </div>
        </div>
      </div>
    </div>
  );
}
