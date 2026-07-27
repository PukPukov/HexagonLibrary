package ru.pukpukov.hexagon;

import ru.pukpukov.hexagon.common.Figure;
import ru.pukpukov.hexagon.common.Point;

import java.util.*;

@SuppressWarnings("all") // ai slop
public record HexagonRegion(HexagonalGrid grid, Set<Hexagon> hexagons) {
    
    public Set<HexagonSide> bounds() {
        Set<HexagonSide> boundarySides = new HashSet<>();
        for (Hexagon hexagon : this.hexagons) {
            for (int i = 0; i < 6; i++) {
                Hexagon neighbor = hexagon.neighbor(i);
                if (!this.hexagons.contains(neighbor)) {
                    // Если соседнего гексагона нет в нашем регионе, 
                    // то эта грань является внешней и сохраняет своё направление
                    boundarySides.add(hexagon.side(i));
                }
            }
        }
        return boundarySides;
    }
    
    /**
     * Returns a list of figures that formed by set of hexagons.
     * <p>
     * So, for example, if these hexagons forming two separate areas that do not connect,
     * it returns list of two elements, and if everything is inside
     * one continuous region only list of one element will be returned.
     */
    public List<Figure> collapse() {
        List<Figure> figures = new ArrayList<>();
        Set<Hexagon> visited = new HashSet<>();
        
        for (Hexagon hex : this.hexagons) {
            if (!visited.contains(hex)) {
                // Шаг 1: Находим все гексагоны, принадлежащие текущему компоненту связности
                Set<Hexagon> component = new HashSet<>();
                Queue<Hexagon> queue = new LinkedList<>();
                queue.add(hex);
                visited.add(hex);
                
                while (!queue.isEmpty()) {
                    Hexagon current = queue.poll();
                    component.add(current);
                    
                    for (int i = 0; i < 6; i++) {
                        Hexagon neighbor = current.neighbor(i);
                        if (this.hexagons.contains(neighbor) && !visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.add(neighbor);
                        }
                    }
                }
                
                // Шаг 2: Получаем направленные границы только для этого куска гексагонов
                Set<HexagonSide> componentBounds = new HexagonRegion(this.grid, component).bounds();
                
                // Шаг 3: Объединяем грани в замкнутые циклы
                List<List<Point>> cycles = this.extractCycles(componentBounds);
                
                // Шаг 4: Если внутри региона есть дырки - циклов будет несколько.
                // Выбираем внешний контур (тот, у которого есть самая "высокая" координата)
                if (!cycles.isEmpty()) {
                    List<Point> outerCycle = cycles.get(0);
                    double minY = Double.MAX_VALUE;
                    
                    for (List<Point> cycle : cycles) {
                        double currentMinY = cycle.stream().mapToDouble(Point::y).min().orElse(Double.MAX_VALUE);
                        if (currentMinY < minY) {
                            minY = currentMinY;
                            outerCycle = cycle;
                        }
                    }
                    
                    figures.add(new Figure(outerCycle));
                }
            }
        }
        
        return figures;
    }
    
    private List<List<Point>> extractCycles(Set<HexagonSide> boundarySides) {
        // Карта, связывающая абсолютную конечную вершину текущей грани 
        // с гранью, которая из нее исходит.
        Map<HexagonVertex, HexagonSide> nextEdgeMap = new HashMap<>();
        for (HexagonSide side : boundarySides) {
            nextEdgeMap.put(side.start().absolute(), side);
        }
        
        List<List<Point>> cycles = new ArrayList<>();
        Set<HexagonSide> unvisitedEdges = new HashSet<>(boundarySides);
        
        while (!unvisitedEdges.isEmpty()) {
            HexagonSide startSide = unvisitedEdges.iterator().next();
            List<Point> cycle = new ArrayList<>();
            
            HexagonSide currentSide = startSide;
            do {
                unvisitedEdges.remove(currentSide);
                cycle.add(currentSide.start().position());
                
                // Берем конец текущей грани в её каноничном виде, 
                // чтобы найти следующую грань, которая из неё начинается
                HexagonVertex nextVertex = currentSide.end().absolute();
                currentSide = nextEdgeMap.get(nextVertex);
                
                if (currentSide == null) {
                    System.err.println("Warning: Broken boundary cycle detected!");
                    break;
                }
            } while (currentSide != startSide); // Пока не замкнем цикл
            
            cycles.add(cycle);
        }
        
        return cycles;
    }
    
    public boolean contains(Hexagon hexagon) {
        return this.hexagons.contains(hexagon);
    }
    
}