package com.gigs2go.money.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Test Calculator basics
 */
public class TestCalculatorBasics extends AbstractCalculatorTest {
    @Test
    public void testClear () {
        MoneyCalculator calculator = getCalculator();
        calculator.clear();
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( null, result.getValue() );
        assertEquals( null, result.getRemainder() );
    }

    @Test( expected = NullPointerException.class )
    public void testSetNull () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( null );
    }

    @Test
    public void testSetZero () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_0_00 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_0_00, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testSetValue () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_1243_21 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1243_21, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

}
