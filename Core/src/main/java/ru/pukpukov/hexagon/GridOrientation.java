package ru.pukpukov.hexagon;

import lombok.*;
import lombok.experimental.Accessors;
import ru.pukpukov.commons.Pair;

import java.util.List;

@RequiredArgsConstructor
@EqualsAndHashCode @ToString
@Accessors(fluent = true) @Getter
public final class GridOrientation {
    
    public final static GridOrientation POINTY = new GridOrientation(
        "pointy",
        new double[] {Math.sqrt(3.0), Math.sqrt(3.0) / 2.0, 0.0, 3.0 / 2.0},
        new double[] {Math.sqrt(3.0) / 3.0, -1.0 / 3.0, 0.0, 2.0 / 3.0},
        0.5,
        List.of(
            new Pair<>( 0,  1),
            new Pair<>(-1,  1),
            new Pair<>(-1,  0),
            new Pair<>( 0, -1),
            new Pair<>( 1, -1),
            new Pair<>( 1,  0)
        )
    );
    
    public final static GridOrientation FLAT = new GridOrientation(
        "flat",
        new double[] {3.0 / 2.0, 0.0, Math.sqrt(3.0) / 2.0, Math.sqrt(3.0)},
        new double[] {2.0 / 3.0, 0.0, -1.0 / 3.0, Math.sqrt(3.0) / 3.0},
        0.0,
        List.of(
            new Pair<>( 1,  0),
            new Pair<>( 0,  1),
            new Pair<>(-1,  1),
            new Pair<>(-1,  0),
            new Pair<>( 0, -1),
            new Pair<>( 1, -1)
        )
    );
    
    private final String name;
    private final double[] f;
    private final double[] b;
    private final double startAngle;
    private final List<Pair<Integer, Integer>> modifiers;
    
    // cache
    @EqualsAndHashCode.Exclude @ToString.Exclude private final double[] sinuses;
    @EqualsAndHashCode.Exclude @ToString.Exclude private final double[] cosinuses;
    
    public GridOrientation(String name, double[] f, double[] b, double startAngle, List<Pair<Integer, Integer>> modifiers) {
        this.name = name;
        this.f = f;
        this.b = b;
        this.startAngle = startAngle;
        this.modifiers = modifiers;
        this.sinuses = new double[6];
        this.cosinuses = new double[6];
        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI * (i + startAngle) / 6.0;
            this.sinuses[i] = Math.sin(angle);
            this.cosinuses[i] = Math.cos(angle);
        }
    }
    
}