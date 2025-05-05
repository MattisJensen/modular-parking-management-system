package dk.sdu.mmmi.pms.presentation.account;

import dk.sdu.mmmi.pms.application.account.usecase.*;
import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.AccountRole;
import dk.sdu.mmmi.pms.core.account.exceptions.EmailFormatException;
import dk.sdu.mmmi.pms.presentation.account.datatransferobjects.AccountResponse;
import dk.sdu.mmmi.pms.presentation.account.datatransferobjects.CreateAccountRequest;
import dk.sdu.mmmi.pms.presentation.account.datatransferobjects.UpdateAccountRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class AccountControllerTest {
    @Mock
    private CreateAccountUseCase createAccountUseCase;
    @Mock
    private UpdateAccountUseCase updateAccountUseCase;
    @Mock
    private FindAccountByIdUseCase findAccountByIdUseCase;
    @Mock
    private FindAccountByEmailUseCase findAccountByEmailUseCase;

    private AccountController controller;
    private UUID testId;
    private Account testAccount;

    @BeforeEach
    void setUp() {
        openMocks(this);
        controller = new AccountController(
                createAccountUseCase,
                updateAccountUseCase,
                findAccountByEmailUseCase,
                findAccountByIdUseCase
        );
        testId = UUID.randomUUID();
        testAccount = new Account(testId, "Test User", "test@mail.com", "hash", AccountRole.USER);
    }


    @Test
    void createAccount_ReturnsCreatedResponse() {
        // Arrange
        CreateAccountRequest request = new CreateAccountRequest("Test User", "test@mail.com", "password", AccountRole.USER);
        when(createAccountUseCase.execute(any(), any(), any(), any())).thenReturn(testId);

        ResponseEntity<AccountResponse> response = controller.createAccount(request);

        // Ensures the response is created successfully
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        AccountResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(testId, body.id());
        assertEquals(request.name(), body.name());
        assertEquals(request.email(), body.email());
        assertEquals(request.role(), body.role());
    }


    @Test
    void updateAccount_ValidRequest_ReturnsNoContent() {
        // Arrange
        UpdateAccountRequest request = new UpdateAccountRequest("New Name", "new@mail.com", "newPass", AccountRole.ADMIN);

        ResponseEntity<?> response = controller.updateAccount(testId.toString(), request);

        // Ensures the response is no content (successful processed, but no content to return)
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(updateAccountUseCase).execute(eq(testId), any());
    }


    @Test
    void updateAccount_InvalidUUID_ReturnsBadRequest() {
        // Arrange
        ResponseEntity<?> response = controller.updateAccount("invalid-uuid", new UpdateAccountRequest(null, null, null, null));

        // Ensures the response is bad request
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void updateAccount_EmailFormatError_ReturnsBadRequest() {
        // Arrange
        UpdateAccountRequest request = new UpdateAccountRequest(null, "bad-email", null, null);
        doThrow(new EmailFormatException("Invalid email")).when(updateAccountUseCase).execute(any(), any());

        ResponseEntity<?> response = controller.updateAccount(testId.toString(), request);

        // Ensures the response is bad request
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void getAccountById_ValidId_ReturnsAccount() {
        // Arrange
        when(findAccountByIdUseCase.execute(testId)).thenReturn(testAccount);

        ResponseEntity<?> response = controller.getAccountById(testId.toString());

        // Ensures the response is OK and contains the expected account
        assertEquals(HttpStatus.OK, response.getStatusCode());
        AccountResponse body = (AccountResponse) response.getBody();
        assertNotNull(body);
        assertEquals(testAccount.id(), body.id());
        assertEquals(testAccount.name(), body.name());
    }


    @Test
    void getAccountById_InvalidId_ReturnsBadRequest() {
        // Arrange
        ResponseEntity<?> response = controller.getAccountById("invalid-id");

        // Ensures the response is bad request
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void getAccountByEmail_ReturnsAccountResponse() {
        // Arrange
        when(findAccountByEmailUseCase.execute("test@mail.com")).thenReturn(testAccount);

        AccountResponse response = controller.getAccountByEmail("test@mail.com");

        // Ensures the response contains the expected account
        assertEquals(testAccount.id(), response.id());
        assertEquals(testAccount.name(), response.name());
        assertEquals(testAccount.email(), response.email());
    }
}