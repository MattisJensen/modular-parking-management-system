package dk.sdu.mmmi.pms.presentation.parkinglot.datatransferobject;

public record CreateParkingLotRequest(
        String name,
        String location,
        int capacity
) {}