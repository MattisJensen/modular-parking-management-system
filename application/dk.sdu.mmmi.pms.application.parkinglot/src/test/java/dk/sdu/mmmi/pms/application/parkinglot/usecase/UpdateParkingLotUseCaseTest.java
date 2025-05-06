package dk.sdu.mmmi.pms.application.parkinglot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotException;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UpdateParkingLotUseCaseTest {
    @Mock
    private ParkingLotRepository repository;
    private UpdateParkingLotUseCase useCase;

    private ParkingLot existingParkingLot;
    private UUID existingId;
    private String oldName;
    private String oldLocation;
    private int oldCapacity;
    private int oldAvailableSpots;

    @BeforeEach
    void setUp() {
        openMocks(this);
        useCase = new UpdateParkingLotUseCase(repository);

        // Existing parking lot setup
        existingId = UUID.randomUUID();
        oldName = "Old Lot";
        oldLocation = "Old Location";
        oldCapacity = 10;
        oldAvailableSpots = 5;
        existingParkingLot = new ParkingLot(
                existingId, oldName, oldLocation, oldCapacity, oldAvailableSpots
        );

        when(repository.findById(existingId)).thenReturn(Optional.ofNullable(existingParkingLot));
    }


    @Test
    void execute_FullUpdate_SavesUpdatedParkingLot() {
        // Arrange
        String newName = "New Lot";
        String newLocation = "New Location";
        int newCapacity = 20;
        int newAvailableSpots = 12;

        UpdateParkingLotUseCase.UpdateParameters params = new UpdateParkingLotUseCase.UpdateParameters(
                newName, newLocation, newCapacity, newAvailableSpots
        );

        useCase.execute(existingId, params);

        // Verify that the repository's update method was called with the correct parameters
        verify(repository).update(argThat(parkingLot ->
                parkingLot.name().equals(newName) &&
                        parkingLot.location().equals(newLocation) &&
                        parkingLot.capacity() == newCapacity &&
                        parkingLot.availableSpots() == newAvailableSpots
        ));
    }


    @Test
    void execute_PartialUpdate_RetainsExistingValues() {
        // Arrange
        int newAvailableSpots = 3;
        UpdateParkingLotUseCase.UpdateParameters params = new UpdateParkingLotUseCase.UpdateParameters(
                null, null, null, newAvailableSpots
        );

        useCase.execute(existingId, params);

        // Verify that the repository's update method was called with the correct parameters
        verify(repository).update(argThat(parkingLot ->
                parkingLot.name().equals(oldName) &&
                        parkingLot.location().equals(oldLocation) &&
                        parkingLot.capacity() == oldCapacity &&
                        parkingLot.availableSpots() == newAvailableSpots
        ));
    }


    @Test
    void execute_InvalidCapacity_ThrowsException() {
        // Arrange
        int newCapacity = 2; // Occupied spots = 10 - 5 = 5; new capacity (2) < 5
        UpdateParkingLotUseCase.UpdateParameters params = new UpdateParkingLotUseCase.UpdateParameters(
                null, null, newCapacity, null
        );

        // Ensure that correct exception is thrown
        assertThrows(ParkingLotException.class, () -> useCase.execute(existingId, params));

        // Verify that the repository's update method is never called
        verify(repository, never()).update(any());
    }


    @Test
    void execute_NonExistingParkingLot_ThrowsException() {
        // Arrange
        UUID nonExistingId = UUID.randomUUID();

        // Ensure that correct exception is thrown
        assertThrows(ParkingLotNotFoundException.class, () ->
                useCase.execute(nonExistingId, new UpdateParkingLotUseCase.UpdateParameters(null, null, null, null))
        );
    }
}