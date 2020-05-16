package com.kulbako.backend.services;

import com.kulbako.backend.models.Department;
import com.kulbako.backend.models.Employee;
import com.kulbako.backend.repos.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

/**
 * Сервисный слой, управляющий сущностью {@link Department}.
 * @author Артемий Кульбако
 * @version 1.3
 */
@Slf4j @Service
public class DepartmentService {

    private final DepartmentRepository depRepo;
    private final String slaveClassName = Department.class.getSimpleName();

    @Autowired DepartmentService(DepartmentRepository depRepo) {
        this.depRepo = depRepo;
    }

    /**
     * Возвращает все департаменты.
     */
    @Transactional
    public Set<Department> getAll() {
        log.info("Запрос к БД на получение списка " + slaveClassName);
        return depRepo.findAll();
    }

    /**
     * Возвращает один департамент.
     * @param id номер необходимого департамента.
     * @throws Exception если департамента с заданным номером не существует.
     */
    @Transactional
    public Department get(long id) throws Exception {
        log.info("Запрос к БД на получение департамента " + id);
        Department department = depRepo.getById(id);
        if (department != null) return department;
        else throw new Exception("Департамента " + id + " не существует");
    }

    /**
     * Удаляет запись о департаменте из БД.
     * @param target департамент, запись о котором нужно удалить.
     * @throws Exception если департамент отсутствует в БД.
     */
    @Transactional
    public void remove(Department target) throws Exception {
        long id = target.getId();
        log.info("Запрос к БД на удаление " + id);
        if (depRepo.existsById(id)) {
            depRepo.removeById(id);
            log.info(id + " успешно удалён из БД");
        } else {
            String errorMessage = "Не удалось закрыть департамент, т.к. " + id + " отсутствует в БД";
            log.info(errorMessage);
            throw new Exception(errorMessage);
        }
    }

    /**
     * Добавляет запись о департаменте в БД.
     * @param newbie департамент, запись о котором нужно добавить в БД.
     * @throws Exception если департамент уже существует.
     */
    @Transactional
    public void add(Department newbie) throws Exception {
        log.info("Запрос к БД на добавление " + newbie);
        if (depRepo.existsByName(newbie.getName())) {
            String errorMessage = "Не удалось добавить департамент, т.к. " + newbie + " уже есть в БД";
            log.info(errorMessage);
            throw new Exception(errorMessage);
        } else {
            depRepo.save(newbie);
            log.info(newbie + " успешно добавлен в БД");
        }
    }

    /**
     * Прикрепляет работника к департаменту.
     * @param departmentId номер департамента, к которому нужно прикрепить работника.
     * @param newbie прикрепляемый работник.
     * @throws Exception если департамента с заданным номером не существует.
     */
    @Transactional
    public void tieEmployeeToDepartment(long departmentId, Employee newbie) throws Exception {
        log.info("Запрос на привязку работника " + newbie.getId() + " к департаменту " + departmentId);
        Department department = this.get(departmentId);
        department.getEmployees().add(newbie);
        depRepo.save(department);
    }

    /**
     * Открепляет работника от департамента.
     * @param departmentId номер департамента, от которого будет отвязан работник.
     * @param victim открепляемый работник.
     * @throws Exception если департамента с заданным номером не существует.
     */
    @Transactional
    public void untieEmployeeFromDepartment(long departmentId, Employee victim) throws Exception {
        log.info("Запрос на отвязку работника " + victim.getId() + " к департаменту " + departmentId);
        Department department = this.get(departmentId);
        department.getEmployees().remove(victim);
        depRepo.save(department);
    }
}
