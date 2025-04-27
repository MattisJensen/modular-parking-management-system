package dk.sdu.mmmi.pms.infrastructure.security;

import dk.sdu.mmmi.pms.application.shared.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Adapter that wraps the Spring Security {@link BCryptPasswordEncoder} and implements the
 * application's {@link PasswordEncoder} interface.
 *
 * Follows the Adapter design pattern by converting the interface of
 * {@code BCryptPasswordEncoder} into the interface expected by the application.
 */
@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoder {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}