package models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Департамента предприятия.
 * @author Артемий Кульбако
 * @version 1.4
 */
public class Department implements Serializable {

    private static final long serialVersionUID = 4L;
    long id;
    private String name;
    private Set<Employee> employees;

    public Department(String name, Set<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public Department(String name) {
        this.name = name;
    }

    public Department() {}

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Employee> getEmployees() { return employees; }

    public void setEmployees(Set<Employee> employees) { this.employees = employees; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employees);
    }

    @Override
    public String toString() { return "Department{" + "id = " + id + ", name = " + name + '}'; }
}
