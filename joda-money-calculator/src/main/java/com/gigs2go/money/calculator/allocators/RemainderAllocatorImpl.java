package com.gigs2go.money.calculator.allocators;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.gigs2go.money.calculator.Allocator;

/**
 * Given a Money and a length, this allocator returns an array of length+1 items
 * where the remainder is the last element. No rounding is performed, and the
 * total value of the array elements is exactly equal to the original value.
 */
public class RemainderAllocatorImpl implements Allocator {

    public Money[] allocate ( Money money, int len ) {
        Money[] result = new Money[len + 1];
        int scale = money.getScale();
        CurrencyUnit currency = money.getCurrencyUnit();

        // Integral ops only
        BigInteger amount = money.getAmount().unscaledValue();
        BigInteger[] divAndR = amount.divideAndRemainder( BigInteger.valueOf( len ) );
        long remainder = divAndR[1].longValue();
        BigDecimal division = BigDecimal.valueOf( divAndR[0].longValue() ).movePointLeft( scale );
        for ( int i = 0; i < len; i++ ) {
            result[i] = Money.of( currency, division );
        }
        result[len] = Money.of( currency, BigDecimal.valueOf( remainder ).movePointLeft( scale ) );
        return result;

    }
}
