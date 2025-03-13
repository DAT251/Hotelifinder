interface Venue {
  name: string;
  address: {
    postalCode: number;
    streetName: string;
    streetNumber: number;
  };
  imageURL: string;
  tags: string[];
}
