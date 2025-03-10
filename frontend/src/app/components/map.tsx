'use client';
import React, { useEffect, useState } from 'react';
import { APIProvider, Map, useMap } from '@vis.gl/react-google-maps';

const api_key = process.env.NEXT_PUBLIC_API_KEY;
const zoom = 12;
const center = { lat: 60.391, lng: 5.322 };

const startLocation: string = 'Lagunen Storsenter, Bergen, Norge';
const destLocation: string = 'Vestkanten Storsenter, Bergen, Norge';

export default function MapComponent() {
  return (
    <div>
      <APIProvider apiKey={api_key as string}>
        <Map
          style={{ width: '60vw', height: '50vh', maxWidth: '800px' }}
          defaultCenter={center}
          defaultZoom={zoom}
          gestureHandling={'greedy'}
          disableDefaultUI={false}
        >
          <DirectionsRenderer />
        </Map>
      </APIProvider>
    </div>
  );
}

function DirectionsRenderer() {
  const map = useMap();
  const [directions, setDirections] =
    useState<google.maps.DirectionsResult | null>(null);

  useEffect(() => {
    if (!map) return;

    const directionsService = new google.maps.DirectionsService();
    directionsService.route(
      {
        origin: startLocation,
        destination: destLocation,
        travelMode: google.maps.TravelMode.TRANSIT,
      },
      (result, status) => {
        if (status === 'OK' && result) {
          setDirections(result);
        }
      }
    );
  }, [map]);

  useEffect(() => {
    if (!map || !directions) return;

    const directionsRenderer = new google.maps.DirectionsRenderer();
    directionsRenderer.setMap(map);
    directionsRenderer.setDirections(directions);

    return () => directionsRenderer.setMap(null);
  }, [map, directions]);

  return null;
}
