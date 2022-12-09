# the first stage of our build will use a maven 3.6.1 parent image
FROM maven:3.8.6-openjdk-18 AS MAVEN_BUILD

# copy the pom and src code to the container
COPY ./ ./

# package our application code
RUN mvn clean package -e

# the second stage of our build will use open jdk 8 on alpine 3.9
#FROM openjdk:8-jre-alpine3.9
#FROM gcr.io/distroless/java 
#FROM adoptopenjdk-8.jdk
FROM openjdk:17


# copy only the artifacts we need from the first stage and discard the rest
COPY --from=MAVEN_BUILD /target/tictactoe-0.0.4-SNAPSHOT.jar /tictactoe-0.0.4-SNAPSHOT.jar

EXPOSE 9001 

# set the startup command to execute the jar
# CMD ["java", "-jar", "/tictactoe-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-jar","/tictactoe-0.0.4-SNAPSHOT.jar"]