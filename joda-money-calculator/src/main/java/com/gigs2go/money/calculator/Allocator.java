package com.gigs2go.money.calculator;

import org.joda.money.Money;

public interface Allocator {
    Money[] allocate ( Money money, int len );
}
