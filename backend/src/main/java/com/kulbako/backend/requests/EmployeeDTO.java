package com.kulbako.backend.requests;

import com.kulbako.backend.models.Employee;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * Объект для передачи данных контроллеру {@link com.kulbako.backend.controllers.EmployeeController}.
 * @author Кульбако Артемий.
 * @version 1.0
 */
@Data
public class EmployeeDTO {

    private static final long serialVersionUID = 4L;
    @NotNull private long departmentId;
    @NotNull private Employee employee;
}
