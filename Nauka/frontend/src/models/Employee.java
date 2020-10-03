package models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Работник департамента.
 * @author Артемий Кульбако
 * @version 1.6
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 4L;
    long id;
    private String name;
    private String surname;
    private Date bornDate;
    private String residency;
    private WorkingCalendar calendar;
    private Role role;

    public Employee(String name, String surname, Date bornDate, String residency, Role role, WorkingCalendar calendar) {
        this.name = name;
        this.surname = surname;
        this.bornDate = bornDate;
        this.residency = residency;
        this.role = role;
        this.calendar = calendar;
    }

    public Employee() {}

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public Date getBornDate() { return bornDate; }

    public void setBornDate(Date bornDate) { this.bornDate = bornDate; }

    public String getResidency() { return residency; }

    public void setResidency(String residency) { this.residency = residency; }

    public WorkingCalendar getCalendar() { return calendar; }

    public void setCalendar(WorkingCalendar calendar) { this.calendar = calendar; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public boolean getRemote() { return this.role.isRemote(); }

    public void setRemote(boolean status) { this.role.setRemote(status); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(name, employee.name) &&
                Objects.equals(surname, employee.surname) &&
                bornDate.equals(employee.bornDate) &&
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
