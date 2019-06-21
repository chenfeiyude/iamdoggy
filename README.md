# Versions
1. Java 8

2. Mysql 5.7

3. tomcat 8

# Prepare

1. Install lombok for eclipse see https://stackoverflow.com/questions/45461777/lombok-problems-with-eclipse-oxygen/46034044  
1). Download lombok from the site Project Lombok https://projectlombok.org/download;  
2). Close your Eclipse IDE if it is open;  
3). Trigger lombok installation either by following the official installation steps or by executing the command:   

```
java -jar lombok.jar;
```
4). Restart IDE and rebuild projects

2. for testing api, using postman or install httpie
```
brew install httpie
```


# Build
build to war and deployed to docker container

## Build project 
```
gradle clean build
```

## Build docker 
```
gradle clean build buildDocker
```

## Run docker locally
```
docker-compose up
```

# Stop and Clean

## Stop docker 
```
docker-compose stop
```

## Remove docker and volumns
```
docker-compose down -v
```

## Remove image
```
docker image ls

REPOSITORY                 TAG                 IMAGE ID            CREATED             SIZE
iamdoggy_iamdoggy_app      latest              868b9adb4f52        20 minutes ago      514MB


docker image rm iamdoggy_iamdoggy_app -f
```




