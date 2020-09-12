FROM airhacks/wildfly
COPY build/libs/fun-with-soap.war /opt/wildfly-20.0.0.Final/standalone/deployments/
