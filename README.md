WIP

Push notifications from MDB to authenticated users

Build the docker image:

docker build --tag=jboss/wildfly-keycloak-admin .

and then run:

docker run -p 8080:8080 -p 9990:9990 -it jboss/wildfly-keycloak-admin

To run the web application:

mvn clean install wildfly:deploy