package com.mika.soap;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.superbiz.wsdl.Multiply;
import org.superbiz.wsdl.ObjectFactory;
import org.superbiz.wsdl.Sum;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;

public class SoapTest {
    @BeforeAll
    public static void setup() {
        RestAssured.port = 8080;
    }

    @Test
    public void wsdl() {
        when()
            .get("/ws/calculator?wsdl")
        .then()
                .log().ifValidationFails()
            .statusCode(200);
    }

    @Test
    public void sum() throws Exception {
        var soapBytes = serialize(soapMessage(sum(12, 9)));

        given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "")
                .body(soapBytes)
            .when()
                .post("/ws/calculator")
            .then()
                .log().all()
                .statusCode(200)
                .body("Envelope.Body.sumResponse.return", is("21"));
    }

    @Test
    public void multiply() throws Exception {
        var soapBytes = serialize(soapMessage(multiply(12, 9)));

        given()
                .header("Content-Type", "text/xml")
                .header("SOAPAction", "")
                .body(soapBytes)
                .when()
                .post("/ws/calculator")
                .then()
                .log().all()
                .statusCode(200)
                .body("Envelope.Body.multiplyResponse.return", is("108"));
    }

    private JAXBElement<Sum> sum(int x, int y) {
        var factory = new ObjectFactory();
        var sum = factory.createSum();
        sum.setArg0(x);
        sum.setArg1(y);
        return factory.createSum(sum);
    }

    private JAXBElement<Multiply> multiply(int x, int y) {
        var factory = new ObjectFactory();
        var sum = factory.createMultiply();
        sum.setArg0(x);
        sum.setArg1(y);
        return factory.createMultiply(sum);
    }

    private SOAPMessage soapMessage(JAXBElement<?> body) throws Exception {
        var factory = MessageFactory.newInstance();
        var message = factory.createMessage();
        var context = JAXBContext.newInstance(body.getValue().getClass());
        var marshaller = context.createMarshaller();
        var document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        marshaller.marshal(body, document);
        message.getSOAPBody().addDocument(document);
        message.saveChanges();
        return message;
    }

    private byte[] serialize(SOAPMessage message) throws Exception {
        try (var out = new ByteArrayOutputStream()) {
            message.writeTo(out);
            return out.toByteArray();
        }
    }
}
