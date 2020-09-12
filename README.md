# Fun with SOAP

Legacy stuff.

## Generating Web Service

Generated sources are checked in and can be updated with a gradle task.

    gradle wsImport

## Running

Docker and IntelliJ IDEA
https://www.jetbrains.com/help/idea/deploying-a-web-app-into-wildfly-container.html

Docker base image
https://github.com/AdamBien/docklands/blob/master/wildfly/Dockerfile

Calculator WSDL
https://tomee.apache.org/examples-trunk/simple-webservice/

## Dependencies

Dependencies for constructing SOAP messages

https://stackoverflow.com/questions/48204141/replacements-for-deprecated-jpms-modules-with-java-ee-apis/48204154

    testImplementation 'jakarta.xml.ws:jakarta.xml.ws-api:2.3.3'

https://stackoverflow.com/questions/47199846/unable-to-create-saaj-meta-factory-after-packaging-as-jar
    
    testImplementation 'com.sun.xml.messaging.saaj:saaj-impl:1.5.1'
