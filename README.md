# javaone_bof7811
Sources for BOF7811 of JavaOne 2015 Annotation Processing 101

This project contains the presentation and the sources for the demoed project during that presentation.
The presentation can be found at [here](/presentation/BOF7811 - Annotation Processing 101.key)
## Usage
### With installed gradle
    ./gradle clean build

### Without installed gradle
    ./gradlew clean build

## Result
There's two results to look for.
### Output in the Console
    ... truncated
    :processor:build
    warning: org.annotationprocessing.usage.JavaOne
    warning: @org.annotationprocessing.defintion.MyName(firstName=Martin, lastName=Klähn)
    2 warnings
    :usage:compileJava
    ... truncated
### Generated file
Under **usage/build/classes/main/data/myname/0000.txt**
