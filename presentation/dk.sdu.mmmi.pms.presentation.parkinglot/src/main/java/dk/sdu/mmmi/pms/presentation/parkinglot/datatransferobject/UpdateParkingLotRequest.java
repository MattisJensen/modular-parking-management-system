package dk.sdu.mmmi.pms.presentation.parkinglot.datatransferobject;

public record UpdateParkingLotRequest(
        String name,
        String location,
        Integer capacity,
        Integer availableSpots
) {}
