package com.kulbako.backend.controllers;

import com.kulbako.backend.models.Employee;
import com.kulbako.backend.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

//TODO: документация
//TODO: логирование
//TODO: хэширование
@Slf4j @RestController @RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService empServ;

    @Autowired public EmployeeController(EmployeeService employeeService) {
        this.empServ = employeeService;
    }

    @PutMapping
    private ResponseEntity<String> add(@Valid @RequestBody Employee addable) {
        try {
            empServ.add(addable);
            return new ResponseEntity<>("Работник добавлен в БД", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Такой работник уже существует", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    private ResponseEntity<String> remove(@Valid @RequestBody Employee removable) {
        try {
            empServ.remove(removable);
            return new ResponseEntity<>("Работник удалён из БД", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Такой работник не существует", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    private ResponseEntity<String> modify(@Valid @RequestBody Employee modifiable) {
        try {
            empServ.modify(modifiable);
            return new ResponseEntity<>("Данные работника изменены", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Такой работник не существует", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    private ResponseEntity<List<Employee>> getAll() { return new ResponseEntity<List<Employee>>(empServ.getAll(), HttpStatus.OK); }
}
