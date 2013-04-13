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

import java.math.RoundingMode;

import org.junit.Test;

/**
 * Test Calculator. All +ve values. All integer ops.
 */
public class TestCalculatorRemainder extends AbstractCalculatorTest {
    @Test
    public void testMultiply1 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( BIGDEC_2_345 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_7_81, result.getValue() );
        assertEquals( BIG_GBP_M0_00115, result.getRemainder() );
    }

    @Test
    public void testMultiply2 () {
        MoneyCalculator calculator = getCalculator( RoundingMode.DOWN );
        calculator.set( GBP_3_33 );
        calculator.multiply( BIGDEC_2_345 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_7_80, result.getValue() );
        assertEquals( BIG_GBP_0_00885, result.getRemainder() );
    }

    @Test
    public void testMultiply3 () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_3_33 );
        calculator.multiply( BIGDEC_0_345678 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1_15, result.getValue() );
        assertEquals( BIG_GBP_0_00111, result.getRemainder() );
    }

    @Test
    public void testMultiply4 () {
        MoneyCalculator calculator = getCalculator( RoundingMode.DOWN );
        calculator.set( GBP_3_33 );
        calculator.multiply( BIGDEC_0_345678 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1_15, result.getValue() );
        assertEquals( BIG_GBP_0_00110, result.getRemainder() );
    }

    @Test
    public void testMultiply5 () {
        MoneyCalculator calculator = getCalculator( RoundingMode.UP );
        calculator.set( GBP_3_33 );
        calculator.multiply( BIGDEC_0_345678 );
        MoneyCalculator.Result result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1_16, result.getValue() );
        assertEquals( BIG_GBP_M0_00890, result.getRemainder() );
    }

}
