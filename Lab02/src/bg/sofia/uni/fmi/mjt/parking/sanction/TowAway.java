package bg.sofia.uni.fmi.mjt.parking.sanction;

import bg.sofia.uni.fmi.mjt.parking.vehicle.Vehicle;
import bg.sofia.uni.fmi.mjt.parking.zone.ParkingZone;

public class TowAway implements Sanction {
    private final Vehicle vehicle;
    private final ParkingZone zone;

    private static final int BASE_SUM = 30;

    public TowAway(Vehicle vehicle, ParkingZone zone) {
        this.vehicle = vehicle;
        this.zone = zone;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public ParkingZone getZone() {
        return zone;
    }

    @Override
    public int getAmount() {
        return BASE_SUM + vehicle.getSurcharge();
    }
}
