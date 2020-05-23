package com.kulbako.backend;

import com.kulbako.backend.models.Department;
import com.kulbako.backend.models.Employee;
import com.kulbako.backend.repos.DepartmentRepository;
import com.kulbako.backend.services.DepartmentService;
import static org.assertj.core.api.Assertions.*;
import com.kulbako.backend.services.EmployeeService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

/*
ПЕРЕД ВЫПОЛНЕНИЕМ ТЕСТОВ ОЧИСТИТЬ ТАБЛИЦЫ В БД
 */
@SpringBootTest
public class DepartmentServiceTest {

    DepartmentService depServ;
    EmployeeService empServ;
    DepartmentRepository repository;
    Department testDepartment;

    {
        this.testDepartment = new Department("TESTERS");
    }

    @Autowired
    DepartmentServiceTest(DepartmentService depServ, DepartmentRepository repository, EmployeeService empServ) {
        this.depServ = depServ;
        this.empServ = empServ;
        this.repository = repository;
    }

    @Test @Order(1)
    public void add() throws Exception {
        depServ.add(this.testDepartment);
        assertThat(repository.findAll()).isNotNull();
    }

    @Test @Order(2)
    public void get() throws Exception { assertThat(depServ.get(1)).isNotNull(); }

    @Test @Order(3)
    public void tieEmployeeToDepartment() throws Exception {
        /*
        Создпть работника: Employee e = new Employee("a", "b", new Date(), null, null, null);
        Привязать к департаменту: depServ.tieEmployeeToDepartment(0, e);
        Проверить, выбросит ли исключение при попытке удалить работника или департамент
        */
    }

    @Test @Order(4)
    public void untieEmployeeFromDepartment() throws Exception {
        /**
         * Удалить успешно
         */
    }

    @Test
    public void getAll() { assertThat(depServ.getAll()).isEqualTo(repository.findAll()); }

    @Test
    public void remove() throws Exception {
        this.testDepartment.setId(1);
        depServ.remove(this.testDepartment);
        assertThat(repository.getById(1)).isNull();
    }
}