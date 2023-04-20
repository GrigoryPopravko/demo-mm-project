package by.paprauka.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String gender;
    private Contact contact;
}
