FROM openjdk:11
EXPOSE 4000
ADD ./target/ms-bootcoin-customer-0.0.1-SNAPSHOT.jar ms-bootcoin-customer.jar
ENTRYPOINT ["java","-jar","/ms-bootcoin-customer.jar"]