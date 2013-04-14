package com.gigs2go.money.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

/**
 * Test Calculator divide operations
 */
public class TestCalculatorDivide extends AbstractCalculatorTest {
    @Test( expected = ArithmeticException.class )
    public void testDivideZeroes () {
        MoneyCalculator calculator = getCalculator();
        calculator.divide( GBP_0_00, LONG_0 );
    }

    @Test( expected = NullPointerException.class )
    public void testDivideNulls1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.divide( null, LONG_0 );
    }

    @Test( expected = NullPointerException.class )
    public void testDivideNulls2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( GBP_0_00, null );
    }

    @Test
    public void testDivideValues1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.divide( GBP_3_33, LONG_3 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1_11, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testDivideValues2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.divide( GBP_10_00, LONG_3 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_3_33, result.getValue() );
        assertEquals( BIG_GBP_0_00333, result.getRemainder() );
    }

    @Test
    public void testDivideValues3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.setRemainderScale( 8 );
        calculator.divide( GBP_10_00, LONG_3 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_3_33, result.getValue() );
        assertEquals( BIG_GBP_0_00333333, result.getRemainder() );
    }

    @Test
    public void testDivideValues4 () {
        MoneyCalculator calculator = getCalculator( RoundingMode.DOWN );
        calculator.setRemainderScale( 8 );
        calculator.divide( GBP_10_00, LONG_6 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1_66, result.getValue() );
        assertEquals( BIG_GBP_0_00666666, result.getRemainder() );
    }

    @Test
    public void testDivideValues5 () {
        MoneyCalculator calculator = getCalculator( RoundingMode.UP );
        calculator.setRemainderScale( 8 );
        calculator.divide( GBP_10_00, LONG_6 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1_67, result.getValue() );
        assertEquals( BIG_GBP_M0_00333333, result.getRemainder() );
    }

    @Test( expected = ArithmeticException.class )
    public void testDivideZero1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.divide( LONG_0 );
    }

    @Test( expected = ArithmeticException.class )
    public void testDivideZero2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.divide( BIGDEC_0 );
    }

    @Test( expected = ArithmeticException.class )
    public void testDivideZero3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.divide( DOUBLE_0_0 );
    }

    @Test
    public void testDivideValue1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.divide( LONG_3 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1_11, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testDivideValue2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.divide( BIGDEC_3 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1_11, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testDivideValue3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.divide( DOUBLE_3_00000 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1_11, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test( expected = NullPointerException.class )
    public void testDivideNull1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.divide( (Long)null );
    }

    @Test( expected = NullPointerException.class )
    public void testDivideNull2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.divide( (Double)null );
    }

    @Test( expected = NullPointerException.class )
    public void testDivideNull3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.divide( (BigDecimal)null );
    }

}
