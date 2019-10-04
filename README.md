<!--- some badges to display on the GitHub page -->
![Travis (.org)](https://img.shields.io/travis/debuglevel/bookmetadata-microservice?label=Travis%20build)
![Gitlab pipeline status](https://img.shields.io/gitlab/pipeline/debuglevel/bookmetadata-microservice?label=GitLab%20build)
![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/debuglevel/bookmetadata-microservice?sort=semver)
![GitHub](https://img.shields.io/github/license/debuglevel/bookmetadata-microservice)

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
  "abstract" : "Angaben aus der Verlagsmeldung \n\n\n Minigolf Paradiso Tobor, Minigolf Paradiso / von Alexandra Tobor\n\n\nDie 16-jährige Malina findet heraus, dass ihr polnischer Großvater, der 1976 ertrunken sein soll, alles andere als tot ist. Alois Dudek lebt: als Talkshow-Lügner, Losbudenverkäufer und Minigolfanlagenbetreiber. Gleich nebenan in Castrop-Rauxel. Malina fährt hin, steht bei Alois vor verschlossener Tür - und wird Zeugin eines Überfalls. Der Alte hat Schulden bei den falschen Leuten. Malina verspricht, ihm zu helfen. Im Gegenzug soll Alois mit ihr in die alte Heimat fahren, um sich in einem Dorf, in dem die Zeit still steht, der Vergangenheit zu stellen: den Wunden, die er abbekommen und die er anderen geschlagen hat.",
  "language" : "de"
}
```
