package bg.sofia.uni.fmi.mjt.tavern.impl;

import bg.sofia.uni.fmi.mjt.tavern.guest.Guest;

import java.util.Arrays;

public class GuestArray {

    private static final int STARTING_CAPACITY = 16; // 16 by default

    private Guest[] guests;

    private int size;
    private int capacity;

    public GuestArray() {
        guests = new Guest[STARTING_CAPACITY];
        size = 0;
        capacity = STARTING_CAPACITY;
    }

    public String[] getNames() {
        String[] result = new String[size];

        for (int i = 0; i < size; ++i) {
            result[i] = guests[i].getName();
        }

        return result;
    }

    public void add(Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException(
                    "Cannot add guest because guest is null."
            );
        }

        if (size == capacity) {
            resize();
        }

        guests[size++] = guest;
    }

    public Guest find(String guestName) {
        if (guestName == null) {
            throw new IllegalArgumentException(
                    "Cannot find guest because given guest name is null.");
        }

        for (int i = 0; i < size; ++i) {
            Guest currGuest = guests[i];
            if (currGuest.getName().equals(guestName)) {
                return currGuest;
            }
        }

        return null;
    }

    public Guest find(Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException(
                    "Cannot find guest because guest is null.");
        }

        String name = guest.getName();

        return find(name);
    }

    public boolean contains(String guestName) {
        return find(guestName) != null;
    }

    public boolean contains(Guest guest) {
        return find(guest) != null;
    }

    private void resize() {
        // in case STARTING_CAPACITY is ever changed to 0
        capacity = (capacity == 0) ? 1 : capacity * 2;

        guests = Arrays.copyOf(guests, capacity);
    }
}