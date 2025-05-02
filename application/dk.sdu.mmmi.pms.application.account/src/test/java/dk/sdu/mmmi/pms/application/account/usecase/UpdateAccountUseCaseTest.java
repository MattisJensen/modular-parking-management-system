package dk.sdu.mmmi.pms.application.account.usecase;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.AccountRole;
import dk.sdu.mmmi.pms.core.account.exceptions.AccountNotFoundException;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailDuplicateException;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class UpdateAccountUseCaseTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UpdateAccountUseCase useCase;
    private Account existingAccount;
    private final UUID existingId = UUID.randomUUID();
    private final String oldName = "Old Name";
    private final String oldEmail = "old@mail.com";
    private final String oldPassword = "oldPassword";
    private final AccountRole oldRole = AccountRole.USER;

    @BeforeEach
    void setUp() {
        openMocks(this);
        existingAccount = new Account(existingId, oldName, oldEmail, oldPassword, oldRole);
        useCase = new UpdateAccountUseCase(accountRepository, passwordEncoder);
        when(accountRepository.findById(existingId)).thenReturn(Optional.of(existingAccount));
    }


    @Test
    void execute_FullUpdate_SavesUpdatedAccount() {
        // Arrange
        String newName = "New Name";
        String newEmail = "new@email.com";
        String newPassword = "newPassword";
        String hashedNewPassword = "hashedNewPassword";
        AccountRole newRole = AccountRole.ADMIN;

        when(accountRepository.findByEmail(newEmail)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(newPassword)).thenReturn(hashedNewPassword);

        UpdateAccountUseCase.UpdateParameters params = new UpdateAccountUseCase.UpdateParameters(
                newName, newEmail, newPassword, newRole
        );

        useCase.execute(existingId, params);

        // Verify that update was successful and the account was saved with the new values
        verify(accountRepository).update(argThat(account ->
                account.name().equals(newName) &&
                        account.email().equals(newEmail) &&
                        account.password().equals(hashedNewPassword) &&
                        account.accountRole() == newRole
        ));
    }


    @Test
    void execute_InvalidEmail_ThrowsFormatException() {
        UpdateAccountUseCase.UpdateParameters params = new UpdateAccountUseCase.UpdateParameters(
                null, "wrong-email", null, null
        );

        // Ensure that an EmailFormatException is thrown
        assertThrows(EmailFormatException.class, () -> useCase.execute(existingId, params));
    }


    @Test
    void execute_DuplicateEmail_ThrowsDuplicateException() {
        String duplicateEmail = "duplicate@email.com";
        when(accountRepository.findByEmail(duplicateEmail))
                .thenReturn(Optional.of(mock(Account.class)));

        UpdateAccountUseCase.UpdateParameters params = new UpdateAccountUseCase.UpdateParameters(
                null, duplicateEmail, null, null
        );

        // Ensure that an EmailDuplicateException is thrown
        assertThrows(EmailDuplicateException.class, () -> useCase.execute(existingId, params));
    }


    @Test
    void execute_PartialUpdate_RetainsExistingValues() throws Exception {
        // Arrange
        String newName = "New Name";
        when(accountRepository.findByEmail(existingAccount.email())).thenReturn(Optional.of(existingAccount));


        UpdateAccountUseCase.UpdateParameters params = new UpdateAccountUseCase.UpdateParameters(
                newName, null, null, null
        );

        useCase.execute(existingId, params);

        // Ensure that the account was updated with the new name and other values remained unchanged
        verify(accountRepository).update(argThat(account ->
                account.name().equals(newName) &&
                        account.email().equals(oldEmail) &&
                        account.password().equals(oldPassword) &&
                        account.accountRole() == oldRole
        ));
    }


    @Test
    void execute_PasswordUpdate_HashesNewPassword() throws Exception {
        // Arrange
        String newPassword = "newPassword";
        String hashedNewPassword = "hashedNewPassword";
        when(passwordEncoder.encode(newPassword)).thenReturn(hashedNewPassword);
        when(accountRepository.findByEmail(oldEmail)).thenReturn(Optional.of(existingAccount));

        UpdateAccountUseCase.UpdateParameters params = new UpdateAccountUseCase.UpdateParameters(
                null, null, newPassword, null
        );

        useCase.execute(existingId, params);

        // Ensure that the password was hash encoder was called
        verify(passwordEncoder).encode(newPassword);

        // Verify that the account was updated with the new hashed password
        verify(accountRepository).update(argThat(account -> account.password().equals(hashedNewPassword)));
    }


    @Test
    void execute_NonExistingAccount_ThrowsNotFoundException() {
        // Arrange
        UUID missingId = UUID.randomUUID();
        when(accountRepository.findById(missingId)).thenReturn(Optional.empty());

        // Ensure that an AccountNotFoundException is thrown
        assertThrows(AccountNotFoundException.class, () -> useCase.execute(missingId, new UpdateAccountUseCase.UpdateParameters(null, null, null, null)));
    }
}