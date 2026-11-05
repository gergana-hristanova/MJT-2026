package bg.sofia.uni.fmi.mjt.tavern.guest;

public class Hothead extends Guest {
    private static final int HOTHEAD_THRESHOLD = 4;

    public Hothead(String name) {
        super(name);
        super.drunkennessThreshold = HOTHEAD_THRESHOLD;
    }
}