package com.gigs2go.money.calculator;

import java.math.BigDecimal;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public interface Constants {
    public static final CurrencyUnit GBP = CurrencyUnit.of( "GBP" );
    public static final CurrencyUnit JPY = CurrencyUnit.of( "JPY" );

    public static final BigDecimal BIGDEC_ZERO = new BigDecimal( "0" );
    public static final BigDecimal BIGDEC_3 = new BigDecimal( "3" );
    public static final BigDecimal BIGDEC_2_3 = new BigDecimal( "2.3" );
    public static final BigDecimal BIGDEC_2_34 = new BigDecimal( "2.34" );
    public static final BigDecimal BIGDEC_0_345678 = new BigDecimal( "0.345678" );
    public static final BigDecimal BIGDEC_2_345 = new BigDecimal( "2.345" );
    public static final BigDecimal BIGDEC_M5_78 = new BigDecimal( "-5.78" );

    public static final Integer INT_ZERO = new Integer( 0 );
    public static final Integer INT_3 = new Integer( 3 );

    public static final Money GBP_0_00 = Money.parse( "GBP 0.00" );
    public static final Money GBP_1_15 = Money.parse( "GBP 1.15" );
    public static final Money GBP_1_16 = Money.parse( "GBP 1.16" );
    public static final Money GBP_1_23 = Money.parse( "GBP 1.23" );
    public static final Money GBP_2_10 = Money.parse( "GBP 2.10" );
    public static final Money GBP_2_33 = Money.parse( "GBP 2.33" );
    public static final Money GBP_3_33 = Money.parse( "GBP 3.33" );
    public static final Money GBP_3_34 = Money.parse( "GBP 3.34" );
    public static final Money GBP_4_56 = Money.parse( "GBP 4.56" );
    public static final Money GBP_5_00 = Money.parse( "GBP 5.00" );
    public static final Money GBP_7_80 = Money.parse( "GBP 7.80" );
    public static final Money GBP_7_81 = Money.parse( "GBP 7.81" );
    public static final Money GBP_9_99 = Money.parse( "GBP 9.99" );
    public static final Money GBP_10_00 = Money.parse( "GBP 10.00" );
    public static final Money GBP_10_01 = Money.parse( "GBP 10.01" );
    public static final Money GBP_10_02 = Money.parse( "GBP 10.02" );
    public static final Money GBP_1243_21 = Money.parse( "GBP 1243.29" );
    public static final Money GBP_95_63 = Money.parse( "GBP 95.63" );
    public static final Money GBP_95_64 = Money.parse( "GBP 95.64" );

    /* Remainders */
    public static final BigMoney BIG_GBP_M0_00115 = BigMoney.parse( "GBP -0.00115" );
    public static final BigMoney BIG_GBP_M0_00889226 = BigMoney.parse( "GBP -0.00889226" );
    public static final BigMoney BIG_GBP_0_00885 = BigMoney.parse( "GBP 0.00885" );
    public static final BigMoney BIG_GBP_0_00110774 = BigMoney.parse( "GBP 0.00110774" );

    /* Yen */
    public static final Money JPY_000 = Money.parse( "JPY 000" );
    public static final Money JPY_1000 = Money.parse( "JPY 1000" );
    public static final Money JPY_333 = Money.parse( "JPY 333" );
    public static final Money JPY_334 = Money.parse( "JPY 334" );
    public static final Money JPY_500 = Money.parse( "JPY 500" );
}
