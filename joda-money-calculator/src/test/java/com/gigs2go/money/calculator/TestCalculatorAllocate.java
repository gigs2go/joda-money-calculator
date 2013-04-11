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

import org.joda.money.Money;
import org.junit.Test;

import com.gigs2go.money.calculator.allocators.AllocatorStrategy;

/**
 * Test Calculator.
 */
public class TestCalculatorAllocate extends AbstractCalculatorTest {
    @Test( expected = IllegalArgumentException.class )
    public void testAllocate1 () {
        MoneyCalculator calculator = getCalculator();
        @SuppressWarnings( "unused" )
        Money[] money = calculator.allocate( AllocatorStrategy.FRONT_LOADING.getAllocator(), null, 1 );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testAllocate2 () {
        MoneyCalculator calculator = getCalculator();
        @SuppressWarnings( "unused" )
        Money[] money = calculator.allocate( AllocatorStrategy.FRONT_LOADING.getAllocator(), GBP_0_00, 0 );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testAllocate3 () {
        MoneyCalculator calculator = getCalculator();
        @SuppressWarnings( "unused" )
        Money[] money = calculator.allocate( AllocatorStrategy.FRONT_LOADING.getAllocator(), GBP_0_00, -1 );
    }

    @Test
    public void testAllocate4 () {
        MoneyCalculator calculator = getCalculator();
        Money[] money = calculator.allocate( AllocatorStrategy.FRONT_LOADING.getAllocator(), GBP_0_00, 1 );
        assertNotNull( money );
        assertEquals( money.length, 1 );
        assertEquals( money[0], GBP_0_00 );
    }

    @Test
    public void testAllocate5 () {
        MoneyCalculator calculator = getCalculator();
        Money[] money = calculator.allocate( AllocatorStrategy.FRONT_LOADING.getAllocator(), GBP_10_00, 1 );
        assertNotNull( money );
        assertEquals( money.length, 1 );
        assertEquals( money[0], GBP_10_00 );
    }

    @Test
    public void testAllocate5a () {
        MoneyCalculator calculator = getCalculator();
        Money[] money = calculator.allocate( AllocatorStrategy.FRONT_LOADING.getAllocator(), GBP_10_00, 2 );
        assertNotNull( money );
        assertEquals( money.length, 2 );
        assertEquals( money[0], GBP_5_00 );
        assertEquals( money[1], GBP_5_00 );
        assertEquals( Money.total( money ), GBP_10_00 );
    }

    @Test
    public void testAllocate5b () {
        MoneyCalculator calculator = getCalculator();
        Money[] money = calculator.allocate( AllocatorStrategy.FRONT_LOADING.getAllocator(), GBP_10_00, 3 );
        assertNotNull( money );
        assertEquals( money.length, 3 );
        assertEquals( money[0], GBP_3_34 );
        assertEquals( money[1], GBP_3_33 );
        assertEquals( money[2], GBP_3_33 );
        assertEquals( Money.total( money ), GBP_10_00 );
    }

    @Test
    public void testAllocate6 () {
        MoneyCalculator calculator = getCalculator();
        Money[] money = calculator.allocate( AllocatorStrategy.FRONT_LOADING.getAllocator(), GBP_10_02, 3 );
        assertNotNull( money );
        assertEquals( money.length, 3 );
        assertEquals( money[0], GBP_3_34 );
        assertEquals( money[1], GBP_3_34 );
        assertEquals( money[2], GBP_3_34 );
        assertEquals( Money.total( money ), GBP_10_02 );
    }

    @Test
    public void testAllocate7 () {
        MoneyCalculator calculator = getCalculator();
        Money[] money = calculator.allocate( AllocatorStrategy.FRONT_LOADING.getAllocator(), GBP_1243_21, 13 );
        assertNotNull( money );
        assertEquals( money.length, 13 );
        assertEquals( money[0], GBP_95_64 );
        assertEquals( money[1], GBP_95_64 );
        assertEquals( money[2], GBP_95_64 );
        assertEquals( money[3], GBP_95_64 );
        assertEquals( money[4], GBP_95_64 );
        assertEquals( money[5], GBP_95_64 );
        assertEquals( money[6], GBP_95_64 );
        assertEquals( money[7], GBP_95_64 );
        assertEquals( money[8], GBP_95_64 );
        assertEquals( money[9], GBP_95_64 );
        assertEquals( money[10], GBP_95_63 );
        assertEquals( money[11], GBP_95_63 );
        assertEquals( money[12], GBP_95_63 );
        assertEquals( Money.total( money ), GBP_1243_21 );
    }

    @Test
    public void testAllocate10 () {
        MoneyCalculator calculator = getCalculator();
        Money[] money = calculator.allocate( AllocatorStrategy.FRONT_LOADING.getAllocator(), JPY_1000, 3 );
        assertNotNull( money );
        assertEquals( money.length, 3 );
        assertEquals( money[0], JPY_334 );
        assertEquals( money[1], JPY_333 );
        assertEquals( money[2], JPY_333 );
        assertEquals( Money.total( money ), JPY_1000 );
    }

    @Test
    public void testAllocateBack () {
        MoneyCalculator calculator = getCalculator();
        Money[] money = calculator.allocate( AllocatorStrategy.BACK_LOADING.getAllocator(), GBP_10_00, 3 );
        assertNotNull( money );
        assertEquals( money.length, 3 );
        assertEquals( money[0], GBP_3_33 );
        assertEquals( money[1], GBP_3_33 );
        assertEquals( money[2], GBP_3_34 );
        assertEquals( Money.total( money ), GBP_10_00 );
    }

}
