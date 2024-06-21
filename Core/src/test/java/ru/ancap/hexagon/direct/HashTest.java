package ru.ancap.hexagon.direct;

import org.junit.jupiter.api.Test;
import ru.ancap.hexagon.*;
import ru.ancap.hexagon.common.Figure;
import ru.ancap.hexagon.common.Point;

import java.security.SecureRandom;
import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashTest {
    
    private final Random random = new SecureRandom();
    
    @Test
    public void test() {
        this.test(integer -> new Figure(List.of(new Point(integer, integer))));
        this.test(integer -> new HexagonalGrid(GridOrientation.FLAT, new Point(integer, integer), new Point(integer, integer)));
        this.test(integer -> new Hexagon(HexagonalGrid.CLASSIC, integer, integer));
        this.test(integer -> new Point(integer, integer));
        this.test(integer -> new GridOrientation(""+integer, new double[] {(double) integer}, new double[] {(double) integer}, (double) integer));
        this.test(integer -> new FractionalHexagon(HexagonalGrid.CLASSIC, (double) integer, (double) integer));
        this.test(integer -> HexagonalGrid.CLASSIC.region(Set.of(new Hexagon(HexagonalGrid.CLASSIC, integer, integer))));
        this.test(integer -> new HexagonSide(new Hexagon(HexagonalGrid.CLASSIC, integer, integer), integer));
        this.test(integer -> new HexagonVertex(new Hexagon(HexagonalGrid.CLASSIC, integer, integer), integer));
    }
    
    private <T> void test(Function<Integer, T> supplier) {
        int count = this.random.nextInt(50);
        Set<T> set = new HashSet<>();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            set.add(supplier.apply(i));
            list.add(supplier.apply(i));
        }
        for (int i = 0; i < count; i++) assertTrue(set.containsAll(list));
        assertEquals(set.size(), list.size(), "Set = \""+set+"\", list = \""+list+"\"");
        assertEquals(set.size(), count,       "Set = \""+set+"\", list = \""+list+"\", count = \""+count+"\", set.size() = \""+set.size()+"\", list.size() = \""+list.size()+"\"");
    }
    
}
