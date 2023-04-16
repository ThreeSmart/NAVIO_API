FROM maven:3.8.4-jdk-11 as build
WORKDIR /home/app
COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src/
RUN mvn clean package

FROM openjdk:11
COPY --from=build /home/app/target/threesmart.jar /usr/local/lib/threesmart.jar
CMD ["java", "-jar", "/usr/local/lib/threesmart.jar"]
EXPOSE 7801