package by.paprauka.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
public abstract class CreatableEntity<T extends Serializable> implements BaseEntity<T> {

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDate created;
}
