package dk.sdu.mmmi.pms.application.account.uitl;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exceptions.AccountNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class IdValidatorTest {
    @Mock
    private AccountRepository mockRepository;
    private final UUID existingId = UUID.randomUUID();

    @BeforeEach
    void setup() {
     openMocks(this);
    }

    @Test
    void validateExistence_ExistingId_NoException() {
        // Arrange
        when(mockRepository.findById(existingId)).thenReturn(Optional.of(mock(Account.class)));

        // Ensures that no exception is thrown
        assertDoesNotThrow(() -> IdValidator.validateExistence(existingId, mockRepository));

        // Verify that the repository's findById method was called with the correct ID
        verify(mockRepository).findById(existingId);
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "123e4567-e89b-12d3-a456-426614174000", // Sample UUID's
            "00000000-0000-0000-0000-000000000000",
            "11111111-1111-1111-1111-111111111111"
    })
    void validateExistence_NonExistingId_ThrowsException(String idString) {
        // Arrange
        UUID id = UUID.fromString(idString);
        when(mockRepository.findById(id)).thenReturn(Optional.empty());

        // Ensures that an AccountNotFoundException is thrown
        assertThrows(AccountNotFoundException.class, () -> IdValidator.validateExistence(id, mockRepository));

        // Verify that the repository's findById method was called with the correct ID
        verify(mockRepository).findById(id);
    }


    @Test
    void validateExistenceAndGetAccount_ExistingId_ReturnsAccount() {
        // Arrange
        Account expectedAccount = mock(Account.class);
        when(mockRepository.findById(existingId)).thenReturn(Optional.of(expectedAccount));

        // Ensures that no exception is thrown
        Account result = IdValidator.validateExistenceAndGetAccount(existingId, mockRepository);

        // Ensures that the returned account is the same as the expected one
        assertSame(expectedAccount, result);

        // Verify that the repository's findById method was called with the correct ID
        verify(mockRepository).findById(existingId);
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "22222222-2222-2222-2222-222222222222", // Sample UUID's
            "33333333-3333-3333-3333-333333333333"
    })
    void validateExistenceAndGetAccount_NonExistingId_ThrowsException(String idString) {
        // Arrange
        UUID id = UUID.fromString(idString);
        when(mockRepository.findById(id)).thenReturn(Optional.empty());

        // Ensures that an AccountNotFoundException is thrown
        assertThrows(AccountNotFoundException.class, () -> IdValidator.validateExistenceAndGetAccount(id, mockRepository));

        // Verify that the repository's findById method was called with the correct ID
        verify(mockRepository).findById(id);
    }
}