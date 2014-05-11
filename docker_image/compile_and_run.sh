#!/bin/bash

CLASSPATH=.:/usr/share/java/testng.jar:/usr/share/java/qdox.jar:/usr/share/java/bsh.jar:/usr/share/java/jcommander.jar

TYPE=$1
FILE=$2
TESTFILE=${2}Test

if [ $TYPE = 1 ]; then
    /usr/bin/javac -d . code/$FILE.java
    if [ $? -eq 0 ]; then
        /usr/bin/timeout 10s /usr/bin/java $FILE
        if [ $? -eq 124 ]; then
            /bin/echo "Program timed out. Execution time is limited to 10 seconds"
        fi
    fi
elif [ $TYPE = 2 ]; then
    /usr/bin/javac -cp $CLASSPATH -d . code/$FILE.java code/$TESTFILE.java
    if [ $? -eq 0 ]; then
        /usr/bin/java -cp $CLASSPATH org.testng.TestNG -listener ReadableListener  -testname "$FILE Tests" -suitename "$FILE Test Suite" -usedefaultlisteners false -testclass $TESTFILE
        if [ $? -eq 124 ]; then
            /bin/echo "Program timed out. Execution time is limited to 20 seconds"
        fi
    fi
elif [ $TYPE = 3 ]; then
    /usr/bin/g++ code/$FILE.cpp
    if [ $? -eq 0 ]; then
        /usr/bin/timeout 10s ./a.out
        if [ $? -eq 124 ]; then
            /bin/echo "Program timed out. Execution time is limited to 10 seconds"
        fi
    fi    
fi