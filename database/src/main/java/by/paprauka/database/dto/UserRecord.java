package by.paprauka.database.dto;

import by.paprauka.database.entity.enam.Role;

public record UserRecord(Long id, String email, Role role, String tel) {
}
