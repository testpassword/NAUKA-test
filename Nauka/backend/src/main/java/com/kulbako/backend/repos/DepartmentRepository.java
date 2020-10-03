package com.kulbako.backend.repos;

import com.kulbako.backend.models.Department;
import com.kulbako.backend.models.Employee;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

/**
 * CRUD-репозиторий для {@link Department}.
 * @author Артемий Кульбако
 * @version 1.1
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    /**
     * Получить множество всех департаментов из БД.
     */
    Set<Department> findAll();

    /**
     * Удалить департамент по его id.
     * @param id выступает ключом для поиска в БД.
     */
    void removeById(long id);

    /**
     * Проверяет факт существования записи о департаменте в БД.
     * @param id выступает ключом для поиска в БД.
     * @return true если запись существует, false если запись отсутствует.
     */
    boolean existsById(long id);

    /**
     * Проверяет факт существования записи о департаменте в БД.
     * @param name выступает ключом для поиска в БД.
     * @return true если запись существует, false если запись отсутствует.
     */
    boolean existsByName(String name);

    /**
     * Получить департамент по его id.
     * @param id выступает ключом для поиска в БД.
     * @return сущность представляющую работника.
     */
    Department getById(long id);

    Department getByEmployeesContaining(Employee e);
}
