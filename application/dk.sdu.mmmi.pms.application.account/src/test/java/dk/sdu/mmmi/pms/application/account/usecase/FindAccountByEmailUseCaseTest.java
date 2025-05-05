package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exception.AccountNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class FindAccountByEmailUseCaseTest {
    @Mock
    private AccountRepository accountRepository;

    private FindAccountByEmailUseCase useCase;
    private final String existingEmail = "existing@mail.com";
    private final String nonExistingEmail = "missing@mail.com";

    @BeforeEach
    void setUp() {
        openMocks(this);
        useCase = new FindAccountByEmailUseCase(accountRepository);
    }


    @Test
    void execute_ExistingEmail_ReturnsAccount() {
        // Arrange
        Account expectedAccount = mock(Account.class);
        when(accountRepository.findByEmail(existingEmail)).thenReturn(Optional.of(expectedAccount));

        Account result = useCase.execute(existingEmail);

        // Ensure that the result is the expected account
        assertSame(expectedAccount, result);

        // Verify that the repository's findByEmail method was called with the correct email
        verify(accountRepository).findByEmail(existingEmail);
    }


    @Test
    void execute_NonExistingEmail_ThrowsException() {
        // Arrange
        when(accountRepository.findByEmail(nonExistingEmail)).thenReturn(Optional.empty());

        // Ensure that an AccountNotFoundException is thrown
        assertThrows(AccountNotFoundException.class, () -> useCase.execute(nonExistingEmail));

        // Verify that the repository's findByEmail method was called with the correct email
        verify(accountRepository).findByEmail(nonExistingEmail);
    }


    @Test
    void execute_VerifyEmailParameterPassedCorrectly() {
        // Arrange
        String testEmail = "test@mail.com";
        when(accountRepository.findByEmail(testEmail)).thenReturn(Optional.of(mock(Account.class)));

        useCase.execute(testEmail);

        // Verify that the repository's findByEmail method was called with the correct email
        verify(accountRepository).findByEmail(testEmail);
    }
}