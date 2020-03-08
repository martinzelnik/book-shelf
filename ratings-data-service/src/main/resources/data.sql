DROP TABLE IF EXISTS ratings;
 
CREATE TABLE ratings (
  isbn VARCHAR(250) PRIMARY KEY,
  rating INT check(rating = null or (rating >= 1 and rating <= 5))
);
 
INSERT INTO ratings (isbn, rating) VALUES
  ('0545010225', 4),
  ('0007124015', 2),
  ('0749396067', 5);
  ('9788081080630', 4);