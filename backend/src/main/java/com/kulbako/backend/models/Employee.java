package com.kulbako.backend.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

//TODO: документация на все классы и пакеты
//TODO: тесты
@Data @Entity @Table(name = "users")
public class Employee implements Serializable {

    @Transient private static final long serialVersionUID = 4L;
    @Id @NotNull @Email private String email;
    //TODO: аватар
    @NotNull private String name;
    @NotNull private String surname;
    @NotNull private Date bornDate;
    @Embedded private Address residency;
    @Enumerated(EnumType.STRING) private Role role;
    //TODO: +3 поля на усмотрение

    public Employee() {}
    //get и set методы определены аннотацией lombok
    //TODO: equals, hashCode, toString

    @Override
    public String toString() {
        return "User{" +
                "email = " + email +
                ", name = " + name +
                ", surname = " + surname +
                ", bornDate = " + bornDate +
                ", residency = " + residency +
                ", role = " + role.toString() + '}';
    }
}
