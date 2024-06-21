package launchers;

import ru.ancap.hexagon.direct.LegacyRegionTest;

/**
 * JavaFX for some reason requires this shit to run properly on non-javafx jdks
 */
public class LegacyRegionTestLauncher {
    
    public static void main(String[] args) {
        LegacyRegionTest.main(args);
    }
    
}