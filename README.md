# jLSLauncher - Livestreamer launching utility
### Download

Eine vorkompilierte `.jar`-Datei kann hier heruntergeladen werden: https://dev.ixab.de/da/jLSLauncher/raw/master/out/artifacts/jLSLauncher_jar/jLSLauncher.jar

### Benötigte Software

* Java (mindestens v7) - https://www.java.com/de/download/
* Livestreamer (Hinweise weiter unten)

### Installation von Livestreamer (Windows)

1. Livestreamer-Download für Windows: http://docs.livestreamer.io/install.html#zip-archive
2. Das zip-Archiv in einen beliebigen Pfad entpacken.

### Installation von Livestreamer (Linux)

Installation von Livestreamer unter Linux sollte mit dem gängigen Paketmanager funktionieren.  
Alternativ hier vorbeischauen: http://docs.livestreamer.io/install.html

### Installation von jLSLauncher

`jLSLauncher.jar` benötigt keine Installation und ist direkt ausführbar.  
Das Programm kann nach belieben verschoben oder kopiert werden.  
Bitte beachten, dass ggf. die Datei `config.dat` auch verschoben werden muss.

### Benutzung von jLSLauncher

Doppelklick `jLSLauncher.jar`.  
Sollte keine Datei-Assoziierung für `*.jar` Dateien im System hinterlegt sein, muss das Programm
über die Konsole/Shell mit folgendem Befehl gestartet werden:
* Windows: `java -jar X:\Pfad\zu\jLSLauncher.jar`
* Linux: `java -jar /pfad/zu/jLSLauncher.jar`

Beim ersten Start von jLSLauncher öffnet sich zunächst nur ein *Datei öffnen*-Dialog.  
Hier bitte einfach die zuvor entpackte `livestreamer.exe` auswählen. Rest sollte selbsterklärend sein.

### Bug Reports

Bevorzugt bitte den Issue-Tracker nutzen: https://dev.ixab.de/da/jLSLauncher/issues  
Alternativ Bug Reports bitte per Email an da-bugreport@ixab.de

Für detaillierte Fehlermeldungen bitte das Programm über die Kommandozeile starten.
