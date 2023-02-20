package ru.ancap.hexagon;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.MODULE)
@EqualsAndHashCode @ToString
public class HexagonRegion {
    
    private final HexagonalGrid grid;
    private final Set<Hexagon> hexagons;

    public HexagonalGrid grid() {return this.grid;}
    public Set<Hexagon> hexagons() {return this.hexagons;}
    
    public Set<HexagonSide> bounds() {
        /*List<HexagonSide> sides = new ArrayList<>();
        for (Hexagon hexagon : this.hexagons) sides.addAll(hexagon.sides());
        sides = sides.stream().map(HexagonSide::absolute).collect(Collectors.toList());
        List<HexagonSide> operateSides = new ArrayList<>(sides);
        Set<HexagonSide> identityChecker = new HashSet<>();
        for(HexagonSide side : sides) {
            if (identityChecker.contains(side)) operateSides.remove(side);
            else identityChecker.add(side);
        }
        return new HashSet<>(operateSides);
        
         */
        ArrayList<HexagonSide> sides = new ArrayList<>();
        Hexagon[] hexagons1 = this.hexagons.toArray(new Hexagon[0]);
        for (int i = 0; i<hexagons1.length; i++) {
            HexagonSide[] hexagonSides = hexagons1[i].sides().toArray(new HexagonSide[0]);
            for (int j = 0; j<hexagonSides.length; j++) {
                hexagonSides[j] = hexagonSides[j].absolute();
                sides.add(hexagonSides[j]);
            }
        }
        for(int i=0; i<sides.size(); i++) {
            for (int j=i+1; j<sides.size(); j++) {
                if (sides.get(i) == null || sides.get(j) == null) {
                    continue;
                }
                if(sides.get(i).equals(sides.get(j))) {
                    sides.set(i, null);
                    sides.set(j, null);
                }
            }
        }
        sides.removeAll(Collections.singleton(null));
        return new HashSet<>(sides);
    }

    public boolean contains(Hexagon hexagon) {
        return this.hexagons.contains(hexagon);
    }
    
}
