FROM openjdk:11
FROM maven

COPY ./ /usr/calc/
WORKDIR /usr/calc
RUN mvn clean test
CMD ["mnv", "clean", "test"]