package com.gigs2go.money.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

/**
 * <p>
 * A MoneyCalculator is a stateful representation of a sequence of operations
 * with Money objects.<br/>
 * It is <b>NOT</b> thread-safe, and should used appropriately.<br/>
 * Internally, operations are performed using (at least) the 'remainderScale',
 * which is initially set to the scale of the CurrencyUnit of the Money being
 * calculated + 3, and the RoundingMode of HALF_UP.<br/>
 * Either of these can be changed.
 * </p>
 * When result() is called, the currently active RoundingMode is applied. If
 * result(RoundingMode) is used, the given RoundingMode will be applied. Note
 * that the remainder may be negative (depending on the chosen RoundingMode).
 * The Result.value is a Money object appropriately scaled for the CurrencyUnit.
 * The Result.remainder is a BigMoney object scaled at the Calculator's
 * remainderScale (rounded using the RoundingMode)</p>
 * <p>
 * All internal calculations are done using variable-precision BigMoney's.
 * </p>
 * <p>
 * Once the Calculator has been set - either explicitly or implicitly - all
 * further operations are performed using the same CurrencyUnit (where
 * applicable). Any other CurrencyUnit will result in a
 * CurrencyMismatchException (as per Money).<br/>
 * NullPointerExceptions will be thrown if necessary.
 * </p>
 * 
 * @see Money
 * @see BigMoney
 * @see CurrencyUnit
 * @see BigDecimal
 * @see RoundingMode
 */
public class MoneyCalculator {
    private static final int REMAINDER_SCALE = 3;

    private BigMoney currentValue = null;
    private RoundingMode roundingMode = RoundingMode.HALF_UP;
    private int remainderScale = REMAINDER_SCALE;

    /**
     * Create a new MoneyCalculator with a default remainderScale of 3 and a
     * default RoundingMode of 'HALF_UP'
     */
    public MoneyCalculator() {
        this( REMAINDER_SCALE, RoundingMode.HALF_UP );
    }

    /**
     * Create a new MoneyCalculator with a default remainderScale of 3 and the
     * given RoundingMode
     * 
     * @param roundingMode
     */
    public MoneyCalculator( RoundingMode roundingMode ) {
        this( REMAINDER_SCALE, roundingMode );
    }

    /**
     * Create a new MoneyCalculator with the given remainderScale and
     * RoundingMode
     * 
     * @param roundingMode
     */
    public MoneyCalculator( int remainderScale, RoundingMode roundingMode ) {
        this.setRemainderScale( remainderScale );
        this.roundingMode = roundingMode;
    }

    /**
     * Clears the Calculator value - RemainderScale and RoundingMode are
     * retained
     * 
     * @return A newly initialised {@link MoneyCalculator}
     */
    public MoneyCalculator clear () {
        currentValue = null;
        return this;
    }

    /**
     * Increases the remainderScale if the given one is bigger than the current
     * one
     * 
     * @param remainderScale
     *            The new remainder scale
     */
    public void setRemainderScale ( int remainderScale ) {
        if ( remainderScale > this.remainderScale ) {
            this.remainderScale = remainderScale;
            if ( this.currentValue != null ) {
                this.currentValue = this.currentValue.withScale( this.remainderScale );
            }
        }
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
        this.clear();
        int scale = money.getScale() + REMAINDER_SCALE;
        if ( remainderScale > scale ) {
            scale = remainderScale;
        }
        this.currentValue = money.toBigMoney().withScale( scale );
        this.setRemainderScale( this.currentValue.getScale() );
        return this;
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
        this.currentValue = this.currentValue.plus( amount );
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
        this.currentValue = this.currentValue.minus( amount );
        return this;
    }

    /**
     * Performs an implicit {@link #clear()}, {@link #set(Money)},
     * {@link #multiply(long)}
     * 
     * @param money
     *            The initial value for the Calculator
     * @param by
     *            The amount to multiply the initial value by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator multiply ( Money money, long by ) {
        this.clear();
        this.set( money );
        return this.multiply( by );
    }

    /**
     * Performs an implicit {@link #clear()}, {@link #set(Money)},
     * {@link #multiply(double)}
     * 
     * @param money
     *            The initial value for the Calculator
     * @param by
     *            The amount to multiply the initial value by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator multiply ( Money money, double by ) {
        this.clear();
        this.set( money );
        return this.multiply( by );
    }

    /**
     * Performs an implicit {@link #clear()}, {@link #set(Money)},
     * {@link #multiply(BigDecimal)}
     * 
     * @param money
     *            The initial value for the Calculator
     * @param by
     *            The amount to multiply the initial value by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator multiply ( Money money, BigDecimal by ) {
        this.clear();
        this.set( money );
        return this.multiply( by );
    }

    /**
     * Multiplies the current amount by the given long
     * 
     * @param by
     *            The value to multiply the current amount by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator multiply ( long by ) {
        this.currentValue = this.currentValue.multipliedBy( by );
        return this;
    }

    /**
     * Multiplies the current amount by the given double
     * 
     * @param by
     *            The value to multiply the current amount by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator multiply ( double by ) {
        this.currentValue = this.currentValue.multipliedBy( by );
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
        this.currentValue = this.currentValue.multipliedBy( by );
        return this;
    }

    /**
     * Performs an implicit {@link #clear()}, {@link #set(Money)},
     * {@link #divide(long)}
     * 
     * @param money
     *            The initial value for the Calculator
     * @param by
     *            The amount to multiply the initial value by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator divide ( Money money, long by ) {
        this.clear();
        this.set( money );
        return this.divide( by );
    }

    /**
     * Performs an implicit {@link #clear()}, {@link #set(Money)},
     * {@link #divide(double)}
     * 
     * @param money
     *            The initial value for the Calculator
     * @param by
     *            The amount to multiply the initial value by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator divide ( Money money, double by ) {
        this.clear();
        this.set( money );
        return this.divide( by );
    }

    /**
     * Performs an implicit {@link #clear()}, {@link #set(Money)},
     * {@link #divide(BigDecimal)}
     * 
     * @param money
     *            The initial value for the Calculator
     * @param by
     *            The amount to multiply the initial value by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator divide ( Money money, BigDecimal by ) {
        this.clear();
        this.set( money );
        return this.divide( by );
    }

    /**
     * Divides the current amount by the given long
     * 
     * @param by
     *            The value to divide the current amount by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator divide ( long by ) {
        this.currentValue = this.currentValue.dividedBy( by, roundingMode );
        return this;
    }

    /**
     * Divides the current amount by the given double
     * 
     * @param by
     *            The value to divide the current amount by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator divide ( double by ) {
        this.currentValue = this.currentValue.dividedBy( by, roundingMode );
        return this;
    }

    /**
     * Divides the current amount by the given BigDecimal
     * 
     * @param by
     *            The value to divide the current amount by
     * @return The {@link MoneyCalculator} for further operations
     */
    public MoneyCalculator divide ( BigDecimal by ) {
        this.currentValue = this.currentValue.dividedBy( by, roundingMode );
        return this;
    }

    /**
     * Obtains the result of any/all preceding calculations since the last
     * {@link #clear()}
     * 
     * @return the result
     */
    public Result result () {
        return toResult( this.roundingMode );
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
    public Result result ( RoundingMode roundingMode ) {
        return toResult( roundingMode );
    }

    /**
     * @param roundingMode
     *            The BigDecimal RoundingMode to use
     * @return the result. NB The remainder may be negative depending on the
     *         value and the RoundingMode
     */
    private Result toResult ( RoundingMode roundingMode ) {
        Result result = new Result();
        result.value = (currentValue != null ? currentValue.withCurrencyScale( roundingMode ).toMoney() : null);
        result.remainder = (currentValue != null ? currentValue.minus( result.value ).withScale( remainderScale, roundingMode ) : null);
        return result;
    }

    class Result {
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

}
