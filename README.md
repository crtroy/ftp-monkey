# FTP Monkey

FTP client written in Java

## Overview

Built using JavaFX and SceneBuilder following an MVC design pattern.

## Getting Started

### Requirements

* Java 1.8.0_131
* IntelliJ 2017.1.5

### Installation

You can build / run this project in IntelliJ or run the JAR executable.

#### Using IntelliJ
1. Clone this repository `git clone https://github.com/danieldn/ftp-monkey-proto.git`
2. Launch IntelliJ | File Open | Select ftp-monkey-proto directory
3. Run


This project requires the Apache Commons Net library. You can manually install this.

1. Download and unpack package
http://mirrors.koehn.com/apache//commons/net/binaries/commons-net-3.6-bin.zip
2. In IntelliJ | File | Project Structure | Modules | Dependencies tab | + | JARs or Directories | navigate to directory containing target jar files
3. Leave the 'Export' box uncheck | Apply | Ok

Running junit tests in IntelliJ.

1. Download and add the listed jars to project structure following the steps above: https://mvnrepository.com/artifact/junit/junit/4.12 and https://mvnrepository.com/artifact/org.hamcrest/hamcrest-core/1.3
2. In IntelliJ | Run | Edit Configs | + | JUnit 
3. Test Kind: All in directory | Directory: {path_to_src_file} | Use classpath of module: ftp-monkey | JRE: 1.8 SDK (Default)
4. Apply / OK and then click run button

#### Using JAR Executable

Clone repo and navigate to repo directory. Run the following command:

`java -jar out/artifacts/ftp_monkey_proto_jar/ftp-monkey-proto.jar`

