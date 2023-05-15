package by.paprauka.database.entity;

import by.paprauka.database.entity.enam.Gender;
import by.paprauka.database.entity.enam.Role;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends CreatableEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "surname", length = 50, nullable = false)
    private String surname;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 10)
    private Role role;

    @Embedded
    @AttributeOverride(name = "tel", column = @Column(name = "tel"))
    @AttributeOverride(name = "address", column = @Column(name = "address"))
    private Contact contact;
}