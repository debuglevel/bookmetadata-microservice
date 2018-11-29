# Book Metadata Microservice

This is a REST microservice for retrieving book metadata from various sources.

## Get metadata for a book
`curl -X GET http://localhost:80/books/?isbn=9783499236303`
```
{
  "title": "Minigolf Paradiso",
  "author": "Alexandra Tobor",
  "year": "2014",
  "source": "GoogleBooksInformationFetcher",
  "isbn": "9783499236303"
}
```

If AccessToken for Deutsche Nationalbibliothek (DNB) is set:
`curl -X GET http://localhost:80/books/?isbn=9783499236303`
```
{
  "title": "Minigolf Paradiso",
  "subtitle": "Roman",
  "combinedTitle": "Minigolf Paradiso: Roman",
  "author": "Alexandra Tobor",
  "year": "2016",
  "edition": "Originalausgabe",
  "publisher": "Rowohlt Taschenbuch Verlag",
  "place": "Reinbek bei Hamburg",
  "source": "DnbInformationFetcher",
  "series": "Rororo",
  "volume": "23630",
  "pages": "254",
  "abstractUrl": "http://deposit.dnb.de/cgi-bin/dokserv?id\u003d4507924\u0026prov\u003dM\u0026dok_var\u003d1\u0026dok_ext\u003dhtm",
  "abstract": "Angaben aus der Verlagsmeldung \u003cbr\u003e\u003cbr\u003e\u003ch3\u003e Minigolf Paradiso  Tobor, Minigolf Paradiso  / von Alexandra Tobor\u003c/h3\u003e\u003cbr\u003e\u003cp\u003eDie 16-jährige Malina findet heraus, dass ihr polnischer Großvater, der 1976 ertrunken sein soll, alles andere als tot ist. Alois Dudek lebt: als Talkshow-Lügner, Losbudenverkäufer und Minigolfanlagenbetreiber. Gleich nebenan in Castrop-Rauxel. Malina fährt hin, steht bei Alois vor verschlossener Tür - und wird Zeugin eines Überfalls. Der Alte hat Schulden bei den falschen Leuten. Malina verspricht, ihm zu helfen. Im Gegenzug soll Alois mit ihr in die alte Heimat fahren, um sich in einem Dorf, in dem die Zeit still steht, der Vergangenheit zu stellen: den Wunden, die er abbekommen und die er anderen geschlagen hat.\u003cBR\u003e",
  "isbn": "9783499236303"
}
```
