-- Init data, can only be run once (or duplicates), delete data folder to run again

/*
INSERT INTO users (username, password) VALUES ('casper', 'glimt');
INSERT INTO users (username, password) VALUES ('user2', 'password');

INSERT INTO preferences (preferred_hotel_type, preferred_location, user_id)
VALUES ('Luxury', 'Fana', (SELECT id FROM users WHERE username = 'casper'));
INSERT INTO preferences (preferred_hotel_type, preferred_location, user_id)
VALUES ('Budget', 'Loddefjord', (SELECT id FROM users WHERE username = 'user2'));

INSERT INTO booking (hotel_name, booking_date, user_id)
VALUES ('Fana Golf&Pils', '2025-03-10', (SELECT id FROM users WHERE username = 'casper'));
INSERT INTO booking (hotel_name, booking_date, user_id)
VALUES ('Hotel Boss', '2025-03-15', (SELECT id FROM users WHERE username = 'user2'));
*/