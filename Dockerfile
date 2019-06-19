FROM tomcat:8.5.41-jre8

VOLUME /tmp
COPY build/libs/iamdoggy-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/app.war

EXPOSE 8080