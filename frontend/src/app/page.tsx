import { Header } from "./components/header";

import SearchBar from "./components/searchbar";

const hotelList = [
  { id: 1, name: "Bergen" },
  { id: 2, name: "Oslo" },
  { id: 3, name: "Trondheim" },
];

export default function HomePage() {
  return (
    <div className='flex flex-col h-screen justify-center items-center'>
      <Header />
        <SearchBar data={hotelList} />
    </div>
  );
}
