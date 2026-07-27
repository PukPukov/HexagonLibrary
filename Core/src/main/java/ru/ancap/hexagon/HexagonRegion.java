package ru.ancap.hexagon;

import ru.ancap.hexagon.common.Figure;
import ru.ancap.hexagon.common.Point;

import java.util.*;
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
    
    /**
     * Returns a list of figures that formed by set of hexagons.
     * <p>
     * So, for example, if these hexagons forming two separate areas that do not connect,
     * it returns list of two elements, and if everything is inside
     * one continuous region only list of one element will be returned.
     */
    public List<Figure> collapse() {
        System.out.println("Starting collapse operation...");
        
        List<Figure> figures = new ArrayList<>();
        
        // Step 1: Identify connected components
        List<Set<Hexagon>> connectedComponents = findConnectedComponents();
        
        System.out.println("Found " + connectedComponents.size() + " connected components.");
        
        // Step 2: For each component, find boundary sides and order them
        for (Set<Hexagon> component : connectedComponents) {
            System.out.println("Processing a component with " + component.size() + " hexagons...");
            HexagonRegion subRegion = new HexagonRegion(grid, component);
            Set<HexagonSide> boundarySides = subRegion.bounds();
            List<Point> orderedVertices = orderBoundaryVertices(boundarySides);
            System.out.println("Boundary vertices ordered: " + orderedVertices);
            
            // Step 3: Create Figure from ordered vertices
            Figure figure = new Figure(orderedVertices);
            figures.add(figure);
        }
        
        System.out.println("Collapse operation completed with " + figures.size() + " figures.");
        return figures;
    }
    
    private List<Set<Hexagon>> findConnectedComponents() {
        List<Set<Hexagon>> components = new ArrayList<>();
        Set<Hexagon> visited = new HashSet<>();
        
        for (Hexagon hex : hexagons) {
            if (!visited.contains(hex)) {
                Set<Hexagon> component = new HashSet<>();
                Queue<Hexagon> queue = new LinkedList<>();
                queue.add(hex);
                
                while (!queue.isEmpty()) {
                    Hexagon current = queue.poll();
                    if (!visited.contains(current)) {
                        visited.add(current);
                        component.add(current);
                        
                        for (Hexagon neighbor : current.neighbors(1)) {
                            if (hexagons.contains(neighbor) && !visited.contains(neighbor)) {
                                queue.add(neighbor);
                            }
                        }
                    }
                }
                
                components.add(component);
            }
        }
        
        return components;
    }
    
    private List<Point> orderBoundaryVertices(Set<HexagonSide> boundarySides) {
        System.out.println("Ordering boundary vertices for " + boundarySides.size() + " sides...");
        
        List<Point> orderedVertices = new ArrayList<>();
        if (boundarySides.isEmpty()) {
            return orderedVertices;
        }
        
        // Maps to store the connections between vertices and sides
        Map<HexagonVertex, HexagonVertex> vertexConnections = new HashMap<>();
        Map<HexagonVertex, Point> vertexToPoint = new HashMap<>();
        
        for (HexagonSide side : boundarySides) {
            HexagonVertex start = side.start().absolute();
            HexagonVertex end = side.end().absolute();
            vertexConnections.put(start, end);
            vertexToPoint.put(start, start.position());
            vertexToPoint.put(end, end.position());
        }
        
        // Start with an arbitrary side
        HexagonVertex currentVertex = boundarySides.iterator().next().start().absolute();
        HexagonVertex startVertex = currentVertex;
        
        do {
            orderedVertices.add(vertexToPoint.get(currentVertex));
            currentVertex = vertexConnections.get(currentVertex);
        } while (currentVertex != null && !currentVertex.equals(startVertex));
        
        return orderedVertices;
    }
    
    public boolean contains(Hexagon hexagon) {
        return this.hexagons.contains(hexagon);
    }
    
}