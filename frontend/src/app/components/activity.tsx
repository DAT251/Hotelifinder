export function Activity({ name, tags }: { name: string; tags: string[] }) {
  return (
    <div className='flex flex-col bg-light-grey h-[200] w-[200] rounded-[20px] p-6 justify-between'>
      <div className='flex flex-col gap-2'>
        {tags.map((tag, i) => (
          <div key={i} className='bg-dark-grey rounded-[10px] px-2 w-min'>
            {tag}
          </div>
        ))}
      </div>
      <h2>{name}</h2>
    </div>
  );
}
