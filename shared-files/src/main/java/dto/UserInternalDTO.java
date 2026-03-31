package dto;

import java.util.Set;
import java.util.UUID;

public record UserInternalDTO(
        UUID id,
        String username,
        String email,
        String password,
        Set<String> roles
) {
}
