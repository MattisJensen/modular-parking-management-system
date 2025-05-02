package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.AccountRole;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailDuplicateException;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CreateAccountUseCaseTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private CreateAccountUseCase useCase;
    private final String validEmail = "test@example.com";
    private final String rawPassword = "password123";
    private final String hashedPassword = "hashedPassword";
    private final String name = "Test User";
    private final AccountRole role = AccountRole.USER;

    @BeforeEach
    void setUp() {
        openMocks(this);
        useCase = new CreateAccountUseCase(accountRepository, passwordEncoder);
    }


    @Test
    void execute_SuccessfulCreation_ReturnsUuidAndSavesAccount() {
        // Arrange
        when(accountRepository.findByEmail(validEmail)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(rawPassword)).thenReturn(hashedPassword);
        UUID result = useCase.execute(name, validEmail, rawPassword, role);

        // Verify account creation
        assertNotNull(result);

        // Verify account properties
        verify(accountRepository).save(argThat(account ->
                account.id().equals(result) &&
                        account.email().equals(validEmail) &&
                        account.password().equals(hashedPassword) &&
                        account.name().equals(name) &&
                        account.accountRole() == role
        ));
    }


    @Test
    void execute_InvalidEmail_ThrowsEmailFormatException() {
        // Arrange
        String invalidEmail = "invalid-email";

        // Ensures that an EmailFormatException is thrown
        assertThrows(EmailFormatException.class, () -> useCase.execute(name, invalidEmail, rawPassword, role));

        // Verify that the passwordEncoder's encode method was not called
        verifyNoInteractions(passwordEncoder);
        verify(accountRepository, never()).save(any());
    }


    @Test
    void execute_DuplicateEmail_ThrowsEmailDuplicateException() {
        // Arrange
        when(accountRepository.findByEmail(validEmail)).thenReturn(Optional.of(mock(Account.class)));

        // Ensures that an EmailDuplicateException is thrown
        assertThrows(EmailDuplicateException.class, () -> useCase.execute(name, validEmail, rawPassword, role));

        // Verify password encoding didn't happen
        verifyNoInteractions(passwordEncoder);
        verify(accountRepository, never()).save(any());
    }


    @Test
    void execute_VerifyPasswordHashing() {
        // Arrange
        when(accountRepository.findByEmail(validEmail)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(rawPassword)).thenReturn(hashedPassword);

        useCase.execute(name, validEmail, rawPassword, role);

        // Verify that the password was hashed correctly
        verify(passwordEncoder).encode(rawPassword);
    }


    @Test
    void execute_AccountNotSavedOnValidationFailure() {
        // Arrange
        String invalidEmail = "bad.email";

        // Ensures that an EmailFormatException is thrown
        assertThrows(EmailFormatException.class, () -> useCase.execute(name, invalidEmail, rawPassword, role));

        // Verify that the account was not saved
        verify(accountRepository, never()).save(any());
    }
}