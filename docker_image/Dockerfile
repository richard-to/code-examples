from dockerfile/java

RUN apt-get install -y g++

RUN addgroup --system javabot
RUN adduser --system --shell /bin/bash --uid 5000 --disabled-password  --home /home/javabot javabot
RUN adduser javabot javabot

ADD ./compile_and_run_java.sh /home/javabot/compile_and_run_java.sh
RUN chmod 755 /home/javabot/compile_and_run_java.sh
RUN chown javabot:javabot /home/javabot/compile_and_run_java.sh

USER root
ENV HOME /home/javabot
WORKDIR /home/javabot

ENTRYPOINT ["/home/javabot/compile_and_run_java.sh"]