## Working with LineTownElection in your IDE

## Running LineTownElection locally
LineTownElection is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Gradle](https://spring.io/guides/gs/gradle/). You can build a jar file and run it from the command line (it should work just as well with Java 8 or newer):

```
git clone https://github.com/sirgeorgie/LineTown.git
cd LineTown
./gradlew bootJar
cd build/libs/
java -jar LineTownElection-0.0.1-SNAPSHOT.jar
```

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer (full JDK not a JRE).
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * IntelliJ IDEA with the Jmix plugin. Note: follow the install process here: https://www.jmix.io/jmix-plugin-installation/
  
### Steps:

1) On the command line
    ```
    git clone https://github.com/sirgeorgie/LineTown.git
    ```
	
2) Inside IntelliJ IDEA
    In the main menu, choose `File -> Open` and select the LineTownElection Project. Click on the `Open` button.

    A run configuration named `LineTownElection Jmix Application` should have been created for you.

3) Navigate to LineTownElection

    Visit [http://localhost:8080](http://localhost:8080) in your browser.
	
## Building a Container

```
docker build -t LineTownElection .
docker run -p 8080:8080 LineTownElection
```

## How to get Bearer Token
Use the following cURL command to do the request:
```
curl -X POST http://localhost:8080/oauth/token \
   --basic --user client:secret \
   -H "Content-Type: application/x-www-form-urlencoded" \
   -d "grant_type=password&username=admin&password=admin"
```
