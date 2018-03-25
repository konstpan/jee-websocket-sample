### Push notifications from a message driven bean to either all authenticated users or specific ones

Build the docker image:

docker build --rm --tag=jboss/wildfly-admin .

then run:

docker network create mynet

Start keycloak container:

docker run --name authserver -d --net mynet -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=secret jboss/keycloak:3.4.3.Final

* You will have to add authserver to point to 127.0.0.1 in etc/hosts

Login to 

http://authserver:8080/auth/admin

and create portal realm, add a client with ID web and * to valid redirect uris. 

Create role auth_user and assign two users with that role.

Start wildfly container:

docker run --name wildfly -d --net mynet -p 8090:8080 -p 9990:9990 jboss/wildfly-admin

To see logs:

docker logs -f wildfly

To run the web application:

mvn clean package wildfly:deploy

Browse to 

http://localhost:8090/jee-websocket-sample/index.xhtml

Open two browsers and log in with different users. Application events are pushed to all clients, but user events target only the logged in user.

* Keycloak uses as user id the ID auto-generated when creating a user in keycloak admin interface.
