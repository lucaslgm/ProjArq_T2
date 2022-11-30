FROM maven:3.8.6-openjdk-18
COPY ./target/traffic-control-api-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","traffic-control-api-0.0.1-SNAPSHOT.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
EXPOSE 8081