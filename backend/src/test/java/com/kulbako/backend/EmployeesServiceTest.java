package com.kulbako.backend;

import com.kulbako.backend.models.Employee;
import com.kulbako.backend.models.Role;
import com.kulbako.backend.services.EmployeeService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Date;

@SpringBootTest
class EmployeesServiceTest {

    @Autowired
    private EmployeeService service;

    @Test @Order(1)
    public void add() throws Exception {
        Employee test = new Employee("Lupa", "Pupovich", new Date(System.currentTimeMillis()), "TestStreet", Role.TIMEKEEPER);
        test.setId(0);
        service.add(0, test);
        Employee test1 = service.get(0);
        assertThat(test).isEqualTo(test1);
    }

    @Test @Order(2)
    public void get() throws Exception {
        Employee test = new Employee("qw", "er", new Date(System.currentTimeMillis()), "TestStreet", Role.EMPLOYEE_ADMIN);
        test.setId(0);
        service.add(0, test);
        Employee test1 = service.get(0);
        assertThat(test1).isNotNull();
    }

    @Test @Order(3)
    public void remove() {

    }

    @Test
    public void getAll() throws Exception {
        Employee a = new Employee("a", "b", new Date(System.currentTimeMillis()), "TestStreet", Role.EMPLOYEE_ADMIN);
        Employee b = new Employee("b", "c", new Date(System.currentTimeMillis()), "TestStreet", Role.EMPLOYEE_ADMIN);
        service.add(0, a);
        service.add(0, b);
        assertThat(service.getAll().size()).isNotZero().isNotNegative();
    }

    @Test
    public void modify() {}
}
