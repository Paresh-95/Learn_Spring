package com.paresh.paresh.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.paresh.paresh.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "Number of character name should be in range of 2 to 10")
    private String name;

    @NotBlank(message = "email of employee cannot be blank")
    @Email(message = "Email should be valid email")
    private String email;

    @Max(value = 80, message = "age cannot be greater than 80")
    @Min(value = 12, message = "age cannot be less than 12")
    private Integer age;

    private Double salary;

    @NotBlank(message = "role of employee cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of employee can be ADMIN OR USER only")
    @EmployeeRoleValidation()
    private String role; //ADMIN OR USER

    @Past()
    private LocalDate dateofJoining;

    @JsonProperty("isActive")

    private Boolean isActive;

}
