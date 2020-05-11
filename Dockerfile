FROM openjdk:8

RUN apt-get update && apt-get install maven -y

ADD ./ /backend/
WORKDIR /backend

RUN mvn clean install -DskipTests

ENTRYPOINT java -Dspring.datasource.url="jdbc:postgresql://db:5432/kamkk" -jar target/kamkk-*.jar