[![Codefresh build status]( https://g.codefresh.io/api/badges/pipeline/balamuru/NameCodingPipeline%2Fname-coding?type=cf-1)]( https%3A%2F%2Fg.codefresh.io%2Fpublic%2Faccounts%2Fbalamuru%2Fpipelines%2Fnew%2F5ed76e90ccb58a9eeea8392c)

# name-coding
TODO: documentation

## Problem Statement
Create a command line utility that will compute a score for a list of first names.
The list of names will be provided as a text file. The full path to the names file will be specified as a command line argument. The names file will contain a single line of quoted, comma-separated names. A small sample of data can be found at the end of this document and a full sample file (names.txt) is attached.
To score a list of names, you must sort it alphabetically and sum the individual scores for all the names. To score a name, sum the alphabetical value of each letter (A=1, B=2, C=3, etc...) and multiply the sum by the name’s position in the list (1-based).

For example, when the sample data below is sorted into alphabetical order, LINDA, which is worth 12 + 9 + 14 + 4 + 1 = 40, is the 4th name in the list. So, LINDA would obtain a score of 40 x 4 = 160. The correct score for the entire list is 3194

```
"MARY","PATRICIA","LINDA","BARBARA","VINCENZO","SHON","LYNWOOD","JERE","HAI"
```

## Algorithm 
![](docs/algorithm.png)


## Assumptions
* All Data is in a single text line 
* No non-alphanumerics in the data set (apart fro whitespace, comma and quotes)
* Each name entry is prefixed and suffixed by ```"```

## Libs Used
