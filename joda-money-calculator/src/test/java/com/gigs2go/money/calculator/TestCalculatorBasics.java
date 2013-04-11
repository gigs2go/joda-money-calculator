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

import org.junit.Test;

/**
 * Test Calculator. All +ve values. All integer ops.
 */
public class TestCalculatorBasics extends AbstractCalculatorTest {
    @Test
    public void testClear () {
        MoneyCalculator calculator = getCalculator();
        calculator.clear();
        MoneyCalculator.FinalResult result = calculator.result();
        assertNotNull( result );
        assertEquals( null, result.getValue() );
        assertEquals( null, result.getRemainder() );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testSetNull () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( null );
    }

    @Test
    public void testSetZero () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_0_00 );
        MoneyCalculator.FinalResult result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_0_00, result.getValue() );
        assertEquals( GBP_0_00.toBigMoney(), result.getRemainder() );
    }

    @Test
    public void testSetValue () {
        MoneyCalculator calculator = getCalculator();
        calculator.set( GBP_1243_21 );
        MoneyCalculator.FinalResult result = calculator.result();
        assertNotNull( result );
        assertEquals( GBP_1243_21, result.getValue() );
        assertEquals( GBP_0_00.toBigMoney(), result.getRemainder() );
    }

}
