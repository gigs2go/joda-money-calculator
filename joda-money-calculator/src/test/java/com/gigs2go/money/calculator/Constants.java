package com.gigs2go.money.calculator;

import java.math.BigDecimal;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public interface Constants {
    public static final CurrencyUnit GBP = CurrencyUnit.of( "GBP" );
    public static final CurrencyUnit JPY = CurrencyUnit.of( "JPY" );

    public static final BigDecimal BIGDEC_0 = new BigDecimal( "0" );
    public static final BigDecimal BIGDEC_3 = new BigDecimal( "3" );
    public static final BigDecimal BIGDEC_2_3 = new BigDecimal( "2.3" );
    public static final BigDecimal BIGDEC_2_34 = new BigDecimal( "2.34" );
    public static final BigDecimal BIGDEC_3_33 = new BigDecimal( "3.33" );
    public static final BigDecimal BIGDEC_0_345678 = new BigDecimal( "0.345678" );
    public static final BigDecimal BIGDEC_2_345 = new BigDecimal( "2.345" );
    public static final BigDecimal BIGDEC_M5_78 = new BigDecimal( "-5.78" );

    public static final long LONG_0 = 0L;
    public static final long LONG_3 = 3L;
    public static final long LONG_6 = 6L;

    public static final double DOUBLE_0_0 = 0.0;
    public static final double DOUBLE_3_0 = 3.0;
    public static final double DOUBLE_3_33 = 3.33;
    public static final double DOUBLE_3_00000 = 3.00000;

    public static final Money GBP_0_00 = Money.parse( "GBP 0.00" );
    public static final Money GBP_0_01 = Money.parse( "GBP 0.01" );
    public static final Money GBP_1_11 = Money.parse( "GBP 1.11" );
    public static final Money GBP_1_15 = Money.parse( "GBP 1.15" );
    public static final Money GBP_1_16 = Money.parse( "GBP 1.16" );
    public static final Money GBP_1_23 = Money.parse( "GBP 1.23" );
    public static final Money GBP_1_66 = Money.parse( "GBP 1.66" );
    public static final Money GBP_1_67 = Money.parse( "GBP 1.67" );
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
    public static final Money GBP_14_55 = Money.parse( "GBP 14.55" );
    public static final Money GBP_95_63 = Money.parse( "GBP 95.63" );
    public static final Money GBP_95_64 = Money.parse( "GBP 95.64" );
    public static final Money GBP_122_96 = Money.parse( "GBP 122.96" );
    public static final Money GBP_245_92 = Money.parse( "GBP 245.92" );
    public static final Money GBP_245_93 = Money.parse( "GBP 245.93" );
    public static final Money GBP_1243_21 = Money.parse( "GBP 1243.29" );

    /* Remainders */
    public static final BigMoney BIG_GBP_M0_00115 = BigMoney.parse( "GBP -0.00115" );
    public static final BigMoney BIG_GBP_M0_00259 = BigMoney.parse( "GBP -0.00259" );
    public static final BigMoney BIG_GBP_M0_00259358 = BigMoney.parse( "GBP -0.00259358" );
    public static final BigMoney BIG_GBP_M0_00333333 = BigMoney.parse( "GBP -0.00333333" );
    public static final BigMoney BIG_GBP_M0_00890 = BigMoney.parse( "GBP -0.00890" );
    public static final BigMoney BIG_GBP_0_00000 = BigMoney.parse( "GBP 0.00000" );
    public static final BigMoney BIG_GBP_0_00885 = BigMoney.parse( "GBP 0.00885" );
    public static final BigMoney BIG_GBP_0_00110774 = BigMoney.parse( "GBP 0.00110774" );
    public static final BigMoney BIG_GBP_0_00110 = BigMoney.parse( "GBP 0.00110" );
    public static final BigMoney BIG_GBP_0_00111 = BigMoney.parse( "GBP 0.00111" );
    public static final BigMoney BIG_GBP_0_00333 = BigMoney.parse( "GBP 0.00333" );
    public static final BigMoney BIG_GBP_0_00333333 = BigMoney.parse( "GBP 0.00333333" );
    public static final BigMoney BIG_GBP_0_00370 = BigMoney.parse( "GBP 0.00370" );
    public static final BigMoney BIG_GBP_0_00740642 = BigMoney.parse( "GBP 0.00740642" );
    public static final BigMoney BIG_GBP_0_00666666 = BigMoney.parse( "GBP 0.00666666" );
    public static final BigMoney BIG_GBP_0_00666667 = BigMoney.parse( "GBP 0.00666667" );
    public static final BigMoney BIG_GBP_0_00741 = BigMoney.parse( "GBP 0.00741" );

    /* Yen */
    public static final Money JPY_000 = Money.parse( "JPY 000" );
    public static final Money JPY_1000 = Money.parse( "JPY 1000" );
    public static final Money JPY_333 = Money.parse( "JPY 333" );
    public static final Money JPY_334 = Money.parse( "JPY 334" );
    public static final Money JPY_500 = Money.parse( "JPY 500" );
}
