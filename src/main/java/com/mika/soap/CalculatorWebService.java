package com.mika.soap;

import org.superbiz.wsdl.CalculatorWs;

import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless
@WebService(
        name = "CalculatorWebService",
        serviceName = "CalculatorWebService",
        portName = "CalculatorWebServicePort"
)
public class CalculatorWebService implements CalculatorWs {
    @Override
    public int sum(int x, int y) {
        return x + y;
    }

    @Override
    public int multiply(int x, int y) {
        return x * y;
    }
}
