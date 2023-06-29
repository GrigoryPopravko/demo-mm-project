package by.paprauka.database.dto;

import by.paprauka.database.entity.enam.Gender;

import java.time.LocalDate;

public record RegistrationDto(
        String name,
        String surname,
        String email,
        String password,
        LocalDate date,
        Gender gender
) {
}
