package br.com.itsmaneka.restwithspringbootandjava.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.itsmaneka.restwithspringbootandjava.converters.NumberConverter;
import br.com.itsmaneka.restwithspringbootandjava.exceptions.UnsupportedMathOperationException;
import br.com.itsmaneka.restwithspringbootandjava.math.SimpleMath;

@RestController
public class MathController {
    private SimpleMath math = new SimpleMath();
    
    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}",
        method = RequestMethod.GET)
    public Double sum(
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
        ) throws Exception {
            if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
                throw new UnsupportedMathOperationException("Informe um valor numérico");
            }
            return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}",
        method = RequestMethod.GET)
    public Double sub(
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
        ) throws Exception {
            if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
                throw new UnsupportedMathOperationException("Informe um valor numérico");
            }
            return math.sub(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mult/{numberOne}/{numberTwo}",
        method = RequestMethod.GET)
    public Double mult(
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
        ) throws Exception {
            if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
                throw new UnsupportedMathOperationException("Informe um valor numérico");
            }
            return math.mult(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}",
        method = RequestMethod.GET)
    public Double div(
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
        ) throws Exception {
            if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
                throw new UnsupportedMathOperationException("Informe um valor numérico.");
            }
            
            return math.div(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}",
        method = RequestMethod.GET)
    public Double mean(
        @PathVariable(value = "numberOne") String numberOne,
        @PathVariable(value = "numberTwo") String numberTwo
        ) throws Exception {
            if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
                throw new UnsupportedMathOperationException("Informe um valor numérico.");
            }
            
            return math.mean(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/sqrt/{number}",
        method = RequestMethod.GET)
    public Double sqrt(
        @PathVariable(value = "number") String number
        ) throws Exception {
            if (!NumberConverter.isNumeric(number)) {
                throw new UnsupportedMathOperationException("Informe um valor numérico.");
            }
            
            return math.sqrt(NumberConverter.convertToDouble(number));
    }
}