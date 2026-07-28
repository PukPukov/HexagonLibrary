package ru.pukpukov.hexagon.fx.launchers;

import ru.pukpukov.hexagon.fx.direct.RegionTest;

/**
 * JavaFX for some reason requires this shit to run properly on non-javafx jdks
 */
public class RegionTestLauncher {
    
    public static void main(String[] args) {
        RegionTest.main(args);
    }
    
}