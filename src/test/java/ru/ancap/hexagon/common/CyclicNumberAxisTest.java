package ru.ancap.hexagon.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CyclicNumberAxisTest {
    
    
    @Test
    public void test() {
        assertEquals(2, new CyclicNumberAxis(10).offset(-3,  5));
        assertEquals(7, new CyclicNumberAxis(10).offset( 5,  12));
        assertEquals(2, new CyclicNumberAxis(10).offset( 5, -3));
        assertEquals(1, new CyclicNumberAxis(6) .offset( 4, -3));
        assertEquals(5, new CyclicNumberAxis(10).offset( 5,  0));
        
        assertThrows(IllegalArgumentException.class, () -> new CyclicNumberAxis(12).offset(12, 3)); 
        assertThrows(IllegalArgumentException.class, () -> new CyclicNumberAxis(1) .offset(5,  10));
    }
    
}
