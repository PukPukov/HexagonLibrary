package ru.ancap.hexagon;

import lombok.*;
import lombok.experimental.Accessors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode @ToString
@Accessors(fluent = true) @Getter
public final class GridOrientation {
    
    public final static GridOrientation POINTY = new GridOrientation(
        "pointy",
        new double[] {Math.sqrt(3.0), Math.sqrt(3.0) / 2.0, 0.0, 3.0 / 2.0},
        new double[] {Math.sqrt(3.0) / 3.0, -1.0 / 3.0, 0.0, 2.0 / 3.0},
        0.5
    );
    
    public final static GridOrientation FLAT = new GridOrientation(
        "flat",
        new double[] {3.0 / 2.0, 0.0, Math.sqrt(3.0) / 2.0, Math.sqrt(3.0)},
        new double[] {2.0 / 3.0, 0.0, -1.0 / 3.0, Math.sqrt(3.0) / 3.0},
        0.0
    );
    
    private final String name;
    private final double[] f;
    private final double[] b;
    private final double startAngle;
    
    // cache
    @EqualsAndHashCode.Exclude @ToString.Exclude private final double[] sinuses;
    @EqualsAndHashCode.Exclude @ToString.Exclude private final double[] cosinuses;
    
    public GridOrientation(String name, double[] f, double[] b, double startAngle) {
        this.name = name;
        this.f = f;
        this.b = b;
        this.startAngle = startAngle;
        this.sinuses = new double[6];
        this.cosinuses = new double[6];
        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI * (i + startAngle) / 6.0;
            this.sinuses[i] = Math.sin(angle);
            this.cosinuses[i] = Math.cos(angle);
        }
    }

    public GridOrientation(GridOrientation gridOrientation) {
        this(gridOrientation.name, gridOrientation.f, gridOrientation.b, gridOrientation.startAngle, gridOrientation.sinuses, gridOrientation.cosinuses);
    }
    
}