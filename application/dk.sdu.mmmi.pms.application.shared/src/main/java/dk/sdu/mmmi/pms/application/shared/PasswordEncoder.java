package dk.sdu.mmmi.pms.application.shared;

public interface PasswordEncoder {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
