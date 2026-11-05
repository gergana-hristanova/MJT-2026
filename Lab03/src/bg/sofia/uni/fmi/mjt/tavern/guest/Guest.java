package bg.sofia.uni.fmi.mjt.tavern.guest;

public class Guest {
    private final String name;
    private Mood mood;
    private int rakiasDrank;
    private int tab;
    private boolean isDrunk;

    // to be set by descendants
    protected int drunkennessThreshold;

    public Guest(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot create guest because name is null.");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("Cannot create guest because name is blank.");
        }

        this.name = name;

        makeDefault();
    }

    // getters

    public String getName() {
        return name;
    }

    public Mood getMood() {
        return mood;
    }

    public int getRakiasDrank() {
        return rakiasDrank;
    }

    public int getTab() {
        return tab;
    }

    // events

    public void drink(int rakias, int rakiaPrice) {
        rakiasDrank += rakias;
        tab += rakias * rakiaPrice;

        if (!isDrunk) {
            updateDrunkenness();
        }
    }

    public void pay(int amount) {
        tab -= amount;

        if (tab < 0) {
            tab = 0;
        }
    }

    // extra functionalities

    public void makeDefault() {
        mood = Mood.CALM;
        rakiasDrank = 0;
        tab = 0;
        isDrunk = false;
    }

    private void updateDrunkenness() {
        isDrunk = getRakiasDrank() >= drunkennessThreshold;
    }
}
