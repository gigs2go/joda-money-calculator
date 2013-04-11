package com.gigs2go.money.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * <p>
 * A MoneyCalculator is a stateful representation of a series of operations with
 * Money objects.<br/>
 * </p>
 * <p>
 * All internal calculations are done using variable-precision BigMoney's, so no
 * rounding is performed during a sequence.
 * </p>
 * <p>
 * There is <b>NO</b> provision for division! If you wish to divide a Money,
 * please use a BigDecimal multiplier. This is because division can result in
 * 'non-terminating decimal expansions'.<br/>
 * Consequently, to divide by a number, create a BigDecimal by using the inverse
 * and specifying the required scale and rounding mode -<br/>
 * <code><br/>
 *   BigDecimal oneThird = new BigDecimal(1).divide(new BigDecimal(3),5,RoundingMode.UP);<br/>
 *   MoneyCalculator.multiply(oneThird);<br/>
 * </code>
 * <p>
 * When result() is called, the currently active RoundingMode is applied. If
 * result(RoundingMode) is used, the given RoundingMode will be applied. Note
 * that the remainder may be negative (depending on the chosen RoundingMode).
 * </p>
 * <p>
 * Once the Calculator has been set - either explicitly or implicitly - all
 * further operations are performed using the same CurrencyUnit (where
 * applicable). Any other CurrencyUnit will result in a
 * CurrencyMismatchException.
 * </p>
 * 
 * @see {@link Money}
 * @see {@link BigMoney}
 * @see {@link CurrencyUnit}
 * @see {@link BigDecimal}
 * @see {@link RoundingMode}
 */
public class MoneyCalculator {
    PartialResult currentResult = null;

    /**
     * Create a new MoneyCalculator with a default RoundingMode of 'HALF_UP'
     */
    public MoneyCalculator() {
        this.currentResult = new PartialResult( RoundingMode.HALF_UP );
    }

    /**
     * Create a new MoneyCalculator with the given RoundingMode
     * 
     * @param roundingMode
     */
    public MoneyCalculator( RoundingMode roundingMode ) {
        this.currentResult = new PartialResult( roundingMode );
    }

    /**
     * Clear the current state of the Calculator
     * 
     * @return A newly initialised {@link MoneyCalculator}
     */
    public MoneyCalculator clear () {
        this.currentResult = new PartialResult( this.currentResult.roundingMode );
        return this;
    }

    /**
     * Allocate the given amount of {@link Money} into 'len' amounts using the
     * given {@link Allocator}
     * 
     * @param allocator
     *            The {@link Allocator} to use
     * @param money
     *            The amount of money to allocate
     * @param len
     *            The number of units to allocate the money to
     * @return The resulting number of {@link Money}'s representing the desired
     *         allocation
     */
    public Money[] allocate ( Allocator allocator, Money money, int len ) {
        if ( !(len > 0) ) {
            throw new IllegalArgumentException( "len must be greater than zero : " + len );
        }
        if ( money == null ) {
            throw new IllegalArgumentException( "money must not be null" );
        }
        return allocator.allocate( money, len );
    }

    /**
     * Set the current state of the Calculator. Implicitly calls
     * {@link #clear()}, and then sets the initial state.
     * 
     * @return An initialised {@link MoneyCalculator}
     */
    public MoneyCalculator set ( Money money ) {
        checkNotNull( money );
        this.clear();
        this.currentResult.value = money.toBigMoney();
        return this;
    }

    /**
     * Obtains the result of any/all preceding calculations since the last
     * {@link #clear()}
     * 
     * @return the result
     */
    public FinalResult result () {
        return currentResult.toResult();
    }

    /**
     * Obtains the result of any/all preceding calculations since the last
     * {@link #clear()}
     * 
     * @param roundingMode
     *            The RoundingMode used to calculate the result. See
     *            {@link BigDecimal} for details.
     * 
     * @return the result
     */
    public FinalResult result ( RoundingMode roundingMode ) {
        return currentResult.toResult( roundingMode );
    }

    /**
     * Performs an implicit {@link #clear()}, {@link #set(Money)},
     * {@link #add(Money)}
     * 
     * @param money1
     *            The initial value for the Calculator
     * @param money2
     *            The amount to add to the initial value
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator add ( Money money1, Money money2 ) {
        checkNotNull( money1 );
        checkNotNull( money2 );
        this.clear();
        this.set( money1 );
        return this.add( money2 );
    }

    /**
     * Add the provided amount to the current value
     * 
     * @param amount
     *            The amount to add
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator add ( Money amount ) {
        checkNotNull( amount );
        this.currentResult.value = this.currentResult.value.plus( amount );
        return this;
    }

    /**
     * Performs an implicit {@link #clear()}, {@link #set(Money)},
     * {@link #subtract(Money)}
     * 
     * @param from
     *            The initial value for the Calculator
     * @param amount
     *            The amount to subtract from the initial value
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator subtract ( Money from, Money amount ) {
        checkNotNull( from );
        checkNotNull( amount );
        this.clear();
        this.set( from );
        return this.subtract( amount );
    }

    /**
     * Subtract the provided amount from the current value
     * 
     * @param amount
     *            The amount to subtract
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator subtract ( Money amount ) {
        checkNotNull( amount );
        this.currentResult.value = this.currentResult.value.minus( amount );
        return this;
    }

    /**
     * Performs an implicit {@link #clear()}, {@link #set(Money)},
     * {@link #multiply(Integer)}
     * 
     * @param money
     *            The initial value for the Calculator
     * @param by
     *            The amount to multiply the initial value by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator multiply ( Money money, Integer by ) {
        checkNotNull( money );
        checkNotNull( by );
        this.clear();
        this.set( money );
        return this.multiply( by );
    }

    /**
     * Multiplies the current amount by the given Integer
     * 
     * @param by
     *            The value to multiply the current amount by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator multiply ( Integer by ) {
        checkNotNull( by );
        this.currentResult.value = this.currentResult.value.multipliedBy( by.longValue() );
        return this;
    }

    /**
     * Multiplies the current amount by the given BigDecimal
     * 
     * @param by
     *            The value to multiply the current amount by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator multiply ( BigDecimal by ) {
        checkNotNull( by );
        this.currentResult.value = this.currentResult.value.multipliedBy( by );
        return this;
    }

    /**
     * Validates that the money object specified is not null
     * 
     * @param money
     *            the object to check, not null
     * @throws NullPointerException
     *             if the input value is null
     */
    private void checkNotNull ( Money money ) {
        if ( money == null ) {
            throw new IllegalArgumentException( "Money value may not be null" );
        }
    }

    /**
     * Validates that the Integer object specified is not null
     * 
     * @param integer
     *            the object to check, not null
     * @throws NullPointerException
     *             if the input value is null
     */
    private void checkNotNull ( Integer integer ) {
        if ( integer == null ) {
            throw new IllegalArgumentException( "Integer value may not be null" );
        }
    }

    /**
     * Validates that the BigDecimal object specified is not null
     * 
     * @param integer
     *            the object to check, not null
     * @throws NullPointerException
     *             if the input value is null
     */
    private void checkNotNull ( BigDecimal integer ) {
        if ( integer == null ) {
            throw new IllegalArgumentException( "BigDecimal value may not be null" );
        }
    }

    class FinalResult {
        private Money value = null;
        private BigMoney remainder = null;

        /**
         * @return the value
         */
        public Money getValue () {
            return value;
        }

        /**
         * @return the remainder
         */
        public BigMoney getRemainder () {
            return remainder;
        }
    }

    private class PartialResult {
        BigMoney value = null;
        RoundingMode roundingMode = null;

        PartialResult( RoundingMode roundingMode ) {
            this.roundingMode = roundingMode;
        }

        /**
         * @return the result using the initial RoundingMode of the
         *         MoneyCalculator
         */
        FinalResult toResult () {
            FinalResult result = toResult( this.roundingMode );
            return result;
        }

        /**
         * @param roundingMode
         *            The BigDecimal RoundingMode to use
         * @return the result. NB The remainder may be negative depending on the
         *         value and the RoundingMode
         */
        FinalResult toResult ( RoundingMode roundingMode ) {
            FinalResult result = new FinalResult();
            result.value = (value != null ? value.withCurrencyScale( roundingMode ).toMoney() : null);
            result.remainder = (value != null ? value.minus( result.value ) : null);
            return result;
        }

    }
}
