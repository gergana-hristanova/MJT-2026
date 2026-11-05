package bg.sofia.uni.fmi.mjt.tavern.guest;

public class Drunkard extends Guest {
    private static final int DRUNKARD_THRESHOLD = 2;

    public Drunkard(String name) {
        super(name);
        super.drunkennessThreshold = DRUNKARD_THRESHOLD;
    }
}