package by.paprauka.database.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Contact {
    private String tel;
    private String address;
}
