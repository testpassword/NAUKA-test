package com.kulbako.backend.services;

import com.kulbako.backend.models.Employee;
import com.kulbako.backend.repos.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

/**
 * Сервисный слой, управляющий сущностью {@link Employee}.
 * @author Артемий Кульбако
 * @version 1.3
 */
@Slf4j @Service
public class EmployeeService {

    private final EmployeeRepository empRepo;
    private final DepartmentService depServ;
    private final String slaveClassName = Employee.class.getSimpleName();

    @Autowired
    public EmployeeService(EmployeeRepository empRepo, DepartmentService depServ) {
        this.empRepo = empRepo;
        this.depServ = depServ;
    }

    /**
     * Возвращает всех работников.
     */
    @Transactional
    public Set<Employee> getAll() {
        log.info("Запрос к БД на получение списка " + slaveClassName);
        return empRepo.findAll();
    }

    //Здесь и далее я не стал писать отдельные классы исключений ввиду низкой потребности в них в данном юзкейсе.
    /**
     * Возвращает одного работника.
     * @param id номер необходимого работника.
     * @throws Exception если работника с заданным номером не существует.
     */
    @Transactional
    public Employee get(long id) throws Exception {
        log.info("Запрос к БД на получение работника " + id);
        Employee employee = empRepo.getById(id);
        if (employee != null) return employee;
        else throw new Exception("Работника " + id + " не существует");
    }

    /**
     * Удаляет запись о работнике из БД.
     * @param departmentId номер департамента к которому прикреплён работник.
     * @param victim работник, запись о котором необходимо удалить.
     * @throws Exception если работник отсутствует в БД.
     */
    @Transactional
    public void remove(Employee victim) throws Exception {
        long id = victim.getId();
        log.info("Запрос к БД на удаление " + id);
        if (empRepo.existsById(id)) {
            depServ.untieEmployeeFromDepartment(victim);
            empRepo.removeById(id);
            log.info(id + " успешно удалён из БД");
        } else {
            String errorMessage = "Не удалось удалить работника, т.к. " + id + " отсутствует в БД";
            log.info(errorMessage);
            throw new Exception(errorMessage);
        }
    }

    /**
     * Модифицирует запись о работнике в БД.
     * @param updatable работник, запись которого нужно изменить.
     * @throws Exception если работник отсутствует в БД.
     */
    @Transactional
    public void modify(Employee updatable) throws Exception {
        long id = updatable.getId();
        log.info("Запрос к БД на модификацию " + id);
        if (empRepo.existsById(id)) {
            empRepo.save(updatable);
            log.info("Данные " + id + " успешно изменены");
        } else {
            String errorMessage = "Не удалось модифицировать данные работника, т.к. " + id + " отсутствует в БД";
            log.info(errorMessage);
            throw new Exception(errorMessage);
        }
    }

    /**
     * Добавляет запись о работнике в БД.
     * @param newbie работник, запись о котором нужно добавить в БД.
     * @throws Exception если работник уже существует.
     */
    @Transactional
    public void add(long departmentId, Employee newbie) throws Exception {
        long id = newbie.getId();
        log.info("Запрос к БД на добавление " + id);
        if (empRepo.existsById(id)) {
            String errorMessage = "Не удалось добавить работника, т.к. " + id + " уже есть в БД";
            log.info(errorMessage);
            throw new Exception(errorMessage);
        } else {
            empRepo.save(newbie);
            depServ.tieEmployeeToDepartment(departmentId, newbie);
            log.info(id + " успешно добавлен в БД");
        }
    }
}