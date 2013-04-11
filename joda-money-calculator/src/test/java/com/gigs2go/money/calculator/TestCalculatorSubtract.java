/*
 *  Copyright 2009-2013 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.gigs2go.money.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.joda.money.CurrencyMismatchException;
import org.junit.Test;

/**
 * Test Calculator. All +ve values. All integer ops.
 */
public class TestCalculatorSubtract extends AbstractCalculatorTest {
    @Test
    public void testSubtractZeroes1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.subtract( GBP_0_00, GBP_0_00 );
        MoneyCalculator.FinalResult result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_0_00, result.getValue() );
        assertEquals( GBP_0_00.toBigMoney(), result.getRemainder() );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testSubtractZeroes2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.subtract( GBP_0_00, JPY_000 );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testSubtractZeroes3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.subtract( JPY_000, GBP_0_00 );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSubtractNulls1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.subtract( null, GBP_0_00 );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSubtractNulls2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.subtract( GBP_0_00, null );
    }

    @Test
    public void testSubtractValues1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.subtract( GBP_3_33, GBP_1_23 );
        MoneyCalculator.FinalResult result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_2_10, result.getValue() );
        assertEquals( GBP_0_00.toBigMoney(), result.getRemainder() );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testSubtractValues2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.subtract( GBP_3_33, JPY_333 );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testSubtractValues3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.subtract( JPY_333, GBP_3_33 );
    }

    @Test
    public void testSubtractValue1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.subtract( GBP_1_23 );
        MoneyCalculator.FinalResult result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_2_10, result.getValue() );
        assertEquals( GBP_0_00.toBigMoney(), result.getRemainder() );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testSubtractValue2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.subtract( JPY_333 );
    }

    @Test
    public void testSubtractZero1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.subtract( GBP_0_00 );
        MoneyCalculator.FinalResult result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_3_33, result.getValue() );
        assertEquals( GBP_0_00.toBigMoney(), result.getRemainder() );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testSubtractZero2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.subtract( JPY_000 );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSubtractNull () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.add( null );
    }

}