package dk.sdu.mmmi.pms.presentation.account.config;

import dk.sdu.mmmi.pms.application.account.AccountRepository;
import dk.sdu.mmmi.pms.application.account.usecase.*;
import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

class AccountConfigPresentationTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private AccountConfigPresentation config;

    @BeforeEach
    void setUp() {
        openMocks(this);
        config = new AccountConfigPresentation();
    }


    @Test
    void createAccountUseCase_BeanConfiguredCorrectly() {
        // Arrange
        CreateAccountUseCase useCase = config.createAccountUseCase(accountRepository, passwordEncoder);

        // Ensure the use case is created correctly
        assertNotNull(useCase);
        assertInstanceOf(CreateAccountUseCase.class, useCase);
    }


    @Test
    void updateAccountUseCase_BeanConfiguredCorrectly() {
        // Arrange
        UpdateAccountUseCase useCase = config.updateAccountUseCase(accountRepository, passwordEncoder);

        // Ensure the use case is created correctly
        assertNotNull(useCase);
        assertInstanceOf(UpdateAccountUseCase.class, useCase);
    }


    @Test
    void findAccountByEmailUseCase_BeanConfiguredCorrectly() {
        // Arrange
        FindAccountByEmailUseCase useCase = config.findAccountByEmailUseCase(accountRepository);

        // Ensure the use case is created correctly
        assertNotNull(useCase);
        assertInstanceOf(FindAccountByEmailUseCase.class, useCase);
    }


    @Test
    void findAccountByIdUseCase_BeanConfiguredCorrectly() {
        // Arrange
        FindAccountByIdUseCase useCase = config.findAccountByIdUseCase(accountRepository);

        // Ensure the use case is created correctly
        assertNotNull(useCase);
        assertInstanceOf(FindAccountByIdUseCase.class, useCase);
    }
}