package com.gigs2go.money.calculator.allocators;

import com.gigs2go.money.calculator.Allocator;

public enum AllocatorStrategy {
    FRONT_LOADING( new FrontLoadedAllocatorImpl() ), BACK_LOADING( new BackLoadedAllocatorImpl() );

    private Allocator allocator;

    AllocatorStrategy( Allocator allocator ) {
        this.allocator = allocator;
    }

    /**
     * @return the allocator
     */
    public Allocator getAllocator () {
        return allocator;
    }

}
