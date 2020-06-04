## QuickStart
* Download or Clone Code from https://github.com/balamuru/name-coding

* Build the executable
* ```mvn install```
* A fat jar ```name-coding-cli-<version>-jar-with-dependencies.jar```containing all the library dependencies will be produced at the local m2 repository. 
eg
```
vinayb@carbon ~/.m2/repository/com/vgb $ tree  --prune -hP *.jar
.
├── [4.0K]  name-coding-cli
│   └── [4.0K]  1.0-SNAPSHOT
│       ├── [5.1K]  name-coding-cli-1.0-SNAPSHOT.jar //CLI with no dependencies (mvn can be configured not to produce this)
│       └── [5.4M]  name-coding-cli-1.0-SNAPSHOT-jar-with-dependencies.jar //The fat jar application will all dependancies included
└── [4.0K]  name-coding-lib
    └── [4.0K]  1.0-SNAPSHOT 
        └── [ 11K]  name-coding-lib-1.0-SNAPSHOT.jar //library that can be re-used by other applications


```

* Alternatively, use CI/CD Build
    * Running on https://codefresh.io/
    * Can be configured to deliver jars to artifacts if needed
![algorithm](docs/codefresh-capture.png)

### Usage
**Syntax**
```
usage: java -jar <cli-jar-name>.jar
    --file <arg>   fully qualified path to the names input file
```

**Example**
```
$ java -jar name-coding-cli-1.0-SNAPSHOT-jar-with-dependencies.jar --file /home/vinayb/Downloads/sample-large.txt 
Input file: /home/vinayb/Downloads/sample-large.txt
Total score: 871198282
```
