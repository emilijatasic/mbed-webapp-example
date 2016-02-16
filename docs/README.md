mbed Web Application - Example
==============================

## Features

- Configure connection to mbed DS
- List all devices
- List device resources
- Invoke proxy requests (GET, PUT, POST, DELETE) 

## REST Client (mbed Device Server Java Client) dependency

Example app build has a dependency to mbed Device Server Java Client libraries used for calling mbed Device Server HTTP REST API. You can find the dependency defined in the pom.xml file that you can use in your own
web application to ease up and streamline development.

REST Client libraries can be found in http://maven.mbed.com repository as defined in pom.xml:
        
        ...
        <mbed-client.version>3.0.0-464</mbed-client.version>
        ...
        <dependency>
            <groupId>com.mbed</groupId>
            <artifactId>mbed-client</artifactId>
            <version>${mbed-client.version}</version>
        </dependency>
        <dependency>
            <groupId>com.mbed</groupId>
            <artifactId>mbed-client-servlet</artifactId>
            <version>${mbed-client.version}</version>
            <exclusions>
                <exclusion>
                    <artifactId>javax.servlet-api</artifactId>
                    <groupId>javax.servlet</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        ...
        <repositories>
            <repository>
                <id>mbed repository</id>
                <url>http://maven.mbed.com</url>
                <releases>
                    <enabled>true</enabled>
                </releases>
                <snapshots>
                    <enabled>false</enabled>
                </snapshots>
            </repository>
        </repositories>
        
        
## Requirements
- Java 8
- Maven 3.x
- mbed DS - where the example application connects to

## Build:

    mvn clean package

With static code analyses check (findbugs, pmd, jacoco):

    mvn clean package -P static-code-check

Build executable war (with embedded tomcat):

    mvn clean package tomcat7:exec-war-only

## Run
- Jetty (embedded):
    
        mvn jetty:run

- Tomcat (embedded):

        mvn tomcat7:run

- Executable war (with embedded Tomcat):

        cd target
        java -Dcom.arm.mbed.restclient.servlet.server-port=8082 -jar example-app-1.0-SNAPSHOT-war-exec.jar -httpPort=8082

Open from browser: http://localhost:8082

Configure with mbed Device Connector
==============================

1. Open in browser: https://connector.mbed.com.
2. Sign up for the first time or login with your credentials. 
3. Click the **Access keys** link.
4. Create new access key.
5. Copy the mbed DS address (https://api.connector.mbed.com) from this page.
6. Open the example-app in browser: http://localhost:8082.
7. Select **Configuration** tab at the top of the page.
8. Select **Token Authentication**.
9. Enter the access key and the copied mbed DS address.
10. Select Pull or Push notification channel. Pull is recommended. Push Notifications requires publicly available URL for the example app (example value: http://REMOTE_HOST:8082/mds-notif)
11. Save.

Pre-subscription
==============================

The mbed Device Server (mbed DS) eventing model consists of observable resources, which enables endpoints to deliver updated resource content, periodically or with a more sophisticated solution dependent logic. 
Applications can subscribe to every individual resource or can set a pre-subscription data to receive a notification update.

Pre-subscription is an advanced feature supported by mbed Device Server (mbed DS) along with the basic subscription feature. Pre-subscription allows an application to define a set of rules and patterns put 
by the application. When an endpoint registers and its name, type and/or registered resources match the pre-subscription data, mbed DS sends subscription requests to the device automatically.
The pre-subscription concerns all the endpoints that are already registered and the server sends subscription requests to the devices immediately when the patterns are set.

1. Open the example-app in browser: http://localhost:8082.
2. Select **Configuration** tab at the top of the page.
3. Select **Pre-Subscription** tab in the page.
4. Set the Pre-subscription pattern by entering the **Endpoint name**, **Endpoint type** and/or **Resource path**.
    - The pattern may include the endpoint name (optionally having an * character at the end), endpoint type, a list of resources or expressions with an * character at the end.
    
    _Example_
    
        endpoint-type: "Light",
        resource-path: ["/sen/*"]
        
5. Click the **ADD**.
6. Click the **delete** to delete the pattern.
7. Click the **edit** to edit the pattern.
8. When you finished the Pre-subscription patterns click **SAVE**.
    - Changing the pre-subscription data overwrites the previous subscriptions. To remove the pre-subscription data, put an empty array as a rule.