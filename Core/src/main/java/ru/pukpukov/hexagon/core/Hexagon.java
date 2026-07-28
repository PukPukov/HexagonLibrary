package ru.pukpukov.hexagon.core;

import ru.pukpukov.commons.Pair;
import ru.pukpukov.commons.compact.Morton64Compactor;
import ru.pukpukov.hexagon.core.common.Point;

import java.util.List;
import java.util.*;

public record Hexagon(HexagonalGrid grid, long q, long r) {
    
    public long s() {
        return -(this.q + this.r);
    }
    
    public long code() {
        Morton64Compactor mort = this.grid.morton();
        return mort.pack(this.q, this.r);
    }
    
    public Point center() {
        GridOrientation gridOrientation = this.grid.orientation();
        Point size = this.grid.size();
        Point origin = this.grid.origin();
        double x = (gridOrientation.f()[0] * this.q + gridOrientation.f()[1] * this.r) * size.x() + origin.x();
        double y = (gridOrientation.f()[2] * this.q + gridOrientation.f()[3] * this.r) * size.y() + origin.y();
        return new Point(x, y);
    }
    
    public HexagonSide side(int index) {
        return new HexagonSide(this, index);
    }
    
    public Set<HexagonSide> sides() {
        Set<HexagonSide> hexagonSides = new HashSet<>();
        for (int i = 0; i < 6; i++) {
            hexagonSides.add(this.side(i));
        }
        return hexagonSides;
    }
    
    public HexagonVertex vertex(int index) {
        return new HexagonVertex(this, index);
    }
    
    public List<HexagonVertex> vertexes() {
        List<HexagonVertex> hexagonVertexes = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            hexagonVertexes.add(this.vertex(i));
        }
        return hexagonVertexes;
    }
    
    public Hexagon neighbor(int index) {
        Pair<Integer, Integer> modifier = this.grid.orientation().modifiers().get(index);
        return new Hexagon(this.grid, this.q + modifier.a(), this.r + modifier.b());
    }
    
    public Set<Hexagon> neighbors(int layers) {
        Set<Hexagon> neighbors = new HashSet<>();
        for (long q = -layers; q <= layers; q++) {
            long r1 = Math.max(-layers, -q - layers);
            long r2 = Math.min(layers, -q + layers);
            for (long r = r1; r <= r2; r++) {
                if (q == 0 && r == 0) {
                    continue;
                }
                neighbors.add(new Hexagon(this.grid, q + this.q, r + this.r));
            }
        }
        return neighbors;
    }
    
    public boolean neighborOf(Hexagon hexagon) {
        return this.neighbors(1).contains(hexagon);
    }
    
    @Override
    public String toString() {
        return this.q+";"+this.r;
    }
    
}