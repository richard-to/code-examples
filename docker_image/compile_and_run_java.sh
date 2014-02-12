#!/bin/bash

cd /root/code
/usr/bin/javac $1.java
if [ $? -eq 0 ]; then
    /usr/bin/timeout 10s /usr/bin/java $1
    if [ $? -eq 124 ]; then
        /bin/echo "Program timed out. Execution time is limited to 10 seconds"
    fi
fi
