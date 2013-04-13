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
public class TestCalculatorAdd extends AbstractCalculatorTest {
    @Test
    public void testAddZeroes1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( GBP_0_00, GBP_0_00 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_0_00, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testAddZeroes2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( GBP_0_00, JPY_000 );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testAddZeroes3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( JPY_000, GBP_0_00 );
    }

    @Test( expected = NullPointerException.class )
    public void testAddNulls1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( null, GBP_0_00 );
    }

    @Test( expected = NullPointerException.class )
    public void testAddNulls2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( GBP_0_00, null );
    }

    @Test
    public void testAddValues1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( GBP_3_33, GBP_1_23 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_4_56, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testAddValues2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( GBP_3_33, JPY_333 );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testAddValues3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.add( JPY_333, GBP_3_33 );
    }

    @Test
    public void testAddZero1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.add( GBP_0_00 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_3_33, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testAddZero2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.add( JPY_000 );
    }

    @Test
    public void testAddValue1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.add( GBP_1_23 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_4_56, result.getValue() );
        assertEquals( BIG_GBP_0_00000, result.getRemainder() );
    }

    @Test( expected = CurrencyMismatchException.class )
    public void testAddValue2 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.add( JPY_333 );
    }

    @Test( expected = NullPointerException.class )
    public void testAddNull () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.add( null );
    }
}
