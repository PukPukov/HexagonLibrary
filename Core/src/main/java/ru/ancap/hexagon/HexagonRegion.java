package ru.ancap.hexagon;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.MODULE)
@EqualsAndHashCode @ToString
public class HexagonRegion {
    
    private final HexagonalGrid grid;
    private final Set<Hexagon> hexagons;

    public HexagonalGrid grid() {return this.grid;}
    public Set<Hexagon> hexagons() {return this.hexagons;}
    
    public Set<HexagonSide> bounds() {
        List<HexagonSide> sides = new ArrayList<>();
        for (Hexagon hexagon : this.hexagons) sides.addAll(hexagon.sides());
        sides = sides.stream().map(HexagonSide::absolute).collect(Collectors.toList());
        Set<HexagonSide> duplicateChecker = new HashSet<>();
        Set<HexagonSide> duplicateSides = new HashSet<>();
        
        for(HexagonSide side : sides) {
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
