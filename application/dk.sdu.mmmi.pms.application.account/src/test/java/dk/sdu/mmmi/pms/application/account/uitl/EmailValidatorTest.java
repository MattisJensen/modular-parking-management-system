package dk.sdu.mmmi.pms.application.account.uitl;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.exception.EmailDuplicateException;
import dk.sdu.mmmi.pms.core.account.exception.EmailFormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "test@example.com",
            "user.name@domain.co",
            "user123@sub.domain.com",
            "aa@a.aa",
            "user-name@domain.com",
            "user+tag@domain.org"
    })
    void validateFormat_validEmails_doesNotThrowException(String email) {
        assertDoesNotThrow(() -> EmailValidator.validateFormat(email));
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "a@example.com",    // Suffix too short
            "@domain.com",      // Suffix missing
            "user@domain",      // Missing Top Level Domain (TLD)
            "user@domain.c",    // TLD too short

            "user@.com",        // Domain starts with dot
            "user@-domain.com", // Domain starts with hyphen
            "user@.domain.com", // Leading dot in domain
            "user@domain..com", // Double dots in domain

            "user@domain_.com", // Underscore in domain
            "user@domain.com.", // Trailing dot
    })
    void validateFormat_invalidEmails_throwsEmailFormatException(String email) {
        assertThrows(EmailFormatException.class, () -> EmailValidator.validateFormat(email));
    }


    @Test
    void validateUniqueness_newEmail_doesNotThrowException() {
        // Arrange
        AccountRepository mockRepository = mock(AccountRepository.class);
        String newEmail = "unique@mail.com";
        when(mockRepository.findByEmail(newEmail)).thenReturn(java.util.Optional.empty());

        // Validate uniqueness
        assertDoesNotThrow(() -> EmailValidator.validateUniqueness(newEmail, mockRepository));
    }


    @Test
    void validateUniqueness_existingEmail_throwsEmailDuplicateException() {
        // Arrange
        AccountRepository mockRepository = mock(AccountRepository.class);
        String existingEmail = "existing@mail.com";
        when(mockRepository.findByEmail(existingEmail)).thenReturn(Optional.of(mock(Account.class)));

        // Validate existence
        assertThrows(EmailDuplicateException.class, () -> EmailValidator.validateUniqueness(existingEmail, mockRepository));
    }
}