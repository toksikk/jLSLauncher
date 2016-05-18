# jLSLauncher - Livestreamer launching utility
### Download

A pre-compiled `.jar`-file can be downloaded here: https://dev.ixab.de/da/jLSLauncher/raw/master/out/artifacts/jLSLauncher_jar/jLSLauncher.jar

### Required Software

* Java (minimum required is v7) - https://www.java.com/de/download/
* Livestreamer

### Installation of Livestreamer (Windows)

1. Download Livestreamer for Windows: http://docs.livestreamer.io/install.html#zip-archive
2. Extract the Zip-File to a folder of your choice.

### Installation of Livestreamer (Linux)

Please use your distribution's packet manager. 
Optionally you can download Livestreamer here: http://docs.livestreamer.io/install.html

### Installation von jLSLauncher

`jLSLauncher.jar` does not need an installation and can be executed as is.  
You can move or copy this tool to any other location you like.  
The configuration is stored in a file named `config.json` which, if present, has to be moved to the same location.

### Usage of jLSLauncher

Double click `jLSLauncher.jar`.
If there is no program associated to launching `*.jar` files, jLSLauncher has to be launched via console/shell with the following commands:
* Windows: `java -jar X:\Pfad\zu\jLSLauncher.jar`
* Linux: `java -jar /pfad/zu/jLSLauncher.jar`

At the first start of jLSLauncher a *Open file* dialog is displayed. You'll have to point this to the previously extracted `livestreamer.exe` or `livestreamer` binary file on Linux.

### Bug Reports

Please use the Issue-Tracker at: https://dev.ixab.de/da/jLSLauncher/issues
Alternative method is to write a mail to da-bugreport@ixab.de

For detailed debug output, please start jLSLauncher via console or shell.