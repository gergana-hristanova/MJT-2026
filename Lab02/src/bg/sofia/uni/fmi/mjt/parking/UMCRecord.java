package bg.sofia.uni.fmi.mjt.parking;

import bg.sofia.uni.fmi.mjt.parking.zone.ParkingZone;

import java.time.LocalDateTime;

public record UMCRecord(
        String licensePlate,
        LocalDateTime paymentMoment,
        ParkingZone parkingZone ) {}
