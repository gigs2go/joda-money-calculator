package com.gigs2go.money.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

/**
 * Test Calculator multiply operations
 */
public class TestCalculatorMultiply extends AbstractCalculatorTest {
    @Test
    public void testMultiplyZeroes () {
        MoneyCalculator calculator = getCalculator();
        calculator.multiply( GBP_0_00, LONG_0 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_0_00, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test( expected = NullPointerException.class )
    public void testMultiplyNulls1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.multiply( null, LONG_0 );
    }

    @Test( expected = NullPointerException.class )
    public void testMultiplyNulls2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( GBP_0_00, null );
    }

    @Test
    public void testMultiplyValues () {
        MoneyCalculator calculator = getCalculator();
        calculator.multiply( GBP_3_33, LONG_3 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_9_99, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testMultiplyZero1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( LONG_0 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_0_00, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testMultiplyZero2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( BIGDEC_0 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_0_00, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test
    public void testMultiplyValue1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( LONG_3 );
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

    @Test
    public void testMultiplyValue3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( DOUBLE_3_00000 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_9_99, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test( expected = NullPointerException.class )
    public void testMultiplyNull1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( (Long)null );
    }

    @Test( expected = NullPointerException.class )
    public void testMultiplyNull2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( (Double)null );
    }

    @Test( expected = NullPointerException.class )
    public void testMultiplyNull3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( (BigDecimal)null );
    }

    @Test
    public void testMultiplySequence1 () {
        MoneyCalculator calculator = getCalculator();
        MoneyCalculator.Result result = calculator.multiply( GBP_3_33, BIGDEC_3_33 ).multiply( BIGDEC_3_33 ).multiply( BIGDEC_3_33 ).result();
        assertEquals( GBP_122_96, result.getValue() );
        assertEquals( BIG_GBP_0_00370, result.getRemainder() );
    }

    @Test
    public void testMultiplySequence2 () {
        MoneyCalculator calculator = getCalculator();
        MoneyCalculator.Result result = calculator.multiply( GBP_3_33, BIGDEC_3_33 ).multiply( DOUBLE_3_33 ).multiply( DOUBLE_3_33 ).multiply( 2L ).result();
        assertEquals( GBP_245_93, result.getValue() );
        assertEquals( BIG_GBP_M0_00259, result.getRemainder() );
    }

    @Test
    public void testMultiplySequence3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.setRemainderScale( 8 );
        MoneyCalculator.Result result = calculator.multiply( GBP_3_33, BIGDEC_3_33 ).multiply( DOUBLE_3_33 ).multiply( DOUBLE_3_33 ).multiply( 2L ).result();
        assertEquals( GBP_245_93, result.getValue() );
        assertEquals( BIG_GBP_M0_00259358, result.getRemainder() );
    }

    @Test
    public void testMultiplySequence4 () {
        MoneyCalculator calculator = getCalculator( RoundingMode.DOWN );
        calculator.setRemainderScale( 8 );
        MoneyCalculator.Result result = calculator.multiply( GBP_3_33, BIGDEC_3_33 ).multiply( DOUBLE_3_33 ).multiply( DOUBLE_3_33 ).multiply( 2L ).result();
        assertEquals( GBP_245_92, result.getValue() );
        assertEquals( BIG_GBP_0_00740642, result.getRemainder() );
    }

}
