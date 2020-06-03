
# name-coding
## Build Status (hosted on https://codefresh.io/)
[![Codefresh build status]( https://g.codefresh.io/api/badges/pipeline/balamuru/NameCodingPipeline%2Fname-coding?type=cf-1)]( https%3A%2F%2Fg.codefresh.io%2Fpublic%2Faccounts%2Fbalamuru%2Fpipelines%2Fnew%2F5ed76e90ccb58a9eeea8392c)


## Problem Statement
_Create a command line utility that will compute a score for a list of first names.
The list of names will be provided as a text file. The full path to the names file will be specified as a command line argument. The names file will contain a single line of quoted, comma-separated names. A small sample of data can be found at the end of this document and a full sample file (names.txt) is attached.
To score a list of names, you must sort it alphabetically and sum the individual scores for all the names. To score a name, sum the alphabetical value of each letter (A=1, B=2, C=3, etc...) and multiply the sum by the name’s position in the list (1-based)._

_For example, when the sample data below is sorted into alphabetical order, LINDA, which is worth 12 + 9 + 14 + 4 + 1 = 40, is the 4th name in the list. So, LINDA would obtain a score of 40 x 4 = 160. The correct score for the entire list is 3194_

```
"MARY","PATRICIA","LINDA","BARBARA","VINCENZO","SHON","LYNWOOD","JERE","HAI"
```
_**Future Requirements**_
* _Another department will want to use this utility as well, but they have a much more complex name scoring algorithm._
* _This scoring feature will be added to the company's intranet web-app, allowing employees to upload and score files from their desktop._
* _The company will be switching from first names only to both first and last names in the file._

## Requirements extracted from Problem Statement (and the chosen approach)
* Command line utility that accepts a single command line argument describing the fully qualified path to the input file
    * Fat Jar that can be executed 
    * Apache CLI to accept the ```--file``` argument
* Print the total ranked score to the console
    * Implemented in CLI
* Should be flexible enough to accomodate other scoring implementations, data structures etc 
    * interface driven design so all implementations can be switched out
    * 2 module approach
        * ```name-coding-lib``` contains the core services
        * ```name-coding-cli``` contains a CLI application that uses ```name-coding-lib``` and wired up with Spring 
* Should be flexible enough to be integrated into other apps and other sources of data
    * The input is a URL, so as long as the input text can be expressed as an URL, the library should work
* Should be flexible to support first and last names
    * The Pattern Delimiters and ReaderService are all interface driven and can support alternate implementation         


## Assumptions
* All Data is in a single text line 
* No non-alphanumerics in the data set (apart fro whitespace, comma and quotes)
* Each name entry is prefixed and suffixed by ```"```


## Algorithm 
![](docs/algorithm.png)

## Libs Used
* JUnit
* Apache Commons CLI
* Spring core


