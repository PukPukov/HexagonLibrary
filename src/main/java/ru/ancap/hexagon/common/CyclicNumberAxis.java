package ru.ancap.hexagon.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode @ToString
public class CyclicNumberAxis {
    
    public static CyclicNumberAxis HEXAGONAL = new CyclicNumberAxis(6);
    
    private final int period;
    
    public CyclicNumberAxis(int period) {
        if (period < 0) throw new IllegalArgumentException("Period can not be smaller than 0");
        this.period = period;
    }

    public int offset(int base, int steps) {
        if (base+1 > this.period) throw new IllegalArgumentException("Base can not be outside the borders!");

        int offset = steps % this.period;

        int newBase = base + offset;
        if (newBase >= this.period) newBase -= this.period;
        else if (newBase < 0) newBase += this.period;

        return newBase;
    }
    
}
