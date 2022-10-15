package br.com.itsmaneka.restwithspringbootandjava.math;

import br.com.itsmaneka.restwithspringbootandjava.exceptions.UnsupportedMathOperationException;

public class SimpleMath {
    public Double sum( Double numberOne, Double numberTwo ) {
        return numberOne + numberTwo;
    }

    public Double sub( Double numberOne, Double numberTwo ) {
        return numberOne - numberTwo;
    }

    public Double mult( Double numberOne, Double numberTwo ) {
        return numberOne * numberTwo;
    }

    public Double div(Double numberOne, Double numberTwo ) throws Exception {
            if (numberTwo == 0) {
                throw new UnsupportedMathOperationException("Divisão por zero.");
            }
            
            return numberOne / numberTwo;
    }

    public Double mean( Double numberOne, Double numberTwo ) {
        return (numberOne + numberTwo) / 2;
    }

    public Double sqrt( Double number ) {
        return Math.sqrt(number);
    }
}
