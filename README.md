Softwaretechnologie: Java 2

# Übung 5


## a) Repository klonen und Branch erstellen

Klonen Sie dieses Repository direkt in Eclipse und importieren Sie das Projekt. Legen Sie einen neuen Branch an, den Sie nach Ihrem GitHub-Benutzernamen benennen.

## b) Aufgabe 1

Im Projekt finden Sie eine Klasse `Document`, die eine Textdatei einlesen kann, und zwar über die (statische) Methods `Document.loadFromFile()`. Sie finden außerdem eine Datei `data/dracula.txt`, wobei es sich um den Text von Bram Stokers' *Dracula* handelt.

Machen Sie zunächstmal die Klasse `Document` iterierbar, so dass sie über die Tokens im Dokument iteriert, die jeweils als Strings repräsentiert werden (d.h. die Klasse `Document` sollte das Interface `Iterable<String>` implementieren). Zur Erkennung einzelner Tokens können Sie auf die Klasse `StringTokenizer` zurückgreifen, die ebenfalls zum  `java.util` package gehört ([javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/StringTokenizer.html)). 

Fügen Sie dann in der `main`-Methode eine Schleife ein, so dass die Tokens des Textes ausgegeben werden.

## c) Aufgabe 2

Erstellen Sie eine neue Klasse `SkipIterator<T> implements Iterator<T>`. Diese bekommt bei Initialisierung einen anderen Iterator übergeben (den Basisiterator), und liefert dann eine Teilmenge der Elemente des Basisiterators zurück. D.h. der SkipIterator überspringt eine Anzahl an Elementen des Basisiterators (hence the name). Die Anzahl der zu überspringenden Elemente *n* sollte ebenfalls ein Argument für den Konstruktor sein.

Angewendet auf den Text, würde der SkipIterator bei *n=2* also jeweils zwei Tokens überspringen und " ", "Bram ", "colophon] ", "GROSSET ", "_Publishers_ ", "in ", ... zurückgeben. Es ist als Lösung dieser Aufgabe auch okay, wenn Ihr SkipIterator die folgende Sequenz liefert: "_by_ ", "[Illustration: ", "YORK ", "DUNLAP ", "1897, ", "United  ", "America, ", also das erste Element verschluckt und dann korrekt weiterarbeitet. Siehe dazu auch Hinweis 2.

*Hinweis 1*: Eine sicher funktionierende, aber sehr unschöne, Lösung wäre den Basisiterator vollständig laufen zu lassen, die Elemente in einem Array zu speichern, und dann einen neuen Iterator über das Array laufen zu lassen. Diese Lösung ist nicht nur unschön, sondern auch deutlich langsamer als es sein müsste, weil das Erzeugen des Arrays eigentlich nicht nötig ist. Versuchen Sie das Problem zu lösen, indem Ihr Iterator tatsächlich parallel zum Basisiterator läuft.

*Hinweis 2*: Wenn Ihr SkipIterator das erste Token überspringt ist das in diesem Fall nicht schlimm (weil es whitespace ist), sollte aber natürlich im Allgemeinen nicht passieren. Sehen Sie einen Weg das zu reparieren?

## d) Commit und Push
Committen Sie alle Ihre Änderungen am Quellcode, und pushen Sie den neuen Branch auf das remote namens `origin` (= GitHub). 