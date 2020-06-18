package com.kulbako.backend.controllers;

import com.kulbako.backend.models.Employee;
import com.kulbako.backend.requests.EmployeeDTO;
import com.kulbako.backend.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Set;

//TODO: хэширование
/**
 * Обрабатывает запросы к url-у /employee.
 * @author Артемий Кульбако
 * @version 1.7
 */
@Slf4j @RestController @RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService empServ;

    @Autowired public EmployeeController(EmployeeService employeeService) {
        this.empServ = employeeService;
    }

    /**
     * Добавляет запись о работнике в БД.
     * @param addable новый работник.
     * @return http-код, описывающий результат операции.
     */
    @PutMapping
    public ResponseEntity<String> add(@Valid @RequestBody EmployeeDTO addable) {
        try {
            empServ.add(addable.getDepartmentId(), addable.getEmployee());
            return new ResponseEntity<>("Работник добавлен в БД", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>("Такой работник уже существует", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Удаляет запись о работнике из БД.
     * @param removable удаляемый работник.
     * @return http-код, описывающий результат операции.
     */
    @DeleteMapping
    public ResponseEntity<String> remove(@Valid @RequestBody Employee removable) {
        try {
            empServ.remove(removable);
            return new ResponseEntity<>("Работник удалён из БД", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>("Такой работник не существует", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Модифицирует запись о работнике.
     * @param modifiable модифицируемый работник.
     * @return http-код, описывающий результат операции.
     */
    @PostMapping
    public ResponseEntity<String> modify(@Valid @RequestBody EmployeeDTO modifiable) {
        try {
            empServ.modify(modifiable.getEmployee());
            return new ResponseEntity<>("Данные работника изменены", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>("Такой работник не существует", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Возвращает всех работников.
     */
    @GetMapping
    public ResponseEntity<Set<Employee>> getAll() { return new ResponseEntity<>(empServ.getAll(), HttpStatus.OK); }
}