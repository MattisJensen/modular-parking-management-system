package dk.sdu.mmmi.pms.presentation.parkinglot.datatransferobject;

import java.util.UUID;

public record ParkingLotResponse(
        UUID id,
        String name,
        String location,
        int capacity,
        int availableSpots
) {}