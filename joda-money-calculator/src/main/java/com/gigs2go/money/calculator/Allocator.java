package com.gigs2go.money.calculator;

import org.joda.money.Money;

/**
 * <p>
 * An Allocator allocates a Money object across the specified number of
 * divisions, and deals with any 'leftovers' according to it's strategy.<br/>
 * </p>
 * 
 * @see Money
 */
public interface Allocator {
    /**
     * Allocates the given amount of Money across the number of divisions given
     * by 'len', according to it's strategy.
     * 
     * @param money
     *            The Money to allocate
     * @param len
     *            The number of divisions to use
     * @return Specified by the implementing strategy
     */
    Money[] allocate ( Money money, int len );
}
