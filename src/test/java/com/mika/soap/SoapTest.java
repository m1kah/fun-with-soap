package com.mika.soap;

import org.junit.jupiter.api.Test;

import javax.xml.soap.MessageFactory;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class SoapTest {
    @Test
    public void helloSoap() throws Exception {
        var factory = MessageFactory.newInstance();
        var message = factory.createMessage();
        var out = new ByteArrayOutputStream();
        message.writeTo(out);
        var xml = new String(out.toByteArray(), StandardCharsets.UTF_8);
        System.out.println(xml);
    }
}
