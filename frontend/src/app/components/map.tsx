import React from 'react';
import { APIProvider, Map } from '@vis.gl/react-google-maps';

const api_key = process.env.NEXT_PUBLIC_API_KEY;
const zoom = 12;
// Bergen latitude and longitude
const center = { lat: 60.391, lng: 5.322 };

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
        />
      </APIProvider>
    </div>
  );
}
