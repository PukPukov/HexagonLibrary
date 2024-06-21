package ru.ancap.hexagon;

public record GridOrientation(String name, double[] f, double[] b, double startAngle, double[] sinuses, double[] cosinuses) {
    
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
    
    public GridOrientation(String name, double[] f, double[] b, double startAngle) {
        this(__(name, f, b, startAngle));
    }
    
    /**
     * Non-flexible constructor bodies in java 21 issue workaround
     */
    private static GridOrientation __(String name, double[] f, double[] b, double startAngle) {
        double[] sinuses = new double[6];
        double[] cosinuses = new double[6];
        for (int i = 0; i < 6; i++) {
            double angle = 2.0 * Math.PI * (i + startAngle) / 6.0;
            sinuses[i] = Math.sin(angle);
            cosinuses[i] = Math.cos(angle);
        }
        return new GridOrientation(name, f, b, startAngle, sinuses, cosinuses);
    }
    
    /**
     * Copy-constructor, mainly used in construction help
     */
    public GridOrientation(GridOrientation gridOrientation) {
        this(gridOrientation.name, gridOrientation.f, gridOrientation.b, gridOrientation.startAngle, gridOrientation.sinuses, gridOrientation.cosinuses);
    }
    
}