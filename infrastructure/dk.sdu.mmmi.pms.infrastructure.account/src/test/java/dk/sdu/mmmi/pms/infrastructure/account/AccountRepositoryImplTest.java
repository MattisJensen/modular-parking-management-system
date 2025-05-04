package dk.sdu.mmmi.pms.infrastructure.account;

import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.AccountRole;
import dk.sdu.mmmi.pms.infrastructure.account.persistence.AccountJpaEntity;
import dk.sdu.mmmi.pms.infrastructure.account.persistence.AccountJpaRepository;
import dk.sdu.mmmi.pms.infrastructure.account.persistence.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountRepositoryImplTest {
    @Mock
    private AccountJpaRepository springDataRepo;
    @Mock
    private AccountMapper mapper;

    private AccountRepositoryImpl repository;
    private UUID testId;
    private Account testAccount;
    private AccountJpaEntity testEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new AccountRepositoryImpl(springDataRepo, mapper);
        testId = UUID.randomUUID();
        testAccount = new Account(testId, "Test User", "test@mail.com", "password", AccountRole.USER);
        testEntity = new AccountJpaEntity(testId, "Test User", "test@mail.com", "password", AccountRole.USER);

        when(mapper.toJpaEntity(any(Account.class))).thenReturn(testEntity);
        when(mapper.toCore(any(AccountJpaEntity.class))).thenReturn(testAccount);
    }


    @Test
    void save_ValidAccount_SavesMappedEntity() {
        // Arrange
        repository.save(testAccount);

        // Verify that the repository was called with the correct parameters
        verify(springDataRepo).save(testEntity);
    }


    @Test
    void update_ValidAccount_SavesMappedEntity() {
        // Arrange
        repository.update(testAccount);

        // Verify that the repository was called with the correct parameters
        verify(springDataRepo).save(testEntity);
    }


    @Test
    void deleteById_CallsJpaRepository() {
        // Arrange
        repository.deleteById(testId);

        // Verify that the repository was called with the correct parameters
        verify(springDataRepo).deleteById(testId);
    }


    @Test
    void findById_ExistingId_ReturnsMappedAccount() {
        // Arrange
        when(springDataRepo.findById(testId)).thenReturn(Optional.of(testEntity));
        Optional<Account> result = repository.findById(testId);

        // Ensure that calling findById returns the expected result
        assertTrue(result.isPresent());
        assertEquals(testAccount, result.get());
    }


    @Test
    void findById_NonExistingId_ReturnsEmpty() {
        // Arrange
        when(springDataRepo.findById(testId)).thenReturn(Optional.empty());
        Optional<Account> result = repository.findById(testId);

        // Ensure that calling findById returns an empty result
        assertTrue(result.isEmpty());
    }


    @Test
    void findByEmail_ExistingEmail_ReturnsMappedAccount() {
        // Arrange
        String email = "test@mail.com";
        when(springDataRepo.findByEmail(email)).thenReturn(Optional.of(testEntity));

        Optional<Account> result = repository.findByEmail(email);

        // Ensure that calling findByEmail returns the expected result
        assertTrue(result.isPresent());
        assertEquals(testAccount, result.get());
    }


    @Test
    void findByEmail_NonExistingEmail_ReturnsEmpty() {
        // Arrange
        String email = "missing@mail.com";
        when(springDataRepo.findByEmail(email)).thenReturn(Optional.empty());

        Optional<Account> result = repository.findByEmail(email);

        // Ensure that calling findByEmail returns an empty result
        assertTrue(result.isEmpty());
    }
}