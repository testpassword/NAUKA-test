package requests;

import lombok.Data;
import models.Employee;

/**
 * Объект для передачи данных контроллеру {@link com.kulbako.backend.controllers.EmployeeController}.
 * @author Кульбако Артемий.
 * @version 1.0
 */
@Data
public class EmployeeDTO {

    private static final long serialVersionUID = 4L;
    private long departmentId;
    private Employee employee;

    public EmployeeDTO(long departmentId, Employee employee) {
        this.departmentId = departmentId;
        this.employee = employee;
    }
}
