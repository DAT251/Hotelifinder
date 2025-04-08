'use client';

import { useState } from 'react';
import Hotel from './hotel';

const mockHotels = [
    { name: 'Fana Hotel', id: 1 },
    { name: 'Sentrum Hotel', id: 2 },
    { name: 'Vestkanten Motel', id: 3 },
];

export default function HotelRecommendations() {
    const [hovered, setHovered] = useState<number | null>(1);

    return (
        <div className='w-[30vw] flex flex-col gap-4'>
            <h2 className='!text-2xl font-bold mb-4 text-center'>Recommended hotels:</h2>
            {mockHotels.map((hotel) => {
                const isHovered = hovered === hotel.id;

                return (
                    <div
                        key={hotel.id}
                        className={`transition-all duration-300 ease-in-out border rounded-xl shadow-md cursor-pointer overflow-hidden ${
                            isHovered ? 'scale-105 z-10' : 'scale-85 opacity-80'
                        }`}
                        onMouseEnter={() => setHovered(hotel.id)}
                        onMouseLeave={() => setHovered(1)}
                    >
                        <div className="p-4">
                            <Hotel name={hotel.name}/>
                        </div>
                    </div>
                );
            })}
        </div>
    );
}
