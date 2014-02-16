#!/bin/bash

/usr/bin/javac -d . code/$1.java
if [ $? -eq 0 ]; then
    /usr/bin/timeout 10s /usr/bin/java $1
    if [ $? -eq 124 ]; then
        /bin/echo "Program timed out. Execution time is limited to 10 seconds"
    fi
fi
