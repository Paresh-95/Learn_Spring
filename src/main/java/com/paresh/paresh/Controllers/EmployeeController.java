package com.paresh.paresh.Controllers;


import com.paresh.paresh.Dto.EmployeeDTO;
import com.paresh.paresh.Service.EmployeeService;
import exceptions.ResourceNotFound;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Optional<EmployeeDTO> employeeDTO =  employeeService.getEmployeeById(id);
       return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()-> new ResourceNotFound("Employee not found with id"+id));
    }



    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }



    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmp) {
        EmployeeDTO savedEmp = employeeService.addEmployee(inputEmp);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody  @Valid  EmployeeDTO inputEmp,@PathVariable Long id){
        return ResponseEntity.ok(employeeService.updateEmployee(inputEmp,id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long id){
        boolean getDeleted =employeeService.deleteEmployeebyId(id);
        if(getDeleted){
            ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }


    @PatchMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployee(@PathVariable Long id, @RequestBody  @Valid  Map<String,Object> updates ){
        EmployeeDTO employeeUpdated =  employeeService.updatePartialData(id,updates);
        if(employeeUpdated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeUpdated);
    }

}
