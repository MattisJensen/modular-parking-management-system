package dk.sdu.mmmi.pms.application.shared;

public interface TokenManager {
    String generateToken(String attribute);
    String extractAttribute(String token);
}
