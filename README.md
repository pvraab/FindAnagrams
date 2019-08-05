# Find Anagrams

## Overview

This is a Java application to look for anagrams in a dictionary file. This app uses a HashMap with a lowercase sorted string for each word as the key. Therefore, a set of anagram matches can be quickly found for a particular word by using the hash key. Also, the hash table can be searched to get sets of anagrams of a particular count.

### GitHub Access

- [GitHub Repository](https://github.com/pvraab/FindAnagrams/)

## Get Started

- Navigate to the git hub repository and clone or download the application and extract it into a directory.
- cd into the application directory.
- You need a Java 8 JRE or JDK to run this application.
- The following files are included:
  - FindAnagrams.java
    - This is the Java class file for the application.  The FindAnagrams.zip described below contains the full Eclipse project.
  - FindAnagrams.jar
    - This is the executable jar file for the application. To run it type:
      - $ java -jar FindAnagrams.jar
  - dictionary.txt.gz
    - A gzipped file containing a dictionary of words to use in the application as a reference set for anagram searches.
  - FindAnagrams.zip
    - This file contains a zipped copy of the Eclipse project including Java source code and an Ant build.xml file as eclipse-workspace.xml. You can extract this zip file into your eclipse workspace and build and run the application from there.
- The dictionary.txt.gz file should be in the same directory as the one from which you execute the FindAnagrams.jar.

## Sample I/O

- To run the program:

```
$ java -jar FindAnagrams.jar

Anagram methods
Enter 1 - to get anagram list == value
Enter 2 - to get the maximum anagram list
Enter 3 - to get anagrams for a word
Enter 4 - to get dictionary stats
Enter 5 - to exit
```

- The application displays a basic command line interface (CLI ). There are five options.
- Option 1 will return a list of all anagram sets from the dictionary which have an array size equal to value.

```
Anagram methods
Enter 1 - to get anagram list == value
Enter 2 - to get the maximum anagram list
Enter 3 - to get anagrams for a word
Enter 4 - to get dictionary stats
Enter 5 - to exit
1

Enter value
8

[arist, astir, sitar, stair, stria, tarsi, tisar, Trias]
[laster, lastre, rastle, relast, resalt, salter, slater, stelar]
[easting, gainset, Genista, genista, ingesta, seating, signate, teasing]
[alem, alme, lame, leam, Male, male, meal, mela]
[ante, Aten, etna, Nate, neat, taen, tane, tean]
[aril, lair, Lari, lari, liar, lira, rail, rial]
[acrolein, arecolin, Caroline, caroline, colinear, Cornelia, creolian, Lonicera]
[leapt, palet, patel, pelta, petal, plate, pleat, tepal]
```

- Option 2 will return the list of anagrams with a maximum array length for the dictionary used.

```
Anagram methods
Enter 1 - to get anagram list == value
Enter 2 - to get the maximum anagram list
Enter 3 - to get anagrams for a word
Enter 4 - to get dictionary stats
Enter 5 - to exit
2

[angor, argon, goran, grano, groan, nagor, Orang, orang, organ, rogan, Ronga]
```

- Option 3 will query the user for a word and then search for all matching anagrams to that word in the dictionary.

```
Anagram methods
Enter 1 - to get anagram list == value
Enter 2 - to get the maximum anagram list
Enter 3 - to get anagrams for a word
Enter 4 - to get dictionary stats
Enter 5 - to exit
3

Enter word
race

[Acer,acre,care,crea]
```

- Option 4 will display statistics about the dictionary.

```
Anagram methods
Enter 1 - to get anagram list == value
Enter 2 - to get the maximum anagram list
Enter 3 - to get anagrams for a word
Enter 4 - to get dictionary stats
Enter 5 - to exit
4

Dictionary statistics
Word count = 235886
Minimum word length = 1
Maximum word length = 24
Median = 9
Mean = 9
```

- Option 5 will exit the application.

```
Anagram methods
Enter 1 - to get anagram list == value
Enter 2 - to get the maximum anagram list
Enter 3 - to get anagrams for a word
Enter 4 - to get dictionary stats
Enter 5 - to exit
5

Exit
```

