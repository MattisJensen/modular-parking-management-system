package dk.sdu.mmmi.pms.infrastructure.security;

import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordEncoderAdapterTest {

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoderAdapter();
    }


    @Test
    void encode_ReturnsNonNullHash() {
        // Arrange
        String rawPassword = "securePassword123";

        String hashed = passwordEncoder.encode(rawPassword);

        // Ensure the password is hashed correctly
        assertNotNull(hashed);
        assertTrue(hashed.length() > 0);
    }


    @Test
    void encode_GeneratesDifferentHashesForSamePassword() {
        // Arrange
        String rawPassword = "password";

        String hash1 = passwordEncoder.encode(rawPassword);
        String hash2 = passwordEncoder.encode(rawPassword);

        // Ensure that the hashes are different
        assertNotEquals(hash1, hash2);
    }


    @Test
    void matches_ReturnsTrueForValidPassword() {
        // Arrange
        String rawPassword = "correctPassword";
        String hashedPassword = passwordEncoder.encode(rawPassword);

        // Ensure the password is hashed correctly
        assertTrue(passwordEncoder.matches(rawPassword, hashedPassword));
    }


    @Test
    void matches_ReturnsFalseForInvalidPassword() {
        // Arrange
        String correctPassword = "rightPassword";
        String wrongPassword = "wrongPassword";
        String hashedPassword = passwordEncoder.encode(correctPassword);

        // Ensure the password is hashed correctly
        assertFalse(passwordEncoder.matches(wrongPassword, hashedPassword));
    }


    @Test
    void matches_HandlesEmptyPassword() {
        // Arrange
        String emptyPassword = "";
        String hashedPassword = passwordEncoder.encode(emptyPassword);

        // Ensure the password is hashed correctly
        assertTrue(passwordEncoder.matches(emptyPassword, hashedPassword));
    }
}