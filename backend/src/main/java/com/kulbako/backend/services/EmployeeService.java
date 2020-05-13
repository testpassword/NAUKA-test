package com.kulbako.backend.services;

import com.kulbako.backend.models.Employee;
import com.kulbako.backend.repos.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Сервисный слой, управляющий сущностью {@link Employee}.
 * @author Артемий Кульбако
 * @version 1.0
 */
@Slf4j @Service
public class EmployeeService {

    private final EmployeeRepository empRepo;
    private final String slaveClassName = Employee.class.getSimpleName();

    @Autowired
    public EmployeeService(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    /**
     * Возвращает всех работников в виде {@link List<Employee>}.
     */
    @Transactional
    public List<Employee> getAll() {
        log.info("Запрос к БД на получение списка " + slaveClassName);
        return empRepo.getAll();
    }

    //Здесь и далее я не стал писать отдельные классы исключений ввиду низкой потребности в них в данном юзкейсе.
    /**
     * Удаляет запись о работнике из БД.
     * @param victim работник, запись о котором необходимо удалить.
     * @throws Exception если {@code victim} отсутствует в БД.
     */
    @Transactional
    public void remove(Employee victim) throws Exception {
        String id = victim.getEmail();
        log.info("Запрос к БД на удаление " + id);
        if (empRepo.existsByEmail(id)) {
            String errorMessage = "Не удалось удалить работника, т.к. " + id + " отсутствует в БД";
            log.info(errorMessage);
            throw new Exception(errorMessage);
        }
        else {
            empRepo.removeByEmail(id);
            log.info(id + " успешно удалён из БД");
        }
    }

    /**
     * Модифицирует запись о работнике в БД.
     * @param updatable работник, запись которого нужно изменить.
     * @throws Exception если {@code updatable} отсутствует в БД.
     */
    @Transactional
    public void modify(Employee updatable) throws Exception {
        String id = updatable.getEmail();
        log.info("Запрос к БД на модификацию " + id);
        if (empRepo.existsByEmail(id)) {
            String errorMessage = "Не удалось модифицировать данные работника, т.к. " + id + " отсутствует в БД";
            log.info(errorMessage);
            throw new Exception(errorMessage);
        }
        else {
            empRepo.save(updatable);
            log.info("Данные " + id + " успешно изменены");
        }
    }

    /**
     * Добавляет запись о работнике в БД.
     * @param newbie работник, запись о котором нужно добавить в БД.
     * @throws Exception если {@code newbie} уже существует.
     */
    @Transactional
    public void add(Employee newbie) throws Exception {
        String id = newbie.getEmail();
        log.info("Запрос к БД на добавление " + id);
        if (empRepo.existsByEmail(newbie.getEmail())) {
            String errorMessage = "Не удалось добавить работника, т.к. " + id + " уже есть в БД";
            log.info(errorMessage);
            throw new Exception(errorMessage);
        }
        else {
            empRepo.save(newbie);
            log.info(id + " успешно добавлен в БД");
        }
    }
}