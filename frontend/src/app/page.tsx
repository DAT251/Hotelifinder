import Link from "next/link";
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
      <Header />
      <div className="flex flex-col items-center gap-8">
        <div>
          <SearchBar data={hotelList} />
        </div>
        <Link href="/activity">Activities</Link>
      </div>
    </div>
  );
}
