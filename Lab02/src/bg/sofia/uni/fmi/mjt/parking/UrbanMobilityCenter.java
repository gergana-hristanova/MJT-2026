package bg.sofia.uni.fmi.mjt.parking;

import bg.sofia.uni.fmi.mjt.parking.location.Location;
import bg.sofia.uni.fmi.mjt.parking.sanction.Sanction;
import bg.sofia.uni.fmi.mjt.parking.sanction.TowAway;
import bg.sofia.uni.fmi.mjt.parking.sanction.WheelClamp;
import bg.sofia.uni.fmi.mjt.parking.vehicle.LicensePlate;
import bg.sofia.uni.fmi.mjt.parking.vehicle.Vehicle;
import bg.sofia.uni.fmi.mjt.parking.zone.BlueZone;
import bg.sofia.uni.fmi.mjt.parking.zone.GreenZone;
import bg.sofia.uni.fmi.mjt.parking.zone.ParkingZone;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UrbanMobilityCenter implements ParkingClientAPI, ParkingInspectorAPI {
    final int ARRAY_SIZE = 64;
    final long PAYMENT_MINS = 60; // 1 hour by default

    UMCRecord[] paymentRecords = new UMCRecord[ARRAY_SIZE];

    int arrPos = 0;

    BlueZone bz;
    GreenZone gz;

    public UrbanMobilityCenter(BlueZone blueZone, GreenZone greenZone) {
        bz = blueZone;
        gz = greenZone;
    }

    @Override
    public boolean sendSms(String smsNumber, String plate, LocalDateTime moment) {
        if (smsNumber == null || plate == null || moment == null) {
            return false;
        }

        if (bz == null || gz == null
                || (!smsNumber.equals(bz.getSmsNumber()) && !smsNumber.equals(gz.getSmsNumber()))) {
            return false;
        }

        LicensePlate licensePlate = LicensePlate.parse(plate);

        if (licensePlate == null) {
            return false;
        }

        if (arrPos == paymentRecords.length) {
            UMCRecord[] expandedRecords =
                    new UMCRecord[paymentRecords.length * 2];
            System.arraycopy(paymentRecords, 0, expandedRecords, 0, paymentRecords.length);
            paymentRecords = expandedRecords;
        }

        ParkingZone paymentRecordZone = (smsNumber.equals(bz.getSmsNumber()) ? bz : gz);
        UMCRecord paymentRecord = new UMCRecord(
                licensePlate.toString(),
                moment,
                paymentRecordZone
        );

        paymentRecords[arrPos++] = paymentRecord;

        return true;
    }

    @Override
    public Sanction inspect(Vehicle vehicle, Location location, LocalDateTime moment) {
        if (vehicle == null || location == null || moment == null) {
            return null;
        }

        boolean inBlueZone = bz != null && bz.contains(location);
        boolean inGreenZone = gz != null && gz.contains(location);
        if (!inBlueZone && !inGreenZone) {
            return null;
        }

        if (vehicle.getLicensePlate() == null) {
            return null;
        }

        UMCRecord plateRecord = getPlateRecord(vehicle.getLicensePlate().toString());
        if (plateRecord != null
                && plateRecord.paymentMoment() != null
                && plateRecord.paymentMoment().isAfter(moment.plusMinutes(PAYMENT_MINS))) {
            return null;
        }

        boolean paymentValid = plateRecord != null
                && plateRecord.parkingZone() != null
                && plateRecord.paymentMoment() != null
                && ((plateRecord.parkingZone() == bz && inBlueZone)
                || (plateRecord.parkingZone() == gz && inGreenZone));
        if (paymentValid) {
            long paymentTimeDiff = ChronoUnit.MINUTES.between(plateRecord.paymentMoment(), moment);
            paymentValid = paymentTimeDiff >= 0 && paymentTimeDiff <= PAYMENT_MINS;
        }

        if (!paymentValid) {
            if (inBlueZone) {
                return new TowAway(vehicle, bz);
            }

            return new WheelClamp(vehicle, gz);
        }

        return null;
    }

    private UMCRecord getPlateRecord(String plate) {
        UMCRecord result = null;
        for (int i = 0; i < arrPos; ++i) {
            UMCRecord record = paymentRecords[i];
            if (record != null && record.licensePlate().equals(plate)
                    && (result == null
                    || record.paymentMoment().isAfter(result.paymentMoment()))) {
                result = record;
            }
        }

        return result;
    }
}
