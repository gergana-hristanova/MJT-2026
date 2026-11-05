package bg.sofia.uni.fmi.mjt.tavern.guest;

public class Cheater extends Guest {
    private static final int CHEATER_THRESHOLD = 3;

    public Cheater(String name) {
        super(name);
        super.drunkennessThreshold = CHEATER_THRESHOLD;
    }
}