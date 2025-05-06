package dk.sdu.mmmi.pms.application.parkinglot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateParkingLotUseCaseTest {
    @Mock
    private ParkingLotRepository repository;
    private CreateParkingLotUseCase useCase;

    @BeforeEach
    void setUp() {
        openMocks(this);
        useCase = new CreateParkingLotUseCase(repository);
    }


    @Test
    void execute_ValidParameters_SavesParkingLotWithCorrectProperties() {
        // Arrange
        String name = "Test Lot";
        String location = "Test Location";
        int capacity = 5;

        UUID result = useCase.execute(name, location, capacity);

        // Ensure that the repository's save method was called with the correct parameters
        assertNotNull(result);
        verify(repository).save(argThat(parkingLot ->
                parkingLot.id().equals(result) &&
                        parkingLot.name().equals(name) &&
                        parkingLot.location().equals(location) &&
                        parkingLot.capacity() == capacity &&
                        parkingLot.availableSpots() == capacity
        ));
    }


    @Test
    void execute_CapacityZero_ThrowsExceptionAndDoesNotSave() {
        // Arrange
        String name = "Test";
        String location = "Location";
        int capacity = 0;

        // Ensure that exception is thrown
        assertThrows(ParkingLotException.class, () -> useCase.execute(name, location, capacity));

        // Ensure that the repository's save method is never called
        verify(repository, never()).save(any());
    }


    @Test
    void execute_NegativeCapacity_ThrowsExceptionAndDoesNotSave() {
        // Arrange
        String name = "Test";
        String location = "Location";
        int capacity = -3;

        // Ensure that exception is thrown
        assertThrows(ParkingLotException.class, () -> useCase.execute(name, location, capacity));

        // Ensure that the repository's save method is never called
        verify(repository, never()).save(any());
    }
}