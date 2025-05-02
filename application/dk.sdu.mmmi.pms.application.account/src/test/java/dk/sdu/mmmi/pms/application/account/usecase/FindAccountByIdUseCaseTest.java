package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exceptions.AccountNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class FindAccountByIdUseCaseTest {
    @Mock
    private AccountRepository accountRepository;

    private FindAccountByIdUseCase useCase;
    private final UUID existingId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    private final UUID nonExistingId = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @BeforeEach
    void setUp() {
        openMocks(this);
        useCase = new FindAccountByIdUseCase(accountRepository);
    }


    @Test
    void execute_ExistingId_ReturnsAccount() {
        // Arrange
        Account expectedAccount = mock(Account.class);
        when(accountRepository.findById(existingId)).thenReturn(Optional.of(expectedAccount));

        Account result = useCase.execute(existingId);

        // Ensure that the result is the expected account
        assertSame(expectedAccount, result);
    }


    @Test
    void execute_NonExistingId_ThrowsException() {
        // Arrange
        when(accountRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // Ensure that an AccountNotFoundException is thrown
        assertThrows(AccountNotFoundException.class, () -> useCase.execute(nonExistingId));

        // Verify that the repository's findById method was called with the correct ID
        verify(accountRepository).findById(nonExistingId);
    }


    @Test
    void execute_VerifyIdParameterPassedCorrectly() {
        // Arrange
        UUID testId = UUID.randomUUID();
        when(accountRepository.findById(testId)).thenReturn(Optional.of(mock(Account.class)));

        useCase.execute(testId);

        // Verify that the repository's findById method was called with the correct ID
        verify(accountRepository).findById(testId);
    }
}