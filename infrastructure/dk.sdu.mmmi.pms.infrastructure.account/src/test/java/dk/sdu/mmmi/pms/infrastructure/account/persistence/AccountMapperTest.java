package dk.sdu.mmmi.pms.infrastructure.account.persistence;

import dk.sdu.mmmi.pms.core.account.Account;
import dk.sdu.mmmi.pms.core.account.AccountRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    private AccountMapper mapper;
    private UUID testId;
    private String testName;
    private String testEmail;
    private String testPassword;
    private AccountRole testRole;

    @BeforeEach
    void setUp() {
        mapper = new AccountMapper();
        testId = UUID.randomUUID();
        testName = "Test User";
        testEmail = "test@example.com";
        testPassword = "securePassword";
        testRole = AccountRole.ADMIN;
    }

    @Test
    void toJpaEntity_MapsAllFieldsCorrectly() {
        // Arrange
        Account coreAccount = new Account(
                testId, testName, testEmail, testPassword, testRole
        );

        AccountJpaEntity jpaEntity = mapper.toJpaEntity(coreAccount);

        // Assert
        assertEquals(testId, jpaEntity.getId());
        assertEquals(testName, jpaEntity.getName());
        assertEquals(testEmail, jpaEntity.getEmail());
        assertEquals(testPassword, jpaEntity.getPassword());
        assertEquals(testRole, jpaEntity.getAccountRole());
    }

    @Test
    void toCore_MapsAllFieldsCorrectly() {
        // Arrange
        AccountJpaEntity jpaEntity = new AccountJpaEntity(
                testId, testName, testEmail, testPassword, testRole
        );

        Account coreAccount = mapper.toCore(jpaEntity);

        // Assert
        assertEquals(testId, coreAccount.id());
        assertEquals(testName, coreAccount.name());
        assertEquals(testEmail, coreAccount.email());
        assertEquals(testPassword, coreAccount.password());
        assertEquals(testRole, coreAccount.accountRole());
    }
}