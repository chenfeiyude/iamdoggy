# Versions
1. Java 8

2. Mysql 8
Mac start mysql server
```
 mysql.server start
```

3. tomcat 8
```
 brew services start tomcat
```

# Prepare

1. Install lombok for eclipse see https://stackoverflow.com/questions/45461777/lombok-problems-with-eclipse-oxygen/46034044
1). Download lombok from the site Project Lombok;
2). Close your Eclipse IDE if it is open;
3). Trigger lombok installation either by following the official installation steps or by executing the command: 
```
java -jar lombok.jar;
```

2. for testing api, using postman or install httpie
```
brew install httpie
```


# Build
build to war and deployed to docker container

## Build project 
gradle clean build

## Build docker image (from gradle) 
gradle clean build buildDocker

## Run docker locally
docker run -p 8080:8080 -t com.iamdoggy/iamdoggy-docker:0.0.1-SNAPSHOT

## Stop docker 
docker stop com.iamdoggy/iamdoggy-docker:0.0.1-SNAPSHOT



