package bg.sofia.uni.fmi.mjt.tavern.guest;

public enum Mood {
    CALM,
    TENSE,
    ANGRY,
    BRAWLING;

    // TODO SEE ABOUT DEPRECATING MAYBE
    public static Mood escalate(Mood mood) {
        return switch (mood) {
            case CALM -> TENSE;
            case TENSE -> ANGRY;
            case ANGRY, BRAWLING -> BRAWLING;
        };
    }

    public static Mood calmDown(Mood mood) {
        return switch (mood) {
            case CALM, TENSE -> CALM;
            case ANGRY -> TENSE;
            case BRAWLING -> ANGRY;
        };
    }
}
