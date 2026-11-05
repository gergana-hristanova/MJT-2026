package bg.sofia.uni.fmi.mjt.tavern.event;

public sealed interface Event permits Drink, Insult, Cheat, Pay, Leave {
}