## catalog API

objective: online book store with rating system

entities:

- user
can have multiple books
book rated by multiple users

- book
add book
update book
remove book


onlinebooks.com/api/userId

```json
{
  "id": "userId",
  "name": "Jon Snow",
  "books": [
    {
      "id": 1234,
      "title": "test1",
      "author": "author1",
      "rating": 5
    },
    {
      "id": 1234,
      "title": "test2",
      "author": "author2",
      "rating": 4
    }
  ]
}
```

## microservices

- [catalog](http://localhost:8081/catalog/userId) book-catalog-service - blue
The catalog relies on the other services to aggregate information for each user about their books and their ratings.

david
alex
andrei
rares
lucian
andreea

- [books](http://localhost:8082/books/bookId) book-info-service - green
The book info service provides book info, and it is the main storage of the application

andreea a
ciprian
robert
stefan
claudia
ioana
cristi

- [ratings](http://localhost:8083/ratings/users/userId) ratings-data-service - red
The ratings data service handles the rating the user provides for books. 

vlad
radu
bogdan
anastasia
mihai
claudia
razvan
