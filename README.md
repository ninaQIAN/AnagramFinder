# AnagramFinder
small test to find a word's anagram from a given dictionary


Task 1. AnagramFinder (folder):
    1). Files
        AnagramFinderBasic.java (basic version)
        AnagramFinderAdvanced.java (enhanced)
        dictionary.txt

    2). Environment:
        java version "1.8.0_40"
        javac 1.8.0_40

    3). Excution:
        run "javac AnagramFinderBasic.java"
        run "java AnagramFinderBasic dictionary.txt"

        run "javac AnagramFinderAdvanced.java"
        run "java AnagramFinderAdvanced dictionary.txt"


Task 2. DictionaryDemo (Java Project)
    1) Feature:
        a. find a word's anagrams from dictionary and output result in a list
        b. search a word by at least 3 letters from dictionary and output result a list
        c. output dictionary word count by ninitial alphabet
        d. output dictionary word count by word size
        e. output filePath
        f. output dictionary word count
        g. change dictionary by resetting file path
        h. error handling
        i. test functions

    2) project structure:
        -DictionaryDemo
            -src/main/java
                -project.dictionarydemo.demo
                    -DictionaryDemo.java
                -project.dictionarydemo.api
                    -AnagramFinder.java
                    -Dictionary.java
                    -Search.java
                -project.dictionarydemo.util
                    -Constants.java
            -src/main/resources
            -src/test/java
                -com.dictionarydemo.test
                    -TestRunner.java
                -com.dictionarydemo.testcase
                    -AnagramFinderTest.java
                    -DictionaryTest.java
                    -SearchTest.java
            -src/test/resources
            -src/resources
                -Dictionary.txt
                -newDict.txt
        -DictionaryDemo.jar
        -DictionaryDemoTest.jar

    3) Execution:
        a. Run in Eclipse
            i) source main class: DictionaryDemo.java
           ii) test main class: TestRunner.java
        b. Run in command line:
            i) go to the directory where runnable jars are located
           ii) run "java -jar DictionaryDemo.jar" (default dictionary file path is ./src/resources/dictionary.txt)
          iii) run "java -jar DictionaryDemoTest.jar"





