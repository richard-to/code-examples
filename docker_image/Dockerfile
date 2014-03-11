from dockerfile/java

RUN apt-get install -y g++
RUN apt-get install -y junit4
RUN apt-get install -y testng

RUN addgroup --system javabot
RUN adduser --system --shell /bin/bash --uid 5000 --disabled-password  --home /home/javabot javabot
RUN adduser javabot javabot

ADD ./compile_and_run_java.sh /home/javabot/compile_and_run_java.sh
RUN chmod 755 /home/javabot/compile_and_run_java.sh
RUN chown javabot:javabot /home/javabot/compile_and_run_java.sh

ADD ./ReadableListener.java /home/javabot/ReadableListener.java
RUN chown javabot:javabot /home/javabot/ReadableListener.java
RUN /usr/bin/javac -cp .:/usr/share/java/testng.jar /home/javabot/ReadableListener.java

USER javabot
ENV HOME /home/javabot
WORKDIR /home/javabot

ENTRYPOINT ["/home/javabot/compile_and_run_java.sh"]