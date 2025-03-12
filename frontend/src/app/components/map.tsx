'use client';
import React, { useEffect, useState } from 'react';
import { APIProvider, Map, useMap, Marker } from '@vis.gl/react-google-maps';

const api_key = process.env.NEXT_PUBLIC_API_KEY;
const zoom = 12;
const center = { lat: 60.391, lng: 5.322 };

const locations = [
  { address: 'Lagunen Storsenter, Bergen, Norge', type: 'red' },
  { address: 'Vestkanten Storsenter, Bergen, Norge', type: 'red' },
  { address: 'Oasen Storsenter, Bergen, Norge', type: 'red' },
  { address: 'Rogges vei 66 B, Bergen, Norge', type: 'blue' },
];

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
          <LocationMarkers locations={locations} />
        </Map>
      </APIProvider>
    </div>
  );
}

function LocationMarkers({ locations }:{ locations: { address: string; type: string }[] }) {
  const map = useMap();
  const [markers, setMarkers] = useState<{ location: google.maps.LatLngLiteral; type: string }[]>([]);


  useEffect(() => {
    if (!map) return;

    const geocoder = new google.maps.Geocoder();


    locations.forEach((loc) => {
      geocoder.geocode({ address: loc.address }, (results, status) => {
        if (status === 'OK' && results && results[0]) {
          const position = results[0].geometry.location;
          setMarkers((prevMarkers) => [
            ...prevMarkers,
            { location: { lat: position.lat(), lng: position.lng() }, type: loc.type },
          ]);
        }
      });
    });
  }, [map, locations]);

  useEffect(() => {
    if (!map || markers.length === 0) return;

    const bounds = new google.maps.LatLngBounds();
    markers.forEach((marker) => bounds.extend(marker.location));
    map.fitBounds(bounds);


    const padding = 50;
    map.panToBounds(bounds, padding);
  }, [map, markers]);

  return (
      <>
        {markers.map((marker, index) => (
            <Marker key={index} position={marker.location}
                    icon={{
              url: marker.type === 'red'
                  ? 'http://maps.google.com/mapfiles/ms/icons/red-dot.png'
                  : 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
            }}
            />
        ))}
      </>
  );
}

/*
{markers.map((marker, index) => (
            <Marker key={index} position={marker.location}
                    icon={{
                      url: marker.color === 'red'
                          ? 'http://localhost:8080/images/img.png'
                          : marker.color === 'konsert'
                              ? 'http://localhost:8080/images/img_2.png'
                              : 'http://localhost:8080/images/img_1.png',
                      scaledSize: new google.maps.Size(40, 40),
            }}
            />
        ))}
 */


/*
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
*/

