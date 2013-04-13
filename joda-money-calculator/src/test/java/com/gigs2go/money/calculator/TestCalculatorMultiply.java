package com.gigs2go.money.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Test Calculator. All +ve values. All integer ops.
 */
public class TestCalculatorMultiply extends AbstractCalculatorTest {
    @Test
    public void testMultiplyZeroes () {
        MoneyCalculator calculator = getCalculator();
        calculator.multiply( GBP_0_00, INT_ZERO );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_0_00, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test( expected = NullPointerException.class )
    public void testMultiplyNulls1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.multiply( null, INT_ZERO );
    }

    @Test( expected = NullPointerException.class )
    public void testMultiplyNulls2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( GBP_0_00, null );
    }

    @Test
    public void testMultiplyValues () {
        MoneyCalculator calculator = getCalculator();
        calculator.multiply( GBP_3_33, INT_3 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_9_99, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testMultiplyZero1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( INT_ZERO );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_0_00, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testMultiplyZero2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( BIGDEC_ZERO );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_0_00, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testMultiplyValue1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( INT_3 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_9_99, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testMultiplyValue2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( BIGDEC_3 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_9_99, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test( expected = NullPointerException.class )
    public void testMultiplyNull1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( (Integer)null );
    }

    @Test( expected = NullPointerException.class )
    public void testMultiplyNull2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( (BigDecimal)null );
    }

}
