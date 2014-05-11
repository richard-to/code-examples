from dockerfile/java

RUN apt-get install -y g++
RUN apt-get install -y testng

RUN addgroup --system codebot
RUN adduser --system --shell /bin/bash --uid 5000 --disabled-password  --home /home/codebot codebot
RUN adduser codebot codebot

ADD ./compile_and_run.sh /home/codebot/compile_and_run.sh
RUN chmod 755 /home/codebot/compile_and_run.sh
RUN chown codebot:codebot /home/codebot/compile_and_run.sh

ADD ./ReadableListener.java /home/codebot/ReadableListener.java
RUN chown codebot:codebot /home/codebot/ReadableListener.java
RUN /usr/bin/javac -cp .:/usr/share/java/testng.jar /home/codebot/ReadableListener.java

USER codebot
ENV HOME /home/codebot
WORKDIR /home/codebot

ENTRYPOINT ["/home/codebot/compile_and_run.sh"]