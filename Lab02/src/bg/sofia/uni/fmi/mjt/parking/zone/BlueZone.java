package bg.sofia.uni.fmi.mjt.parking.zone;

import bg.sofia.uni.fmi.mjt.parking.location.Location;

public class BlueZone implements ParkingZone {
    private final String name;
    private final Location center;
    private final double radius;

    private final static  String SMS_NUMBER = "1302";

    public BlueZone(String name, Location center, double radius) {
        this.name = name;
        this.center = center;
        this.radius = radius;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSmsNumber() {
        return SMS_NUMBER;
    }

    @Override
    public boolean contains(Location location) {
        if (location == null) {
            return false;
        }

        // location x, y and center x, y
        double lx = location.getX();
        double ly = location.getY();
        double cx = center.getX();
        double cy = center.getY();

        double dist = Math.sqrt((cx - lx) * (cx - lx) + (cy - ly) * (cy - ly));

        return dist <= radius;
    }
}
