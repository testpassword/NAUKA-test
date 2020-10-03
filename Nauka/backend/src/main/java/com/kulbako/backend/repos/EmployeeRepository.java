package com.kulbako.backend.repos;

import com.kulbako.backend.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

/**
 * CRUD-репозиторий для {@link Employee}.
 * @author Артемий Кульбако
 * @version 1.1
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    /**
     * Получить множество всех работников из БД.
     */
    Set<Employee> findAll();

    /**
     * Удалить работника по его id.
     * @param id выступает ключом для поиска в БД.
     */
    void removeById(long id);

    /**
     * Проверяет факт существования записи о работнике в БД.
     * @param id выступает ключом для поиска в БД.
     * @return true если запись существует, false если запись отсутствует.
     */
    boolean existsById(long id);

    /**
     * Получить работника по его id.
     * @param id выступает ключом для поиска в БД.
     * @return сущность представляющую работника.
     */
    Employee getById(long id);
}
