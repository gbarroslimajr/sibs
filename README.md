# SIBS - DEMO TEST #


### Requirements ###

* Java 8+
* Maven 3.2.x+
* Docker


### Building the artifact ###

```
mvn clean package
```

### Running the application from command line ###

#### Docker ####
```
please, run first:  docker compose -f ./docker/docker-compose.yml up -d
```

#### running service  ####
```
mvn spring-boot:run
```

#### Swagger ####

```
http://localhost:8080/api/swagger-ui/index.html
```


should result in successful responses. Please look at the logs to verify Hibernate executes different queries depending on the request parameters.

#### Note: ####

Some features were implemented only for testing and not fully developed, but the code is 100% functional.
In a production environment it would be necessary to refactor and improve some parts of the code, but I believe that for testing purposes it's presentable.


