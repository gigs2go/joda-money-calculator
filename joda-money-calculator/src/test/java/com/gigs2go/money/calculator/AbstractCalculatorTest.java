package com.gigs2go.money.calculator;

import static org.junit.Assert.assertNotNull;

import java.math.RoundingMode;

public class AbstractCalculatorTest implements Constants {
    protected MoneyCalculator getCalculator () {
        MoneyCalculator calculator = new MoneyCalculator();
        assertNotNull( calculator );
        return calculator;
    }

    protected MoneyCalculator getCalculator ( RoundingMode roundingMode ) {
        MoneyCalculator calculator = new MoneyCalculator( roundingMode );
        assertNotNull( calculator );
        return calculator;
    }

}
