package com.kulbako.backend.controllers;

import com.kulbako.backend.models.Department;
import com.kulbako.backend.services.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Set;

/**
 * Обрабатывает запросы к url-у /departments.
 * @author Артемий Кульбако
 * @version 1.5
 */
@Slf4j @RestController @RequestMapping(path = "department")
public class DepartmentController {

    private final DepartmentService depServ;

    @Autowired public DepartmentController(DepartmentService depServ) {
        this.depServ = depServ;
    }

    /**
     * Добавляет запись о департаменте в БД.
     * @param addable новый департамент.
     * @return http-код, описывающий результат операции.
     */
    @PutMapping
    public ResponseEntity<String> add(@Valid @RequestBody Department addable) {
        try {
            depServ.add(addable);
            return new ResponseEntity<>("Департамент добавлен в БД", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>("Такой департамент уже существует", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Удаляет запись о департаменте из БД.
     * @param removable удаляемый департамент.
     * @return http-код, описывающий результат операции.
     */
    @DeleteMapping
    //TODO: при удалении департамента удалять всех его работников
    public ResponseEntity<String> remove(@Valid @RequestBody Department removable) {
        try {
            depServ.remove(removable);
            return new ResponseEntity<>("Департамент удалён из БД", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>("Такой департамент не существует", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Возвращает все департаменты.
     */
    @GetMapping
    public ResponseEntity<Set<Department>> getAll() {
        return new ResponseEntity<>(depServ.getAll(), HttpStatus.OK);
    }
}