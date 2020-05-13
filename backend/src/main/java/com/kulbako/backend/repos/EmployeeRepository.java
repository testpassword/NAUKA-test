package com.kulbako.backend.repos;

import com.kulbako.backend.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CRUD-репозиторий для {@link Employee}.
 * @author Артемий Кульбако
 * @version 1.0
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    /**
     * Получить всех работников из БД.
     */
    List<Employee> getAll();

    /**
     * Удалить работника по его email-адресу.
     * @param email выступает ключом для поиска в БД.
     */
    void removeByEmail(String email);

    /**
     * Проверить факт существования записи о работнике в БД.
     * @param email выступает ключом для поиска в БД.
     * @return true если запись существует, false если запись отсутствует.
     */
    boolean existsByEmail(String email);

    /**
     * Получить работника по его email-адресу.
     * @param email выступает ключом для поиска в БД.
     * @return сущность представляющую работника.
     */
    Employee getByEmail(String email);
}
