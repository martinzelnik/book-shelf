# book-shelf
Personal book shelf api

First snapshot of a personal book shelf api, which is to store user's favourites books along with their own reviews. Based on an International Standard Book Number (ISBN), the api fetches information about a particular book from an external api (Goodreads) as well as stores ISBN with user's rating in a local database (in this case, h2 in memory db is used). The architecture is built on 3 microserveces, which are discovered by eureka discovery server: book catalog service, book info service and rating data service. 

Please run the discovery server as well as all three microservices.

The api has so far 3 endpoints: book catalog service server.port=8090
GET: curl -v http://localhost:8090/mybookcatalog/favourites

returns all stored books in the db with ratings and info from the external api

POST: curl -d '{"isbn":" ","rating":" "}' -H "Content-Type: application/json" http://localhost:8090/mybookcatalog/favourites
  
adds a new or updates an existing record, returns the book with its info.  request body param validation: isbn must be Not Null, rating must be an integer Min:1 Man:5 and can be null.

DELETE: curl -X DELETE http://localhost:8090/mybookcatalog/favourites/{isbn}

deletes a particular book from the daatbase based on its isdb

Frontend has to be yet developed
  
