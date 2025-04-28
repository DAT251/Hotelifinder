package dat251_gruppe2.hotelifinder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DistanceCacheDTO {
    private Metadata metadata;
    private List<HotelWithDistances> hotels;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<HotelWithDistances> getHotels() {
        return hotels;
    }

    public void setHotels(List<HotelWithDistances> hotels) {
        this.hotels = hotels;
    }

    // Getters and setters
    public static class Metadata {
        @JsonProperty("generated_at")
        private String generatedAt;
        @JsonProperty("hotel_count")
        private int hotelCount;
        @JsonProperty("venue_count")
        private int venueCount;

        public String getGeneratedAt() {
            return generatedAt;
        }

        public void setGeneratedAt(String generatedAt) {
            this.generatedAt = generatedAt;
        }

        public int getHotelCount() {
            return hotelCount;
        }

        public void setHotelCount(int hotelCount) {
            this.hotelCount = hotelCount;
        }

        public int getVenueCount() {
            return venueCount;
        }

        public void setVenueCount(int venueCount) {
            this.venueCount = venueCount;
        }
        // Getters and setters
    }

    public static class HotelWithDistances {
        private String name;
        private Location location;
        private Address address;
        private double rating;
        private List<VenueDistance> distances;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public List<VenueDistance> getDistances() {
            return distances;
        }

        public void setDistances(List<VenueDistance> distances) {
            this.distances = distances;
        }
        // Getters and setters
    }

    public static class VenueDistance {
        private String venue;
        @JsonProperty("distance_text")
        private String distanceText;
        @JsonProperty("distance_meters")
        private int distanceMeters;
        @JsonProperty("duration_text")
        private String durationText;
        @JsonProperty("duration_seconds")
        private int durationSeconds;

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public String getDistanceText() {
            return distanceText;
        }

        public void setDistanceText(String distanceText) {
            this.distanceText = distanceText;
        }

        public int getDistanceMeters() {
            return distanceMeters;
        }

        public void setDistanceMeters(int distanceMeters) {
            this.distanceMeters = distanceMeters;
        }

        public String getDurationText() {
            return durationText;
        }

        public void setDurationText(String durationText) {
            this.durationText = durationText;
        }

        public int getDurationSeconds() {
            return durationSeconds;
        }

        public void setDurationSeconds(int durationSeconds) {
            this.durationSeconds = durationSeconds;
        }
        // Getters and setters
    }

    public static class Location {
        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }
        // Getters and setters
    }

    public static class Address {
        @JsonProperty("streetNumber")
        private String streetNumber;
        @JsonProperty("streetName")
        private String streetName;
        @JsonProperty("postalCode")
        private String postalCode;

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }
        // Getters and setters
    }
}
