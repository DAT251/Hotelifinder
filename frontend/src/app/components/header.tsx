export function Header() {
  return (
    <div className='m-15'>
      <h1 className='text-center'>Hotelifinder</h1>
      <div className='flex'>
        {/* button */}
        <p>button</p>
        {/* language + login */}
        <div className='flex absolute right-10'>
          <p>langugage</p>
          <p>login</p>
        </div>
      </div>
      <div>
        {/* filters */}
        <p>filter</p>
      </div>
    </div>
  );
}
