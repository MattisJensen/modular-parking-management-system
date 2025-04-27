package dk.sdu.mmmi.pms.application.shared;

public interface PasswordEncoder {
    /**
     * Encodes the provided raw password.
     *
     * @param rawPassword the password before encryption
     * @return the encoded password
     */
    String encode(String rawPassword);

    /**
     * Verifies if the provided raw password matches the previously encoded password.
     *
     * @param rawPassword   the password to verify
     * @param hashedPassword the encoded password for comparison
     * @return true if the passwords match; false otherwise
     */
    boolean matches(String rawPassword, String hashedPassword);
}
