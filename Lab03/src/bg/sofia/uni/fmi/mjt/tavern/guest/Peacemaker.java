package bg.sofia.uni.fmi.mjt.tavern.guest;

public class Peacemaker extends Guest {
    private static final int PEACEMAKER_THRESHOLD = 5;

    public Peacemaker(String name) {
        super(name);
        super.drunkennessThreshold = PEACEMAKER_THRESHOLD;
    }
}