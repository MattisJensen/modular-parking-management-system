package dk.sdu.mmmi.pms.application.parkinglot.usecase;

import dk.sdu.mmmi.pms.application.parkinglot.ParkingLotRepository;
import dk.sdu.mmmi.pms.core.parkinglot.ParkingLot;
import dk.sdu.mmmi.pms.core.parkinglot.exception.ParkingLotNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class DeleteParkingLotUseCaseTest {
    @Mock
    private ParkingLotRepository repository;

    private DeleteParkingLotUseCase useCase;
    private final UUID existingId = UUID.randomUUID();
    private final UUID nonExistingId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        openMocks(this);
        useCase = new DeleteParkingLotUseCase(repository);
        when(repository.findById(existingId)).thenReturn(Optional.of(mock(ParkingLot.class)));
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
    }


    @Test
    void execute_ExistingId_DeletesParkingLot() {
        // Arrange
        useCase.execute(existingId);

        // Verify that the repository's deleteById method was called with the correct ID
        verify(repository).deleteById(existingId);
    }


    @Test
    void execute_NonExistingId_ThrowsExceptionAndDoesNotDelete() {
        // Ensure that correct exception is thrown
        assertThrows(ParkingLotNotFoundException.class, () -> useCase.execute(nonExistingId));

        // Verify that the repository's deleteById method is never called
        verify(repository, never()).deleteById(any());
    }
}