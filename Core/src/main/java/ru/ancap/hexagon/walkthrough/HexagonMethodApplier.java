package ru.ancap.hexagon.walkthrough;

import ru.ancap.algorithm.walkthrough.NodeMethodApplier;
import ru.ancap.hexagon.Hexagon;

import java.util.Set;

public class HexagonMethodApplier implements NodeMethodApplier<Hexagon> {
    
    public static HexagonMethodApplier INSTANCE = new HexagonMethodApplier();
    
    /* Shouldn't instantiate, should be used by provided instance */
    private HexagonMethodApplier() {}
    
    @Override
    public Set<Hexagon> neighbors(Hexagon hexagon) {
        return hexagon.neighbors(1);
    }
    
    @Override
    public boolean equals(Hexagon hexagon, Hexagon compared) {
        return hexagon.equals(compared);
    }
    
}