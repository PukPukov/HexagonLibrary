package ru.ancap.hexagon;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode @ToString
public class GridOrientation {
    
    public final static GridOrientation POINTY = new GridOrientation(
            "pointy",
            new double[]{Math.sqrt(3.0), Math.sqrt(3.0) / 2.0, 0.0, 3.0 / 2.0},
            new double[]{Math.sqrt(3.0) / 3.0, -1.0 / 3.0, 0.0, 2.0 / 3.0},
            0.5
    );

    public final static GridOrientation FLAT = new GridOrientation(
            "flat",
            new double[]{3.0 / 2.0, 0.0, Math.sqrt(3.0) / 2.0, Math.sqrt(3.0)},
            new double[]{2.0 / 3.0, 0.0, -1.0 / 3.0, Math.sqrt(3.0) / 3.0},
            0.0
    );

    private final String name;
    
    @ToString.Exclude private final double[] f;
    @ToString.Exclude private final double[] b;
    @ToString.Exclude private final double startAngle;
    @ToString.Exclude private final double[] sinuses;
    @ToString.Exclude private final double[] cosinuses;

    public GridOrientation(String name, double[] f, double[] b, double startAngle) {
        this.name = name;
        this.f = f;
        this.b = b;
        this.startAngle = startAngle;
        this.sinuses = new double[6];
        this.cosinuses = new double[6];
        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI * (i + startAngle()) / 6.0;
            this.sinuses[i] = Math.sin(angle);
            this.cosinuses[i] = Math.cos(angle);
        }
    }
    
    public String name() {return this.name;}
    public double[] f() {return this.f;}
    public double[] b() {return this.b;}
    public double startAngle() {return this.startAngle;}
    public double[] sinuses() {return this.sinuses;}
    public double[] cosinuses() {return this.cosinuses;}
    
}
