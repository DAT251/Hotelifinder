"use client";

import React, { useState, useEffect } from "react";
import SearchIcon from "../assets/Search.svg";
import PinkCircle from "../assets/Ellipse 1.svg";
import Image from "next/image";
import Link from "next/link";

interface SearchBarProps {
  data: { id: number; name: string }[];
}

const SearchBar: React.FC<SearchBarProps> = ({ data }) => {
  const [query, setQuery] = useState("");
  const [showDropdown, setShowDropdown] = useState(false);
  const [selectedId, setSelectedId] = useState<number | null>(null); // Store matched ID

  const filteredResults = data.filter((item) =>
    item.name.toLowerCase().includes(query.toLowerCase())
  );

  useEffect(() => {
    const matchedItem = data.find(
      (item) => item.name.toLowerCase() === query.toLowerCase()
    );

    if (matchedItem) {
      setShowDropdown(false);
      setSelectedId(matchedItem.id);
    } else {
      setShowDropdown(true);
      setSelectedId(null);
    }
  }, [query, data]);

  return (
    <div className="relative w-full flex justify-center">
      <div className="relative w-full max-w-xl">
        <div className="flex bg-gray-300 rounded-full px-6 py-4">
          {/* Input Field */}
          <input
            type="text"
            placeholder="Where do you want to go?"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === "Enter" && selectedId !== null) {
                window.location.href = `/activity`;
              }
            }}
            className="bg-transparent outline-none text-lg placeholder-black w-full"
          />

          {/* Button Container */}
          <div className="relative w-12 h-12 flex items-center justify-center">
            <Image
              src={PinkCircle}
              alt="Button Background"
              width={48}
              height={48}
              className="pointer-events-none"
            />

            {/* Clickable Search Icon */}
            {selectedId !== null ? (
              <Link href={`/activity`}>
                <div className="absolute w-12 h-12 inset-0 flex items-center justify-center rounded-full">
                  <Image
                    src={SearchIcon}
                    alt="Search"
                    width={24}
                    height={24}
                    className="rounded-full"
                  />
                </div>
              </Link>
            ) : (
              <div className="absolute w-12 h-12 flex items-center justify-center rounded-full cursor-not-allowed opacity-50">
                <Image
                  src={SearchIcon}
                  alt="Search (disabled)"
                  width={24}
                  height={24}
                  className="rounded-full"
                />
              </div>
            )}
          </div>
        </div>

        {/* Dropdown (only visible when user types and has non-exact matches) */}
        {query && showDropdown && (
          <ul className="absolute left-0 mt-2 w-full bg-gray-200 rounded-2xl shadow-lg">
            {filteredResults.length > 0 ? (
              filteredResults.map((item) => (
                <li
                  key={item.id}
                  className="px-4 py-3 hover:bg-gray-300 cursor-pointer rounded-2xl"
                  onClick={() => {
                    setQuery(item.name);
                    setSelectedId(item.id);
                    setShowDropdown(false);
                  }}
                >
                  {item.name}
                </li>
              ))
            ) : (
              <li className="px-4 py-3 text-gray-500">No results found</li>
            )}
          </ul>
        )}
      </div>
    </div>
  );
};

export default SearchBar;
