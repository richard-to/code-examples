#!/bin/bash

CLASSPATH=.:/usr/share/java/junit4.jar

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
        /usr/bin/timeout 20s /usr/bin/java -cp $CLASSPATH org.junit.runner.JUnitCore $TESTFILE
        if [ $? -eq 124 ]; then
            /bin/echo "Program timed out. Execution time is limited to 20 seconds"
        fi
    fi
fi
