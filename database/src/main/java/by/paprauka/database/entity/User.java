package by.paprauka.database.entity;

import by.paprauka.database.entity.enam.Gender;
import by.paprauka.database.entity.enam.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class User {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Gender gender;
    private Role role;
    private Contact contact;
}
