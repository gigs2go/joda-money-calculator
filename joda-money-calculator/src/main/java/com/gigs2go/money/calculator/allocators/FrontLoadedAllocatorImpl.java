package com.gigs2go.money.calculator.allocators;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.gigs2go.money.calculator.Allocator;

/**
 * Given a Money and a length, this allocator returns an array of length items
 * where the remainder is shared across the first n elements (where n <=
 * length). No rounding is performed, and the total value of the array elements
 * is exactly equal to the original value.
 */
public class FrontLoadedAllocatorImpl implements Allocator {

    public Money[] allocate ( Money money, int len ) {
        Money[] result = new Money[len];
        int scale = money.getScale();
        CurrencyUnit currency = money.getCurrencyUnit();

        BigInteger amount = money.getAmount().unscaledValue();
        BigInteger[] divAndR = amount.divideAndRemainder( BigInteger.valueOf( len ) );
        int remainder = divAndR[1].intValue();
        BigDecimal lowResult = BigDecimal.valueOf( divAndR[0].longValue() ).movePointLeft( scale );
        BigDecimal highResult = BigDecimal.valueOf( divAndR[0].longValue() + 1 ).movePointLeft( scale );
        for ( int i = 0; i < remainder; i++ ) {
            result[i] = Money.of( currency, highResult );
        }
        for ( int i = remainder; i < len; i++ ) {
            result[i] = Money.of( currency, lowResult );
        }
        return result;

    }

}
