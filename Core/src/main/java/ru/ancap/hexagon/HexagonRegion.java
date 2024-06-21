package ru.ancap.hexagon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record HexagonRegion(HexagonalGrid grid, Set<Hexagon> hexagons) {
    
    public Set<HexagonSide> bounds() {
        List<HexagonSide> sides = new ArrayList<>();
        for (Hexagon hexagon : this.hexagons) sides.addAll(hexagon.sides());
        sides = sides.stream().map(HexagonSide::absolute).collect(Collectors.toList());
        Set<HexagonSide> duplicateChecker = new HashSet<>();
        Set<HexagonSide> duplicateSides = new HashSet<>();
        
        for (HexagonSide side : sides) {
            if (duplicateChecker.contains(side)) duplicateSides.add(side);
            duplicateChecker.add(side);
        }
        
        Set<HexagonSide> returnSides = new HashSet<>(sides);
        returnSides.removeAll(duplicateSides);
        
        return returnSides;
    }
    
    public boolean contains(Hexagon hexagon) {
        return this.hexagons.contains(hexagon);
    }
    
}