package bg.sofia.uni.fmi.mjt.tavern;

import bg.sofia.uni.fmi.mjt.tavern.event.Event;
import bg.sofia.uni.fmi.mjt.tavern.exception.BannedGuestException;
import bg.sofia.uni.fmi.mjt.tavern.exception.GuestAlreadyInsideException;
import bg.sofia.uni.fmi.mjt.tavern.guest.Guest;
import bg.sofia.uni.fmi.mjt.tavern.guest.Mood;
import bg.sofia.uni.fmi.mjt.tavern.impl.GuestArray;

public class TavernAPIImpl implements TavernAPI {
    private GuestArray bookOfBanned;
    private GuestArray inside;
    private int rakiaPrice;

    public TavernAPIImpl(int rakiaPrice) {
        if (rakiaPrice <= 0) {
            throw new IllegalArgumentException(
                    "Cannot set rakia price because it is not positive."
            );
        }

        this.rakiaPrice = rakiaPrice;
    }

    @Override
    public void admit(Guest guest) throws BannedGuestException, GuestAlreadyInsideException {
        if (guest == null) {
            throw new IllegalArgumentException("Cannot admit guest because guest is null.");
        }

        if (isBanned(guest)) {
            throw new BannedGuestException();
        }

        if (isAlreadyInside(guest)) {
            throw new GuestAlreadyInsideException();
        }

        guest.makeDefault();
    }

    @Override
    public void handle(Event event) {
        //TODO HANDLE EVENTS
    }

    @Override
    public Mood moodOf(String name) {
        return inside.find(name).getMood();
    }

    @Override
    public int tabOf(String name) {
        return inside.find(name).getTab();
    }

    @Override
    public String[] guestsInside() {
        return inside.getNames();
    }

    @Override
    public String[] bannedGuests() {
        return bookOfBanned.getNames();
    }

    private boolean isBanned(Guest guest) {
        return bookOfBanned.contains(guest);
    }

    private boolean isAlreadyInside(Guest guest) {
        return inside.contains(guest);
    }
}