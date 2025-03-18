import { Header } from "./components/header";

import SearchBar from "./components/searchbar";

const hotelList = [
  { id: 1, name: "Bergen" },
  { id: 2, name: "Oslo" },
  { id: 3, name: "Trondheim" },
];

export default function HomePage() {
  return (
    <div className="flex flex-col">
      {/* Fix: Ensure the Header does not take extra space */}
      <Header />
      {/* SearchBar should fill remaining space and be centered */}
      <div className="flex justify-center content-center">
        <SearchBar data={hotelList} />
      </div>
    </div>
  );
}
