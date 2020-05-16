package com.kulbako.backend;

import com.kulbako.backend.models.Department;
import com.kulbako.backend.repos.DepartmentRepository;
import com.kulbako.backend.services.DepartmentService;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartmentServiceTest {

    DepartmentService service;
    DepartmentRepository repository;
    Department testDepartment;

    {
        this.testDepartment = new Department("TESTERS");
    }

    @Autowired
    DepartmentServiceTest(DepartmentService service, DepartmentRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Test @Order(1)
    public void add() throws Exception {
        service.add(this.testDepartment);
        assertThat(repository.findAll()).isNotNull();
    }

    @Test @Order(2)
    public void get() throws Exception { assertThat(service.get(1)).isNotNull(); }

    @Test @Order(3)
    public void tieEmployeeToDepartment() throws Exception {

    }

    @Test @Order(4)
    public void untieEmployeeFromDepartment() throws Exception {

    }

    @Test
    public void getAll() { assertThat(service.getAll()).isEqualTo(repository.findAll()); }

    @Test
    public void remove() throws Exception {
        this.testDepartment.setId(1);
        service.remove(this.testDepartment);
        assertThat(repository.getById(1)).isNull();
    }
}