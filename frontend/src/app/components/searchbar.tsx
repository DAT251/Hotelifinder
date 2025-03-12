"use client";

import React, { useState, useEffect } from "react";
import SearchIcon from "../assets/Search.svg";
import PinkCircle from "../assets/Ellipse 1.svg";
import Image from "next/image";

interface SearchBarProps {
  data: { id: number; name: string }[];
}

const SearchBar: React.FC<SearchBarProps> = ({ data }) => {
  const [query, setQuery] = useState("");
  const [showDropdown, setShowDropdown] = useState(false);

  const filteredResults = data.filter((item) =>
    item.name.toLowerCase().includes(query.toLowerCase())
  );

  // Function to autocomplete the search when an item is clicked
  const handleSelect = (selectedValue: string) => {
    setQuery(selectedValue); // Set the input field value
    setShowDropdown(false); // Hide dropdown after selection
  };

  // Hide dropdown if input fully matches an item
  useEffect(() => {
    if (
      filteredResults.length === 1 &&
      filteredResults[0].name.toLowerCase() === query.toLowerCase()
    ) {
      setShowDropdown(false);
    } else {
      setShowDropdown(true);
    }
  }, [query, filteredResults]);

  return (
    <div className="relative w-full max-w-3xl px-6 mx-auto">
      {/* Search Box Container */}
      <div className="relative w-full max-w-xl">
        <div className="flex items-center bg-gray-300 rounded-full px-6 py-4 w-full">
          <input
            type="text"
            placeholder="Where do you want to go?"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            className="w-full bg-transparent outline-none text-lg placeholder-black"
          />
          {/* Search Button with SVG Background */}
          <button className="relative w-12 h-12 flex items-center justify-center">
            <Image src={PinkCircle} alt="Button Background" layout="fill" />
            <Image
              src={SearchIcon}
              alt="Search"
              width={24}
              height={24}
              className="absolute"
            />
          </button>
        </div>

        {/* Dropdown (only visible when user types and has non-exact matches) */}
        {query && showDropdown && (
          <ul className="absolute left-0 mt-2 w-full bg-gray-200 rounded-md shadow-lg">
            {filteredResults.length > 0 ? (
              filteredResults.map((item) => (
                <li
                  key={item.id}
                  className="px-4 py-3 hover:bg-gray-300 cursor-pointer"
                  onClick={() => handleSelect(item.name)} // Autocomplete and hide dropdown
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
