export default function Hotel({ name }: { name: string }) {
  return (
    <div className='bg-light-grey w-[20em] p-4 rounded-[15px]'>
      <h2>{name}</h2>
    </div>
  );
}
