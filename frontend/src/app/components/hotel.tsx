export function Hotel({
  name,
  numOfStars,
  description,
  freeBreakfast,
  freeWiFi,
}: {
  name: string;
  numOfStars: number;
  description: string;
  freeBreakfast: boolean;
  freeWiFi: boolean;
}) {
  return (
    <div>
      <h2>{name}</h2>
      <p>{'⭐️'.repeat(numOfStars)}</p>
      <p>{description}</p>
      <div className='flex flex-row gap-2'>
        {/* tags */}
        {freeBreakfast && <p>Gratis frokost</p>}
        {freeWiFi && <p>Gratis Wi-Fi</p>}
      </div>
    </div>
  );
}
