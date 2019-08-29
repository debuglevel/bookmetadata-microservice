**This README is out of date for the current commit, as this microservice is migrating to Micronaut! Switch to a tagged commit (i.e. 0.0.2) to get a stable and well documented version**

# Book Metadata Microservice

This is a REST microservice for retrieving book metadata from various sources.

## Get metadata for a book
`curl -X GET http://localhost:8080/books/9783499236303`
```
{
  "title": "Minigolf Paradiso",
  "author": "Alexandra Tobor",
  "year": "2016",
  "source": "GoogleBooksInformationFetcher",
  "isbn": "9783499236303"
}
```

If AccessToken for Deutsche Nationalbibliothek (DNB) is set, results (at least for german books) are way better:
`curl -X GET http://localhost:8080/books/9783499236303`
```
{
  "isbn" : "9783499236303",
  "requestedIsbn" : "9783499236303",
  "title" : "Minigolf Paradiso",
  "subtitle" : "Roman",
  "combinedTitle" : "Minigolf Paradiso: Roman",
  "author" : "Tobor, Alexandra",
  "year" : "2016",
  "edition" : "Originalausgabe",
  "publisher" : "Rowohlt Taschenbuch Verlag",
  "place" : "Reinbek bei Hamburg",
  "source" : "Deutsche Nationalbibliothek",
  "series" : "Rororo",
  "volume" : "23630",
  "pages" : "254",
  "abstractUrl" : "http://deposit.dnb.de/cgi-bin/dokserv?id=4507924&prov=M&dok_var=1&dok_ext=htm",
  "abstract" : "Angaben aus der Verlagsmeldung <br><br><h3> Minigolf Paradiso  Tobor, Minigolf Paradiso  / von Alexandra Tobor</h3><br><p>Die 16-jährige Malina findet heraus, dass ihr polnischer Großvater, der 1976 ertrunken sein soll, alles andere als tot ist. Alois Dudek lebt: als Talkshow-Lügner, Losbudenverkäufer und Minigolfanlagenbetreiber. Gleich nebenan in Castrop-Rauxel. Malina fährt hin, steht bei Alois vor verschlossener Tür - und wird Zeugin eines Überfalls. Der Alte hat Schulden bei den falschen Leuten. Malina verspricht, ihm zu helfen. Im Gegenzug soll Alois mit ihr in die alte Heimat fahren, um sich in einem Dorf, in dem die Zeit still steht, der Vergangenheit zu stellen: den Wunden, die er abbekommen und die er anderen geschlagen hat.<BR>",
  "language" : "de"
}
```
