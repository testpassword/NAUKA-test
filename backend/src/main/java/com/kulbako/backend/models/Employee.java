package com.kulbako.backend.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Работник департамента.
 * @author Артемий Кульбако
 * @version 1.6
 */
@Data @Entity @Table(name = "employees")
public class Employee implements Serializable {

    @Transient private static final long serialVersionUID = 4L;
    @Id @GeneratedValue(strategy=GenerationType.AUTO) long id;
    //TODO: аватар
    //TODO: email field
    @NotNull private String name;
    @NotNull private String surname;
    @NotNull private Date bornDate;
    private String residency;
    @Enumerated(EnumType.STRING) private Role role;
    @ElementCollection private List<String> days;

    public Employee(@NotNull String name, @NotNull String surname, @NotNull Date bornDate, String residency, Role role) {
        this.name = name;
        this.surname = surname;
        this.bornDate = bornDate;
        this.residency = residency;
        this.role = role;
    }

    public Employee() {}
    //get и set методы определены аннотацией lombok

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(name, employee.name) &&
                Objects.equals(surname, employee.surname) &&
                Objects.equals(residency, employee.residency) &&
                role == employee.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, residency, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id = " + id +
                ", name = " + name +
                ", surname = " + surname +
                ", bornDate = " + bornDate +
                ", role = " + role.toString() + '}';
    }
}
