package com.gigs2go.money.calculator.allocators;

/**
 * Given a Money and a length, this allocator returns an array of length items where the remainder
 * is shared across the last n elements (where n <= length).
 * No rounding is performed, and the total value of the array elements is exactly equal to the original
 * value.
 */
import java.math.BigDecimal;
import java.math.BigInteger;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.gigs2go.money.calculator.Allocator;

public class BackLoadedAllocatorImpl implements Allocator {

    public Money[] allocate ( Money money, int len ) {
        Money[] result = new Money[len];
        int scale = money.getScale();
        CurrencyUnit currency = money.getCurrencyUnit();

        // Integral ops only
        BigInteger amount = money.getAmount().unscaledValue();
        BigInteger[] divAndR = amount.divideAndRemainder( BigInteger.valueOf( len ) );
        int remainder = divAndR[1].intValue();
        BigDecimal lowResult = BigDecimal.valueOf( divAndR[0].longValue() ).movePointLeft( scale );
        BigDecimal highResult = BigDecimal.valueOf( divAndR[0].longValue() + 1 ).movePointLeft( scale );
        for ( int i = 0; i < remainder; i++ ) {
            result[len - i - 1] = Money.of( currency, highResult );
        }
        for ( int i = remainder; i < len; i++ ) {
            result[len - i - 1] = Money.of( currency, lowResult );
        }
        return result;

    }
}
