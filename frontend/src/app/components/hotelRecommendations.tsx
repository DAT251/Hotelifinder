'use client';

import { useEffect, useState } from 'react';
import Hotel from './hotel';

type HotelData = {
    name: string;
    location: {
        latitude: number;
        longitude: number;
    };
    address: {
        postalCode: string;
        streetName: string;
        streetNumber: number;
    };
};

type HotelRecommendationsProps = {
    onHotelsFetched: (hotels: HotelData[]) => void;
};

export default function HotelRecommendations({ onHotelsFetched }: HotelRecommendationsProps) {
    const [hotels, setHotels] = useState<HotelData[]>([]);
    const [hovered, setHovered] = useState<number | null>(0);

    useEffect(() => {
        const selectedVenues = localStorage.getItem('selectedVenues');
        if (selectedVenues) {
            fetch('http://localhost:8080/api/v1/recommender', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: selectedVenues,
            })
                .then((res) => res.json())
                .then((data: HotelData[]) => {
                    setHotels(data);
                    onHotelsFetched(data);
                })
                .catch((err) => console.error('Failed to fetch hotels:', err));
        }
    }, []);

    return (
        <div className='w-[30vw] flex flex-col gap-4'>
            <h2 className='text-3xl font-bold mb-4 text-center'>Recommended hotels:</h2>
            {hotels.map((hotel, index) => {
                const isHovered = hovered === index;

                return (
                    <div
                        key={index}
                        className={`transition-all duration-300 ease-in-out border rounded-xl shadow-md cursor-pointer overflow-hidden ${
                            isHovered ? 'scale-105 z-10' : 'scale-95 opacity-80'
                        }`}
                        onMouseEnter={() => setHovered(index)}
                        onMouseLeave={() => setHovered(0)}
                    >
                        <div className="p-4">
                            <Hotel name={hotel.name} />
                        </div>
                    </div>
                );
            })}
        </div>
    );
}
