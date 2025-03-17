'use client';
import React, { useState, useEffect } from 'react';
import { APIProvider, Map, Marker, InfoWindow, useMap } from '@vis.gl/react-google-maps';
import { useSearchParams } from 'next/navigation';
import { Venue } from '../schema/venue';


const api_key = process.env.NEXT_PUBLIC_API_KEY;
const zoom = 12;
const center = { lat: 59.9139, lng: 10.7522 };

const MapContent = ({ selectedVenues  }) => {
  const [hotels, setHotels] = useState<any[]>([]);
  const [selectedPoint, setSelectedPoint] = useState<{ name: string; lat: number; lng: number } | null>(null);
  const [showInitialMarkers, setShowInitialMarkers] = useState(true);
  const [selectedHotel, setSelectedHotel] = useState<any | null>(null);
  const [geocodedVenues, setGeocodedVenues] = useState<{ name: string; lat: number; lng: number }[]>([]);
  const map = useMap();

  useEffect(() => {
    const geocodeVenues = async () => {
      const geocoded = await Promise.all(selectedVenues.map(async (venue: Venue) => {
        const address = `${venue.address.streetName} ${venue.address.streetNumber}, ${venue.address.postalCode}`;
        const response = await fetch(`https://maps.googleapis.com/maps/api/geocode/json?address=${encodeURIComponent(address)}&key=${api_key}`);
        const data = await response.json();
        if (data.results && data.results.length > 0) {
          const location = data.results[0].geometry.location;
          return { name: venue.name, lat: location.lat, lng: location.lng };
        }
        return null;
      }));
      setGeocodedVenues(geocoded.filter(venue => venue !== null));
    };

    geocodeVenues();
  }, [selectedVenues]);


  useEffect(() => {
    if (map && showInitialMarkers && geocodedVenues.length > 0) {
      fitMapToBounds(geocodedVenues);
    }
  }, [map, showInitialMarkers, geocodedVenues]);

  const handleMarkerClick = (point: { name: string; lat: number; lng: number }) => {
    setSelectedPoint(point);
    setShowInitialMarkers(false);
    searchHotels(point);
  };

  const searchHotels = (point: { lat: number; lng: number }) => {
    if (!window.google || !window.google.maps || !window.google.maps.places) {
      console.error('Google Maps Places library not loaded');
      return;
    }

    const map = new window.google.maps.Map(document.createElement('div'));
    const service = new window.google.maps.places.PlacesService(map);

    const request = {
      location: new window.google.maps.LatLng(point.lat, point.lng),
      radius: 500,
      type: 'lodging',
    };

    service.nearbySearch(request, (results, status) => {
      if (status === window.google.maps.places.PlacesServiceStatus.OK && results) {
        setHotels(results);
        fitMapToBounds(results)
      } else {
        console.error('Places API error:', status);
      }
    });
  };

  const fitMapToBounds = (locations: any[]) => {
    if (!map || locations.length === 0) return;

    const bounds = new window.google.maps.LatLngBounds();

    locations.forEach((location) => {
      if (location.geometry && location.geometry.location) {
        bounds.extend({
          lat: location.geometry.location.lat(),
          lng: location.geometry.location.lng(),
        });
      } else if (location.lat && location.lng) {
        bounds.extend({
          lat: location.lat,
          lng: location.lng,
        });
      }
    });

    map.fitBounds(bounds);
  };

  const resetMap = () => {
    setSelectedPoint(null);
    setHotels([]);
    setShowInitialMarkers(true);
    setSelectedHotel(null);

    if(map){
      map.setCenter(center);
      map.setZoom(zoom)
    }
  };

  const handleHotelClick = (hotel: any) => {
    setSelectedHotel(hotel);
  };

  return (
      <>
        <Map
            style={{ width: '60vw', height: '50vh', maxWidth: '800px' }}
            defaultCenter={center}
            defaultZoom={zoom}
            gestureHandling={'greedy'}
            disableDefaultUI={false}
        >

          {showInitialMarkers &&
              geocodedVenues.map((venue, index) => (
                  <Marker
                      key={index}
                      position={{ lat: venue.lat, lng: venue.lng }}
                      onClick={() => handleMarkerClick(venue)}
                  />
              ))}

          {hotels.map((hotel, index) => (
              <Marker
                  key={index}
                  position={{
                    lat: hotel.geometry.location.lat(),
                    lng: hotel.geometry.location.lng(),
                  }}
                  title={hotel.name}
                  onClick={() => handleHotelClick(hotel)}
              />
          ))}

          {selectedHotel && (
              <InfoWindow
                  position={{
                    lat: selectedHotel.geometry.location.lat(),
                    lng: selectedHotel.geometry.location.lng(),
                  }}
                  onCloseClick={() => setSelectedHotel(null)}
              >
                <div style={{color: "black"}}>
                  <h3>{selectedHotel.name}</h3>
                  <p>Rating: {selectedHotel.rating || 'N/A'}</p>
                  <p>Address: {selectedHotel.vicinity}</p>
                </div>
              </InfoWindow>
          )}
        </Map>
        {selectedPoint && (
            <button
                onClick={resetMap}
                style={{
                  marginTop: '10px',
                  padding: '10px 20px',
                  fontSize: '16px',
                  cursor: 'pointer',
                }}
            >
              Reset Map
            </button>
        )}
      </>
  );
};

export default function MapComponent() {
  const searchParams = useSearchParams();
  const venuesParam = searchParams.get('venues');
  const selectedVenues = venuesParam ? JSON.parse(venuesParam) : [];

  return (
      <div>
        <APIProvider apiKey={api_key as string} libraries={['places']}>
          <MapContent selectedVenues={selectedVenues} />
        </APIProvider>
      </div>
  );
}