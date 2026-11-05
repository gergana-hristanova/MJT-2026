package bg.sofia.uni.fmi.mjt.parking.sanction;

import bg.sofia.uni.fmi.mjt.parking.vehicle.Vehicle;
import bg.sofia.uni.fmi.mjt.parking.zone.ParkingZone;

public class WheelClamp extends TowAway {
    private static final int BASE_SUM = 15;

    public WheelClamp(Vehicle vehicle, ParkingZone zone) {
        super(vehicle, zone);
    }

    @Override
    public int getAmount() {
        return BASE_SUM + getVehicle().getSurcharge();
    }
}
