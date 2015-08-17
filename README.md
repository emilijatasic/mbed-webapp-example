mbed web application - example
==============================

This is a simple web application that connects to mbed Device Server. 

### Features

- Configure connection to device server
- List all devices
- List device resources
- Invoke proxy requests (GET, PUT, POST, DELETE) 
 

Development
-----------
[![Build Status](https://magnum.travis-ci.com/ARMmbed/mbed-webapp-example.svg?token=dwQ5RVGhwvjYBMfR1k6t&branch=master)](https://magnum.travis-ci.com/ARMmbed/mbed-webapp-example)

### Requirements
- Java 8
- Maven 3.x

### Build:

    mvn clean package

With static code analyses check (findbugs, pmd, jacoco):

    mvn clean package -P static-code-check

Build executable war (with embedded tomcat):

    mvn clean package tomcat7:exec-war-only

### Run
- Jetty (embedded):
    
        mvn jetty:run

- Tomcat (embedded):

        mvn tomcat7:run

- Executable war (with embedded tomcat):

        cd target
        java -Dcom.arm.mbed.restclient.servlet.server-port=8082 -jar example-app-1.0-SNAPSHOT-war-exec.jar -httpPort=8082

Open from browser: http://localhost:8082
