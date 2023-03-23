package by.paprauka.entity;

import java.util.Objects;

public class User {

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.isMale = true;
    }

    private String name;
    private String surname;
    private Boolean isMale;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Boolean isMale() {
        System.out.println(isMale);
        return isMale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!Objects.equals(name, user.name)) {
            return false;
        }
        return Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
