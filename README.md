WIP

Push notifications from MDB to authenticated users

Build the docker image:

docker build --rm --tag=jboss/wildfly-admin .

and then run:

docker run -p 8080:8080 -p 9990:9990 -it jboss/wildfly-admin

To run the web application:

mvn clean package wildfly:deploy

Browse to 

http://localhost:8080/jee-websocket-sample/index.xhtml