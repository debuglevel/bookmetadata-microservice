package de.debuglevel.bookmetadata.metadataprovider.dnb

import de.debuglevel.bookmetadata.BookResponseDTO
import java.util.stream.Stream

object TestDataProvider {
    fun bookProvider() = Stream.of(
        BookResponseDTO("9783551551672").apply {
            requestedIsbn = "9783551551672"
            isbn = requestedIsbn
            title = "Harry Potter und der Stein der Weisen"
            combinedTitle = title
            author = "Rowling, J. K."
            //author = "Joanne K. Rowling. Aus dem Engl. von Klaus Fritz"
            publisher = "Carlsen"
            year = "1998"
            pages = "335"
            place = "Hamburg"
            language = "de"
        },
        BookResponseDTO("9783868943238").apply {
            requestedIsbn = "9783868943238"
            isbn = requestedIsbn
            title = "Psychologie"
            edition = "21., aktualisierte und erweiterte Auflage"
            tableOfContentsUrl = "http://d-nb.info/1166686841/04"
            combinedTitle = title
            author = "Gerrig, Richard J."
            //author = "Richard J. Gerrig ; aus dem Amerikanischen von Andreas Klatt ; Tobias Dörfler, Jeanette Roos (Hrsg.)"
            publisher = "Pearson"
            year = "2018"
            pages = "834"
            place = "Hallbergmoos/Germany"
            language = "de"
        },
        BookResponseDTO("9783621285674").apply {
            requestedIsbn = "9783621285674"
            isbn = requestedIsbn
            title = "Kognitive Therapie der Depression"
            combinedTitle = title
            edition = "5., neu ausgestattete Auflage"
            tableOfContentsUrl = "http://d-nb.info/1127602284/04"
            abstractUrl =
                "http://deposit.dnb.de/cgi-bin/dokserv?id=9fe9bde43c444775be1b264cc8abab4a&prov=M&dok_var=1&dok_ext=htm"
            abstract =
                "Angaben aus der Verlagsmeldung <br><br><h3> Kognitive Therapie der Depression  Cognitive Therapy of Depression  / von Aaron T. Beck, A. John Rush, Brian F. Shaw, Gary Emery</h3><br><p>Der weltweit anerkannte und bewährte Ansatz zur Behandlung von Depressionen: Aaron T. Beck erklärt sein Modell und geht konkret und ausführlich auf die kognitive Therapie für depressive Patienten ein.<BR><BR>Mit Erkenntnis, Einsicht und Bewusstwerdung gegen Depression: Die kognitive Therapie der Depression nach Aaron T. Beck ist weltweit anerkannt und erfolgreich. In diesem Buch wird das bewährte Modell dargestellt. Die Behandlung depressiver Patienten und die Rolle der Emotionen in der Therapie werden ausführlich und konkret beschrieben. Im Anhang: zusätzliche Arbeitsmaterialien, Fragebogen, Listen und andere Diagnoseinstrumente sowie Forschungsarbeiten aus dem deutschsprachigen Raum. Ein umfassendes Standardwerk zur Depressionstherapie.<BR>"
            author = "Beck, Aaron T."
            //author = "Aaaron T. Beck, A. John Rush, Brian F. Shaw, Gary Emery"
            publisher = "Beltz"
            year = "2017"
            pages = "415"
            place = "Weinheim"
            language = "de"
        },
        BookResponseDTO("9783801726973").apply {
            requestedIsbn = "9783801726973"
            isbn = requestedIsbn
            title = "Soziale Kompetenzen fördern"
            combinedTitle = title
            edition = "2., überarb. Aufl."
            tableOfContentsUrl = "http://d-nb.info/107205048X/04"
            abstractUrl = "http://deposit.dnb.de/cgi-bin/dokserv?id=5288410&prov=M&dok_var=1&dok_ext=htm"
            abstract =
                "Angaben aus der Verlagsmeldung <br><br><h3> Soziale Kompetenzen fördern  / von Uwe Peter Kanning</h3><br><p>Soziale Kompetenzen sind in Personalabteilungen ein wichtiges Thema. Neben fachlichen Kompetenzen sind sie zu einer Schlüsselvariable der Personalauswahl und -platzierung geworden und finden darüber hinaus in Form von Führungskräfteseminaren große Beachtung in der Personalentwicklung. Der großen Popularität des Konzeptes steht jedoch nicht selten eine gewisse Konzeptionslosigkeit gegenüber, denn viele verstehen unter sozialen Kompetenzen etwas anderes. <BR>Vor dem Hintergrund psychologischer Theorien und Forschungsergebnisse bringt dieser Band Struktur in die Vielfalt des Themas. Die Neuauflage berücksichtigt dabei aktuelle Entwicklungen auf diesem Gebiet. Zunächst wird eine Abgrenzung grundlegender Begriffe vorgenommen, ehe die Entstehung sozial kompetenten Verhaltens sowie die Ursachen für sozial inkompetentes Verhalten im beruflichen Kontext diskutiert werden. Nach einer Darstellung unterschiedlicher Methoden zur Diagnose sozialer Kompetenzen geht es schließlich um die Frage, wie sich soziale Kompetenzen im Rahmen der Personalentwicklung fördern lassen. Fallbeispiele illustrieren, wie dabei vorgegangen werden kann.<BR>"
            author = "Kanning, Uwe Peter"
            //author = "Aaaron T. Beck, A. John Rush, Brian F. Shaw, Gary Emery"
            publisher = "Hogrefe"
            year = "2015"
            pages = "139"
            place = "Göttingen"
            series = "Praxis der Personalpsychologie"
            volume = "Bd. 10"
            language = "de"
        }
    )

    fun dataProvider() = Stream.of(
        BookData(
            "9783551551672",
            """
<?xml version="1.0" encoding="UTF-8"?>
<searchRetrieveResponse xmlns="http://www.loc.gov/zing/srw/"><version>1.1</version><numberOfRecords>1</numberOfRecords><records><record><recordSchema>MARC21-xml</recordSchema><recordPacking>xml</recordPacking><recordData><record xmlns="http://www.loc.gov/MARC21/slim" type="Bibliographic">
    <leader>00000nam a2200000 c 4500</leader>
    <controlfield tag="001">958577617</controlfield>
    <controlfield tag="003">DE-101</controlfield>
    <controlfield tag="005">20171203084040.0</controlfield>
    <controlfield tag="007">tu</controlfield>
    <controlfield tag="008">000301s1998    gw ||||| |||| 00||||ger  </controlfield>
    <datafield tag="015" ind1=" " ind2=" ">
      <subfield code="a">00,A15,0174</subfield>
      <subfield code="2">dnb</subfield>
    </datafield>
    <datafield tag="016" ind1="7" ind2=" ">
      <subfield code="2">DE-101</subfield>
      <subfield code="a">958577617</subfield>
    </datafield>
    <datafield tag="020" ind1=" " ind2=" ">
      <subfield code="a">9783551551672</subfield>
      <subfield code="c">Pp. : DM 26.00</subfield>
      <subfield code="9">978-3-551-55167-2</subfield>
    </datafield>
    <datafield tag="020" ind1=" " ind2=" ">
      <subfield code="a">3551551677</subfield>
      <subfield code="c">Pp. : DM 26.00</subfield>
      <subfield code="9">3-551-55167-7</subfield>
    </datafield>
    <datafield tag="035" ind1=" " ind2=" ">
      <subfield code="a">(DE-599)DNB958577617</subfield>
    </datafield>
    <datafield tag="035" ind1=" " ind2=" ">
      <subfield code="a">(OCoLC)246371547</subfield>
    </datafield>
    <datafield tag="040" ind1=" " ind2=" ">
      <subfield code="a">1240</subfield>
      <subfield code="b">ger</subfield>
      <subfield code="c">DE-101</subfield>
      <subfield code="d">9999</subfield>
    </datafield>
    <datafield tag="041" ind1=" " ind2=" ">
      <subfield code="a">ger</subfield>
      <subfield code="h">eng</subfield>
    </datafield>
    <datafield tag="044" ind1=" " ind2=" ">
      <subfield code="c">XA-DE</subfield>
    </datafield>
    <datafield tag="084" ind1=" " ind2=" ">
      <subfield code="a">07</subfield>
      <subfield code="a">K</subfield>
      <subfield code="q">DE-101</subfield>
      <subfield code="2">sdnb</subfield>
    </datafield>
    <datafield tag="090" ind1=" " ind2=" ">
      <subfield code="a">b</subfield>
    </datafield>
    <datafield tag="100" ind1="1" ind2=" ">
      <subfield code="0">(DE-588)122340469</subfield>
      <subfield code="0">http://d-nb.info/gnd/122340469</subfield>
      <subfield code="0">(DE-101)122340469</subfield>
      <subfield code="a">Rowling, J. K.</subfield>
      <subfield code="d">1965-</subfield>
      <subfield code="e">Verfasser</subfield>
      <subfield code="4">aut</subfield>
    </datafield>
    <datafield tag="245" ind1="1" ind2="0">
      <subfield code="a">Harry Potter und der Stein der Weisen</subfield>
      <subfield code="c">Joanne K. Rowling. Aus dem Engl. von Klaus Fritz</subfield>
    </datafield>
    <datafield tag="264" ind1=" " ind2="1">
      <subfield code="a">Hamburg</subfield>
      <subfield code="b">Carlsen</subfield>
      <subfield code="c">1998</subfield>
    </datafield>
    <datafield tag="300" ind1=" " ind2=" ">
      <subfield code="a">335 S.</subfield>
      <subfield code="c">22 cm</subfield>
    </datafield>
    <datafield tag="336" ind1=" " ind2=" ">
      <subfield code="a">Text</subfield>
      <subfield code="b">txt</subfield>
      <subfield code="2">rdacontent</subfield>
    </datafield>
    <datafield tag="337" ind1=" " ind2=" ">
      <subfield code="a">ohne Hilfsmittel zu benutzen</subfield>
      <subfield code="b">n</subfield>
      <subfield code="2">rdamedia</subfield>
    </datafield>
    <datafield tag="338" ind1=" " ind2=" ">
      <subfield code="a">Band</subfield>
      <subfield code="b">nc</subfield>
      <subfield code="2">rdacarrier</subfield>
    </datafield>
    <datafield tag="700" ind1="1" ind2="2">
      <subfield code="a">Rowling, J. K.</subfield>
      <subfield code="d">1965-</subfield>
      <subfield code="t">Harry Potter and the philosopher's stone</subfield>
      <subfield code="g">dt.</subfield>
    </datafield>
    <datafield tag="850" ind1=" " ind2=" ">
      <subfield code="a">DE-101a</subfield>
      <subfield code="a">DE-101b</subfield>
    </datafield>
    <datafield tag="925" ind1="r" ind2=" ">
      <subfield code="a">ra</subfield>
    </datafield>
  </record></recordData><recordPosition>1</recordPosition></record></records><nextRecordPosition>2</nextRecordPosition><echoedSearchRetrieveRequest><version>1.1</version><query>isbn=${'$'}9783551551672</query><xQuery xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:nil="true"/><recordSchema>MARC21-xml</recordSchema></echoedSearchRetrieveRequest><extraResponseData><accountOf xmlns="">Arbeitsgemeinschaft für Verhaltensmodifikation</accountOf></extraResponseData></searchRetrieveResponse>
            """.trimIndent()
        ),
        BookData(
            "9783868943238",
            """
<?xml version="1.0" encoding="UTF-8"?>
<searchRetrieveResponse xmlns="http://www.loc.gov/zing/srw/"><version>1.1</version><numberOfRecords>1</numberOfRecords><records><record><recordSchema>MARC21-xml</recordSchema><recordPacking>xml</recordPacking><recordData><record xmlns="http://www.loc.gov/MARC21/slim" type="Bibliographic">
    <leader>00000nam a2200000 c 4500</leader>
    <controlfield tag="001">1166686841</controlfield>
    <controlfield tag="003">DE-101</controlfield>
    <controlfield tag="005">20190220223806.0</controlfield>
    <controlfield tag="007">tu</controlfield>
    <controlfield tag="008">180911s2018    gw ||||| |||| 00||||ger  </controlfield>
    <datafield tag="015" ind1=" " ind2=" ">
      <subfield code="a">19,A09</subfield>
      <subfield code="2">dnb</subfield>
    </datafield>
    <datafield tag="016" ind1="7" ind2=" ">
      <subfield code="2">DE-101</subfield>
      <subfield code="a">1166686841</subfield>
    </datafield>
    <datafield tag="020" ind1=" " ind2=" ">
      <subfield code="a">9783868943238</subfield>
      <subfield code="c">Festeinband</subfield>
      <subfield code="9">978-3-86894-323-8</subfield>
    </datafield>
    <datafield tag="035" ind1=" " ind2=" ">
      <subfield code="a">(DE-599)DNB1166686841</subfield>
    </datafield>
    <datafield tag="040" ind1=" " ind2=" ">
      <subfield code="a">1230</subfield>
      <subfield code="b">ger</subfield>
      <subfield code="c">DE-101</subfield>
      <subfield code="d">9999</subfield>
      <subfield code="e">rda</subfield>
    </datafield>
    <datafield tag="041" ind1=" " ind2=" ">
      <subfield code="a">ger</subfield>
      <subfield code="h">eng</subfield>
    </datafield>
    <datafield tag="044" ind1=" " ind2=" ">
      <subfield code="c">XA-DE</subfield>
    </datafield>
    <datafield tag="082" ind1="0" ind2="4">
      <subfield code="8">2\u</subfield>
      <subfield code="a">150</subfield>
      <subfield code="q">DE-101</subfield>
      <subfield code="2">23/ger</subfield>
    </datafield>
    <datafield tag="083" ind1="0" ind2=" ">
      <subfield code="8">3\u</subfield>
      <subfield code="a">150</subfield>
      <subfield code="q">DE-101</subfield>
      <subfield code="2">22/ger</subfield>
    </datafield>
    <datafield tag="083" ind1="7" ind2=" ">
      <subfield code="a">150</subfield>
      <subfield code="q">DE-101</subfield>
      <subfield code="2">23sdnb</subfield>
    </datafield>
    <datafield tag="085" ind1=" " ind2=" ">
      <subfield code="8">2\u</subfield>
      <subfield code="b">150</subfield>
    </datafield>
    <datafield tag="085" ind1=" " ind2=" ">
      <subfield code="8">3\u</subfield>
      <subfield code="b">150</subfield>
    </datafield>
    <datafield tag="090" ind1=" " ind2=" ">
      <subfield code="a">b</subfield>
    </datafield>
    <datafield tag="100" ind1="1" ind2=" ">
      <subfield code="0">(DE-588)12087976X</subfield>
      <subfield code="0">http://d-nb.info/gnd/12087976X</subfield>
      <subfield code="0">(DE-101)12087976X</subfield>
      <subfield code="a">Gerrig, Richard J.</subfield>
      <subfield code="d">1959-</subfield>
      <subfield code="e">Verfasser</subfield>
      <subfield code="4">aut</subfield>
    </datafield>
    <datafield tag="240" ind1="1" ind2="0">
      <subfield code="a">Psychology and life</subfield>
    </datafield>
    <datafield tag="245" ind1="1" ind2="0">
      <subfield code="a">Psychologie</subfield>
      <subfield code="c">Richard J. Gerrig ; aus dem Amerikanischen von Andreas Klatt ; Tobias Dörfler, Jeanette Roos (Hrsg.)</subfield>
    </datafield>
    <datafield tag="250" ind1=" " ind2=" ">
      <subfield code="a">21., aktualisierte und erweiterte Auflage</subfield>
    </datafield>
    <datafield tag="259" ind1=" " ind2=" ">
      <subfield code="a">221</subfield>
    </datafield>
    <datafield tag="264" ind1=" " ind2="1">
      <subfield code="a">Hallbergmoos/Germany</subfield>
      <subfield code="b">Pearson</subfield>
      <subfield code="c">[2018]</subfield>
    </datafield>
    <datafield tag="300" ind1=" " ind2=" ">
      <subfield code="a">XXV, 834 Seiten</subfield>
      <subfield code="b">Illustrationen</subfield>
      <subfield code="c">28 cm</subfield>
    </datafield>
    <datafield tag="336" ind1=" " ind2=" ">
      <subfield code="a">Text</subfield>
      <subfield code="b">txt</subfield>
      <subfield code="2">rdacontent</subfield>
    </datafield>
    <datafield tag="337" ind1=" " ind2=" ">
      <subfield code="a">ohne Hilfsmittel zu benutzen</subfield>
      <subfield code="b">n</subfield>
      <subfield code="2">rdamedia</subfield>
    </datafield>
    <datafield tag="338" ind1=" " ind2=" ">
      <subfield code="a">Band</subfield>
      <subfield code="b">nc</subfield>
      <subfield code="2">rdacarrier</subfield>
    </datafield>
    <datafield tag="650" ind1=" " ind2="7">
      <subfield code="0">(DE-588)4047704-6</subfield>
      <subfield code="0">http://d-nb.info/gnd/4047704-6</subfield>
      <subfield code="0">(DE-101)040477045</subfield>
      <subfield code="a">Psychologie</subfield>
      <subfield code="2">gnd</subfield>
    </datafield>
    <datafield tag="650" ind1=" " ind2="7">
      <subfield code="0">(DE-588)4047704-6</subfield>
      <subfield code="0">http://d-nb.info/gnd/4047704-6</subfield>
      <subfield code="0">(DE-101)040477045</subfield>
      <subfield code="a">Psychologie</subfield>
      <subfield code="2">gnd</subfield>
    </datafield>
    <datafield tag="650" ind1=" " ind2="7">
      <subfield code="8">1\p</subfield>
      <subfield code="0">(DE-588)4047704-6</subfield>
      <subfield code="0">http://d-nb.info/gnd/4047704-6</subfield>
      <subfield code="0">(DE-101)040477045</subfield>
      <subfield code="a">Psychologie</subfield>
      <subfield code="2">gnd</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(Keywords)Wahrnehmung</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(Keywords)Kognition</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(Keywords)Methoden</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(Keywords)Psychologie</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(Keywords)Persönlichkeit</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Sozialpsychologie</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Zimbardo</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Psychologiestudium</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Kognitionspsychologie</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Philip Zimbardo</subfield>
    </datafield>
    <datafield tag="655" ind1=" " ind2="7">
      <subfield code="0">(DE-588)4123623-3</subfield>
      <subfield code="0">http://d-nb.info/gnd/4123623-3</subfield>
      <subfield code="0">(DE-101)041236238</subfield>
      <subfield code="a">Lehrbuch</subfield>
      <subfield code="2">gnd-content</subfield>
    </datafield>
    <datafield tag="655" ind1=" " ind2="7">
      <subfield code="a">Lehrbuch</subfield>
      <subfield code="2">gnd</subfield>
    </datafield>
    <datafield tag="689" ind1="0" ind2="0">
      <subfield code="0">(DE-588)4047704-6</subfield>
      <subfield code="0">http://d-nb.info/gnd/4047704-6</subfield>
      <subfield code="0">(DE-101)040477045</subfield>
      <subfield code="D">s</subfield>
      <subfield code="a">Psychologie</subfield>
    </datafield>
    <datafield tag="689" ind1="0" ind2=" ">
      <subfield code="5">DE-101</subfield>
      <subfield code="5">DE-101</subfield>
    </datafield>
    <datafield tag="689" ind1="1" ind2="0">
      <subfield code="0">(DE-588)4047704-6</subfield>
      <subfield code="0">http://d-nb.info/gnd/4047704-6</subfield>
      <subfield code="0">(DE-101)040477045</subfield>
      <subfield code="D">s</subfield>
      <subfield code="a">Psychologie</subfield>
    </datafield>
    <datafield tag="689" ind1="1" ind2="1">
      <subfield code="A">f</subfield>
      <subfield code="a">Lehrbuch</subfield>
    </datafield>
    <datafield tag="689" ind1="1" ind2=" ">
      <subfield code="5">DE-101</subfield>
      <subfield code="5">DE-101</subfield>
    </datafield>
    <datafield tag="850" ind1=" " ind2=" ">
      <subfield code="a">DE-101a</subfield>
      <subfield code="a">DE-101b</subfield>
    </datafield>
    <datafield tag="856" ind1="4" ind2="2">
      <subfield code="m">B:DE-101</subfield>
      <subfield code="q">application/pdf</subfield>
      <subfield code="u">http://d-nb.info/1166686841/04</subfield>
      <subfield code="3">Inhaltsverzeichnis</subfield>
    </datafield>
    <datafield tag="883" ind1="1" ind2=" ">
      <subfield code="8">1\p</subfield>
      <subfield code="a">maschinell aus Konkordanz gebildet</subfield>
      <subfield code="c">1</subfield>
      <subfield code="d">20190524</subfield>
      <subfield code="q">DE-101</subfield>
    </datafield>
    <datafield tag="925" ind1="r" ind2=" ">
      <subfield code="a">ra</subfield>
    </datafield>
  </record></recordData><recordPosition>1</recordPosition></record></records><nextRecordPosition>2</nextRecordPosition><echoedSearchRetrieveRequest><version>1.1</version><query>isbn=9783868943238</query><xQuery xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:nil="true"/><recordSchema>MARC21-xml</recordSchema></echoedSearchRetrieveRequest><extraResponseData><accountOf xmlns="">Arbeitsgemeinschaft für Verhaltensmodifikation</accountOf></extraResponseData></searchRetrieveResponse>
            """.trimIndent()
        ),
        BookData(
            "9783621285674",
            """
<?xml version="1.0" encoding="UTF-8"?>
<searchRetrieveResponse xmlns="http://www.loc.gov/zing/srw/"><version>1.1</version><numberOfRecords>1</numberOfRecords><records><record><recordSchema>MARC21-xml</recordSchema><recordPacking>xml</recordPacking><recordData><record xmlns="http://www.loc.gov/MARC21/slim" type="Bibliographic">
    <leader>00000pam a2200000 c 4500</leader>
    <controlfield tag="001">1127602284</controlfield>
    <controlfield tag="003">DE-101</controlfield>
    <controlfield tag="005">20180208003124.0</controlfield>
    <controlfield tag="007">tu</controlfield>
    <controlfield tag="008">170314s2017    gw ||||| |||| 00||||ger  </controlfield>
    <datafield tag="015" ind1=" " ind2=" ">
      <subfield code="a">18,A07</subfield>
      <subfield code="z">17,N12</subfield>
      <subfield code="2">dnb</subfield>
    </datafield>
    <datafield tag="016" ind1="7" ind2=" ">
      <subfield code="2">DE-101</subfield>
      <subfield code="a">1127602284</subfield>
    </datafield>
    <datafield tag="020" ind1=" " ind2=" ">
      <subfield code="a">9783621285674</subfield>
      <subfield code="c">Broschur : EUR 24.95 (DE), EUR 25.60 (AT), CHF 34.60 (freier Preis)</subfield>
      <subfield code="9">978-3-621-28567-4</subfield>
    </datafield>
    <datafield tag="020" ind1=" " ind2=" ">
      <subfield code="a">3621285679</subfield>
      <subfield code="9">3-621-28567-9</subfield>
    </datafield>
    <datafield tag="024" ind1="3" ind2=" ">
      <subfield code="a">9783621285674</subfield>
    </datafield>
    <datafield tag="028" ind1="5" ind2="2">
      <subfield code="a">Bestellnummer: 128567</subfield>
    </datafield>
    <datafield tag="035" ind1=" " ind2=" ">
      <subfield code="a">(DE-599)DNB1127602284</subfield>
    </datafield>
    <datafield tag="035" ind1=" " ind2=" ">
      <subfield code="a">(OCoLC)979558238</subfield>
    </datafield>
    <datafield tag="040" ind1=" " ind2=" ">
      <subfield code="a">1245</subfield>
      <subfield code="b">ger</subfield>
      <subfield code="c">DE-101</subfield>
      <subfield code="d">0292</subfield>
      <subfield code="e">rda</subfield>
    </datafield>
    <datafield tag="041" ind1=" " ind2=" ">
      <subfield code="a">ger</subfield>
      <subfield code="h">eng</subfield>
    </datafield>
    <datafield tag="044" ind1=" " ind2=" ">
      <subfield code="c">XA-DE-BW</subfield>
      <subfield code="c">XA-CH</subfield>
    </datafield>
    <datafield tag="082" ind1="0" ind2="4">
      <subfield code="8">3\u</subfield>
      <subfield code="a">616.85270651</subfield>
      <subfield code="q">DE-101</subfield>
      <subfield code="2">22/ger</subfield>
    </datafield>
    <datafield tag="083" ind1="7" ind2=" ">
      <subfield code="a">610</subfield>
      <subfield code="a">150</subfield>
      <subfield code="q">DE-101</subfield>
      <subfield code="2">23sdnb</subfield>
    </datafield>
    <datafield tag="085" ind1=" " ind2=" ">
      <subfield code="8">3\u</subfield>
      <subfield code="b">616.8527</subfield>
    </datafield>
    <datafield tag="085" ind1=" " ind2=" ">
      <subfield code="8">3\u</subfield>
      <subfield code="s">615.851</subfield>
    </datafield>
    <datafield tag="090" ind1=" " ind2=" ">
      <subfield code="a">b</subfield>
    </datafield>
    <datafield tag="100" ind1="1" ind2=" ">
      <subfield code="0">(DE-588)11917359X</subfield>
      <subfield code="0">http://d-nb.info/gnd/11917359X</subfield>
      <subfield code="0">(DE-101)11917359X</subfield>
      <subfield code="a">Beck, Aaron T.</subfield>
      <subfield code="d">1921-</subfield>
      <subfield code="e">Verfasser</subfield>
      <subfield code="4">aut</subfield>
    </datafield>
    <datafield tag="240" ind1="1" ind2="0">
      <subfield code="a">Cognitive therapy of depression</subfield>
    </datafield>
    <datafield tag="245" ind1="1" ind2="0">
      <subfield code="a">Kognitive Therapie der Depression</subfield>
      <subfield code="c">Aaaron T. Beck, A. John Rush, Brian F. Shaw, Gary Emery</subfield>
    </datafield>
    <datafield tag="250" ind1=" " ind2=" ">
      <subfield code="a">5., neu ausgestattete Auflage</subfield>
    </datafield>
    <datafield tag="259" ind1=" " ind2=" ">
      <subfield code="a">15</subfield>
    </datafield>
    <datafield tag="264" ind1=" " ind2="1">
      <subfield code="a">Weinheim</subfield>
      <subfield code="a">Basel</subfield>
      <subfield code="b">Beltz</subfield>
      <subfield code="c">2017</subfield>
    </datafield>
    <datafield tag="300" ind1=" " ind2=" ">
      <subfield code="a">415 Seiten</subfield>
      <subfield code="c">21 cm</subfield>
    </datafield>
    <datafield tag="336" ind1=" " ind2=" ">
      <subfield code="a">Text</subfield>
      <subfield code="b">txt</subfield>
      <subfield code="2">rdacontent</subfield>
    </datafield>
    <datafield tag="337" ind1=" " ind2=" ">
      <subfield code="a">ohne Hilfsmittel zu benutzen</subfield>
      <subfield code="b">n</subfield>
      <subfield code="2">rdamedia</subfield>
    </datafield>
    <datafield tag="338" ind1=" " ind2=" ">
      <subfield code="a">Band</subfield>
      <subfield code="b">nc</subfield>
      <subfield code="2">rdacarrier</subfield>
    </datafield>
    <datafield tag="500" ind1=" " ind2=" ">
      <subfield code="a">Der Name des 1. Verfassers sollte lauten: Aaron T. Beck</subfield>
    </datafield>
    <datafield tag="650" ind1=" " ind2="7">
      <subfield code="0">(DE-588)4011474-0</subfield>
      <subfield code="0">http://d-nb.info/gnd/4011474-0</subfield>
      <subfield code="0">(DE-101)040114740</subfield>
      <subfield code="a">Depression</subfield>
      <subfield code="2">gnd</subfield>
    </datafield>
    <datafield tag="650" ind1=" " ind2="7">
      <subfield code="0">(DE-588)4114250-0</subfield>
      <subfield code="0">http://d-nb.info/gnd/4114250-0</subfield>
      <subfield code="0">(DE-101)041142500</subfield>
      <subfield code="a">Kognitive Verhaltenstherapie</subfield>
      <subfield code="2">gnd</subfield>
    </datafield>
    <datafield tag="650" ind1=" " ind2="7">
      <subfield code="8">1\p</subfield>
      <subfield code="0">(DE-588)4011474-0</subfield>
      <subfield code="0">http://d-nb.info/gnd/4011474-0</subfield>
      <subfield code="0">(DE-101)040114740</subfield>
      <subfield code="a">Depression</subfield>
      <subfield code="2">gnd</subfield>
    </datafield>
    <datafield tag="650" ind1=" " ind2="7">
      <subfield code="8">2\p</subfield>
      <subfield code="0">(DE-588)4218754-0</subfield>
      <subfield code="0">http://d-nb.info/gnd/4218754-0</subfield>
      <subfield code="0">(DE-101)042187540</subfield>
      <subfield code="a">Ganzheitstherapie</subfield>
      <subfield code="2">gnd</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(Produktform)Paperback / softback</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Depression</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Kognitive Psychotherapie</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(Verlag)4: Beltz</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(VLB-WN)2534: Taschenbuch / Psychologie/Angewandte Psychologie</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(BISAC Subject Heading)PSY000000</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(Produktrabattgruppe)95R02: 2 = Fachbuch</subfield>
    </datafield>
    <datafield tag="689" ind1="0" ind2="0">
      <subfield code="0">(DE-588)4011474-0</subfield>
      <subfield code="0">http://d-nb.info/gnd/4011474-0</subfield>
      <subfield code="0">(DE-101)040114740</subfield>
      <subfield code="D">s</subfield>
      <subfield code="a">Depression</subfield>
    </datafield>
    <datafield tag="689" ind1="0" ind2="1">
      <subfield code="0">(DE-588)4114250-0</subfield>
      <subfield code="0">http://d-nb.info/gnd/4114250-0</subfield>
      <subfield code="0">(DE-101)041142500</subfield>
      <subfield code="D">s</subfield>
      <subfield code="a">Kognitive Verhaltenstherapie</subfield>
    </datafield>
    <datafield tag="689" ind1="0" ind2=" ">
      <subfield code="5">DE-101</subfield>
      <subfield code="5">DE-101</subfield>
    </datafield>
    <datafield tag="700" ind1="1" ind2=" ">
      <subfield code="0">(DE-588)135741440</subfield>
      <subfield code="0">http://d-nb.info/gnd/135741440</subfield>
      <subfield code="0">(DE-101)135741440</subfield>
      <subfield code="a">Rush, A. John</subfield>
      <subfield code="d">1942-</subfield>
      <subfield code="e">Verfasser</subfield>
      <subfield code="4">aut</subfield>
    </datafield>
    <datafield tag="700" ind1="1" ind2=" ">
      <subfield code="0">(DE-588)153483318</subfield>
      <subfield code="0">http://d-nb.info/gnd/153483318</subfield>
      <subfield code="0">(DE-101)153483318</subfield>
      <subfield code="a">Shaw, Brian F.</subfield>
      <subfield code="e">Verfasser</subfield>
      <subfield code="4">aut</subfield>
    </datafield>
    <datafield tag="700" ind1="1" ind2=" ">
      <subfield code="0">(DE-588)109941004</subfield>
      <subfield code="0">http://d-nb.info/gnd/109941004</subfield>
      <subfield code="0">(DE-101)109941004</subfield>
      <subfield code="a">Emery, Gary</subfield>
      <subfield code="e">Verfasser</subfield>
      <subfield code="4">aut</subfield>
    </datafield>
    <datafield tag="700" ind1="1" ind2=" ">
      <subfield code="a">Hautzinger, Martin</subfield>
      <subfield code="e">Herausgeber</subfield>
      <subfield code="4">edt</subfield>
    </datafield>
    <datafield tag="700" ind1="1" ind2=" ">
      <subfield code="a">Bronder, Gisela</subfield>
      <subfield code="e">Übersetzer</subfield>
      <subfield code="4">trl</subfield>
    </datafield>
    <datafield tag="700" ind1="1" ind2=" ">
      <subfield code="a">Stein, Brigitte</subfield>
      <subfield code="e">Übersetzer</subfield>
      <subfield code="4">trl</subfield>
    </datafield>
    <datafield tag="710" ind1="2" ind2=" ">
      <subfield code="a">The Guilford Press</subfield>
      <subfield code="e">Sonstige</subfield>
      <subfield code="4">oth</subfield>
    </datafield>
    <datafield tag="710" ind1="2" ind2=" ">
      <subfield code="0">(DE-588)1064929486</subfield>
      <subfield code="0">http://d-nb.info/gnd/1064929486</subfield>
      <subfield code="0">(DE-101)1064929486</subfield>
      <subfield code="a">Julius Beltz GmbH &amp; Co. KG</subfield>
      <subfield code="e">Verlag</subfield>
      <subfield code="4">pbl</subfield>
    </datafield>
    <datafield tag="776" ind1="0" ind2="8">
      <subfield code="i">Erscheint auch als</subfield>
      <subfield code="n">Online-Ausgabe</subfield>
      <subfield code="z">9783621285681</subfield>
    </datafield>
    <datafield tag="776" ind1="0" ind2="8">
      <subfield code="i">Erscheint auch als</subfield>
      <subfield code="n">Druck-Ausgabe</subfield>
      <subfield code="z">9783407220233</subfield>
    </datafield>
    <datafield tag="776" ind1="0" ind2="8">
      <subfield code="i">Erscheint auch als</subfield>
      <subfield code="n">Online-Ausgabe</subfield>
      <subfield code="z">9783621283991</subfield>
    </datafield>
    <datafield tag="776" ind1="0" ind2="8">
      <subfield code="i">Erscheint auch als</subfield>
      <subfield code="n">Online-Ausgabe</subfield>
      <subfield code="a">Beck, Aaron T., 1921-</subfield>
      <subfield code="t">Kognitive Therapie der Depression</subfield>
      <subfield code="d">Weinheim : Beltz, 2017</subfield>
      <subfield code="h">Online-Ressourcen, 415 Seiten</subfield>
      <subfield code="w">(DE-101)1135651582</subfield>
      <subfield code="b">5. Auflage</subfield>
    </datafield>
    <datafield tag="850" ind1=" " ind2=" ">
      <subfield code="a">DE-101a</subfield>
      <subfield code="a">DE-101b</subfield>
    </datafield>
    <datafield tag="856" ind1="4" ind2="2">
      <subfield code="m">X:MVB</subfield>
      <subfield code="q">text/html</subfield>
      <subfield code="u">http://deposit.dnb.de/cgi-bin/dokserv?id=9fe9bde43c444775be1b264cc8abab4a&amp;prov=M&amp;dok_var=1&amp;dok_ext=htm</subfield>
      <subfield code="3">Inhaltstext</subfield>
    </datafield>
    <datafield tag="856" ind1="4" ind2="2">
      <subfield code="m">X:MVB</subfield>
      <subfield code="u">http://www.beltz.de/de/nc/verlagsgruppe-beltz/gesamtprogramm.html?isbn=978-3-621-28567-4</subfield>
    </datafield>
    <datafield tag="856" ind1="4" ind2="2">
      <subfield code="m">B:DE-101</subfield>
      <subfield code="q">application/pdf</subfield>
      <subfield code="u">http://d-nb.info/1127602284/04</subfield>
      <subfield code="3">Inhaltsverzeichnis</subfield>
    </datafield>
    <datafield tag="883" ind1="1" ind2=" ">
      <subfield code="8">1\p</subfield>
      <subfield code="a">maschinell aus Konkordanz gebildet</subfield>
      <subfield code="c">1</subfield>
      <subfield code="d">20190524</subfield>
      <subfield code="q">DE-101</subfield>
    </datafield>
    <datafield tag="883" ind1="1" ind2=" ">
      <subfield code="8">2\p</subfield>
      <subfield code="a">maschinell aus Konkordanz gebildet</subfield>
      <subfield code="c">1</subfield>
      <subfield code="d">20190524</subfield>
      <subfield code="q">DE-101</subfield>
    </datafield>
    <datafield tag="925" ind1="r" ind2=" ">
      <subfield code="a">ra</subfield>
    </datafield>
    <datafield tag="926" ind1="1" ind2=" ">
      <subfield code="a">JM</subfield>
      <subfield code="o">93</subfield>
      <subfield code="q">Publisher</subfield>
      <subfield code="v">1.2</subfield>
      <subfield code="x">Psychologie</subfield>
    </datafield>
  </record></recordData><recordPosition>1</recordPosition></record></records><nextRecordPosition>2</nextRecordPosition><echoedSearchRetrieveRequest><version>1.1</version><query>isbn=9783621285674</query><xQuery xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:nil="true"/><recordSchema>MARC21-xml</recordSchema></echoedSearchRetrieveRequest><extraResponseData><accountOf xmlns="">Arbeitsgemeinschaft für Verhaltensmodifikation</accountOf></extraResponseData></searchRetrieveResponse>
            """.trimIndent()
        ),
        BookData(
            "9783801726973",
            """
<?xml version="1.0" encoding="UTF-8"?>
<searchRetrieveResponse xmlns="http://www.loc.gov/zing/srw/"><version>1.1</version><numberOfRecords>1</numberOfRecords><records><record><recordSchema>MARC21-xml</recordSchema><recordPacking>xml</recordPacking><recordData><record xmlns="http://www.loc.gov/MARC21/slim" type="Bibliographic">
    <leader>00000pam a2200000 c 4500</leader>
    <controlfield tag="001">107205048X</controlfield>
    <controlfield tag="003">DE-101</controlfield>
    <controlfield tag="005">20171202104834.0</controlfield>
    <controlfield tag="007">tu</controlfield>
    <controlfield tag="008">150608s2015    gw ||||| |||| 00||||ger  </controlfield>
    <datafield tag="015" ind1=" " ind2=" ">
      <subfield code="a">15,A26</subfield>
      <subfield code="z">15,N25</subfield>
      <subfield code="2">dnb</subfield>
    </datafield>
    <datafield tag="016" ind1="7" ind2=" ">
      <subfield code="2">DE-101</subfield>
      <subfield code="a">107205048X</subfield>
    </datafield>
    <datafield tag="020" ind1=" " ind2=" ">
      <subfield code="a">9783801726973</subfield>
      <subfield code="c">kart. : EUR 24.95 (DE), EUR 25.70 (AT), sfr 32.50 (freier Pr.)</subfield>
      <subfield code="9">978-3-8017-2697-3</subfield>
    </datafield>
    <datafield tag="024" ind1="3" ind2=" ">
      <subfield code="a">9783801726973</subfield>
    </datafield>
    <datafield tag="035" ind1=" " ind2=" ">
      <subfield code="a">(DE-599)DNB107205048X</subfield>
    </datafield>
    <datafield tag="035" ind1=" " ind2=" ">
      <subfield code="a">(OCoLC)911090520</subfield>
    </datafield>
    <datafield tag="035" ind1=" " ind2=" ">
      <subfield code="a">(OCoLC)911229584</subfield>
    </datafield>
    <datafield tag="040" ind1=" " ind2=" ">
      <subfield code="a">1245</subfield>
      <subfield code="b">ger</subfield>
      <subfield code="c">DE-101</subfield>
      <subfield code="d">9999</subfield>
    </datafield>
    <datafield tag="041" ind1=" " ind2=" ">
      <subfield code="a">ger</subfield>
    </datafield>
    <datafield tag="044" ind1=" " ind2=" ">
      <subfield code="c">XA-DE-NI</subfield>
      <subfield code="c">XA-CH</subfield>
      <subfield code="c">XA-AT</subfield>
    </datafield>
    <datafield tag="082" ind1="0" ind2="4">
      <subfield code="8">1\u</subfield>
      <subfield code="a">658.3124</subfield>
      <subfield code="q">DE-101</subfield>
      <subfield code="2">22/ger</subfield>
    </datafield>
    <datafield tag="083" ind1="7" ind2=" ">
      <subfield code="a">650</subfield>
      <subfield code="a">150</subfield>
      <subfield code="q">DE-101</subfield>
      <subfield code="2">23sdnb</subfield>
    </datafield>
    <datafield tag="085" ind1=" " ind2=" ">
      <subfield code="8">1\u</subfield>
      <subfield code="b">658.3124</subfield>
    </datafield>
    <datafield tag="090" ind1=" " ind2=" ">
      <subfield code="a">b</subfield>
    </datafield>
    <datafield tag="100" ind1="1" ind2=" ">
      <subfield code="0">(DE-588)115502653</subfield>
      <subfield code="0">http://d-nb.info/gnd/115502653</subfield>
      <subfield code="0">(DE-101)115502653</subfield>
      <subfield code="a">Kanning, Uwe Peter</subfield>
      <subfield code="d">1966-</subfield>
      <subfield code="e">Verfasser</subfield>
      <subfield code="4">aut</subfield>
    </datafield>
    <datafield tag="245" ind1="1" ind2="0">
      <subfield code="a">Soziale Kompetenzen fördern</subfield>
      <subfield code="c">von Uwe Peter Kanning</subfield>
    </datafield>
    <datafield tag="250" ind1=" " ind2=" ">
      <subfield code="a">2., überarb. Aufl.</subfield>
    </datafield>
    <datafield tag="259" ind1=" " ind2=" ">
      <subfield code="a">12</subfield>
    </datafield>
    <datafield tag="264" ind1=" " ind2="1">
      <subfield code="a">Göttingen</subfield>
      <subfield code="a">Bern</subfield>
      <subfield code="a">Wien</subfield>
      <subfield code="a">Paris</subfield>
      <subfield code="a">Oxford</subfield>
      <subfield code="a">Prag</subfield>
      <subfield code="a">Toronto</subfield>
      <subfield code="a">Boston, Mass.</subfield>
      <subfield code="a">Amsterdam</subfield>
      <subfield code="a">Kopenhagen</subfield>
      <subfield code="a">Stockholm</subfield>
      <subfield code="a">Florenz</subfield>
      <subfield code="a">Helsinki</subfield>
      <subfield code="a">São Paulo</subfield>
      <subfield code="b">Hogrefe</subfield>
      <subfield code="c">2015</subfield>
    </datafield>
    <datafield tag="300" ind1=" " ind2=" ">
      <subfield code="a">VI, 139 S.</subfield>
      <subfield code="b">Ill., graph. Darst.</subfield>
      <subfield code="c">24 cm</subfield>
      <subfield code="e">Beil. ([4] S.)</subfield>
    </datafield>
    <datafield tag="336" ind1=" " ind2=" ">
      <subfield code="a">Text</subfield>
      <subfield code="b">txt</subfield>
      <subfield code="2">rdacontent</subfield>
    </datafield>
    <datafield tag="337" ind1=" " ind2=" ">
      <subfield code="a">ohne Hilfsmittel zu benutzen</subfield>
      <subfield code="b">n</subfield>
      <subfield code="2">rdamedia</subfield>
    </datafield>
    <datafield tag="338" ind1=" " ind2=" ">
      <subfield code="a">Band</subfield>
      <subfield code="b">nc</subfield>
      <subfield code="2">rdacarrier</subfield>
    </datafield>
    <datafield tag="490" ind1="1" ind2=" ">
      <subfield code="a">Praxis der Personalpsychologie</subfield>
      <subfield code="v">Bd. 10</subfield>
    </datafield>
    <datafield tag="500" ind1=" " ind2=" ">
      <subfield code="a">Literaturangaben</subfield>
    </datafield>
    <datafield tag="650" ind1=" " ind2="7">
      <subfield code="0">(DE-588)4121465-1</subfield>
      <subfield code="0">http://d-nb.info/gnd/4121465-1</subfield>
      <subfield code="0">(DE-101)04121465X</subfield>
      <subfield code="a">Personalentwicklung</subfield>
      <subfield code="2">gnd</subfield>
    </datafield>
    <datafield tag="650" ind1=" " ind2="7">
      <subfield code="0">(DE-588)4077606-2</subfield>
      <subfield code="0">http://d-nb.info/gnd/4077606-2</subfield>
      <subfield code="0">(DE-101)040776069</subfield>
      <subfield code="a">Sozialkompetenz</subfield>
      <subfield code="2">gnd</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(Produktform)Book</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(Produktform (spezifisch))Unsewn / adhesive bound</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Personalentwicklung</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Personalmanagement</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">soft skills</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Führungskräftetraining</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Coaching</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Sozialkompetenz</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">Kompetenzen</subfield>
    </datafield>
    <datafield tag="653" ind1=" " ind2=" ">
      <subfield code="a">(VLB-WN)1530: Hardcover, Softcover / Psychologie</subfield>
    </datafield>
    <datafield tag="689" ind1="0" ind2="0">
      <subfield code="0">(DE-588)4121465-1</subfield>
      <subfield code="0">http://d-nb.info/gnd/4121465-1</subfield>
      <subfield code="0">(DE-101)04121465X</subfield>
      <subfield code="D">s</subfield>
      <subfield code="a">Personalentwicklung</subfield>
    </datafield>
    <datafield tag="689" ind1="0" ind2="1">
      <subfield code="0">(DE-588)4077606-2</subfield>
      <subfield code="0">http://d-nb.info/gnd/4077606-2</subfield>
      <subfield code="0">(DE-101)040776069</subfield>
      <subfield code="D">s</subfield>
      <subfield code="a">Sozialkompetenz</subfield>
    </datafield>
    <datafield tag="689" ind1="0" ind2=" ">
      <subfield code="5">DE-101</subfield>
      <subfield code="5">DE-101</subfield>
    </datafield>
    <datafield tag="710" ind1="2" ind2=" ">
      <subfield code="0">(DE-588)5508760-7</subfield>
      <subfield code="0">http://d-nb.info/gnd/5508760-7</subfield>
      <subfield code="0">(DE-101)961083298</subfield>
      <subfield code="a">Hogrefe-Verlag</subfield>
      <subfield code="e">Verlag</subfield>
      <subfield code="4">pbl</subfield>
    </datafield>
    <datafield tag="780" ind1="0" ind2="0">
      <subfield code="i">1. Aufl. u.d.T.:</subfield>
      <subfield code="a">Kanning, Uwe Peter, 1966-</subfield>
      <subfield code="t">Soziale Kompetenzen</subfield>
      <subfield code="d">Göttingen ; Bern ; Wien ; Toronto ; Seattle ; Oxford ; Prag : Hogrefe, 2005</subfield>
      <subfield code="h">VI, 96 S., graph. Darst.</subfield>
      <subfield code="w">(DE-101)975661612</subfield>
    </datafield>
    <datafield tag="830" ind1=" " ind2="0">
      <subfield code="a">Praxis der Personalpsychologie</subfield>
      <subfield code="v">Bd. 10</subfield>
      <subfield code="w">(DE-101)025378066</subfield>
      <subfield code="w">(DE-600)2128649-8</subfield>
      <subfield code="9">210</subfield>
      <subfield code="7">as</subfield>
    </datafield>
    <datafield tag="850" ind1=" " ind2=" ">
      <subfield code="a">DE-101a</subfield>
      <subfield code="a">DE-101b</subfield>
    </datafield>
    <datafield tag="856" ind1="4" ind2="2">
      <subfield code="m">B:DE-101</subfield>
      <subfield code="q">application/pdf</subfield>
      <subfield code="u">http://d-nb.info/107205048X/04</subfield>
      <subfield code="3">Inhaltsverzeichnis</subfield>
    </datafield>
    <datafield tag="856" ind1="4" ind2="2">
      <subfield code="m">X:MVB</subfield>
      <subfield code="q">text/html</subfield>
      <subfield code="u">http://deposit.dnb.de/cgi-bin/dokserv?id=5288410&amp;prov=M&amp;dok_var=1&amp;dok_ext=htm</subfield>
      <subfield code="3">Inhaltstext</subfield>
    </datafield>
    <datafield tag="925" ind1="r" ind2=" ">
      <subfield code="a">ra</subfield>
    </datafield>
    <datafield tag="926" ind1="1" ind2=" ">
      <subfield code="a">JM</subfield>
      <subfield code="o">93</subfield>
      <subfield code="q">WGSneuMapping</subfield>
      <subfield code="v">1.0</subfield>
      <subfield code="x">Psychologie</subfield>
    </datafield>
  </record></recordData><recordPosition>1</recordPosition></record></records><nextRecordPosition>2</nextRecordPosition><echoedSearchRetrieveRequest><version>1.1</version><query>isbn=9783801726973</query><xQuery xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:nil="true"/><recordSchema>MARC21-xml</recordSchema></echoedSearchRetrieveRequest><extraResponseData><accountOf xmlns="">Arbeitsgemeinschaft für Verhaltensmodifikation</accountOf></extraResponseData></searchRetrieveResponse>
            """.trimIndent()
        )
        ,
        BookData(
            "dummy",
            """
                dummy
            """.trimIndent()
        )
    )

    data class BookData(val isbn: String, val data: String)
}