package by.paprauka.database.entity;

import by.paprauka.database.entity.enam.Role;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 10)
    private Role role;

    @Embedded
    @AttributeOverride(name = "tel", column = @Column(name = "tel"))
    @AttributeOverride(name = "address", column = @Column(name = "address"))
    private Contact contact;
}
