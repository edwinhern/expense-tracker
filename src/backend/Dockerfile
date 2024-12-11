FROM maven:openjdk

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests

EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar","target/backend-0.0.1-SNAPSHOT.jar", "config.yml"]
