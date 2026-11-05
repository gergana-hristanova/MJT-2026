package bg.sofia.uni.fmi.mjt.parking.vehicle;

public class Car implements Vehicle {
    private final LicensePlate licensePlate;

    private static final int SURCHARGE = 15;

    public Car(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    @Override
    public int getSurcharge() {
        return SURCHARGE;
    }
}
