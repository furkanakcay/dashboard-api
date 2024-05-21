package com.akcay.dashboard.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Gender must not be null")
    @Pattern(regexp = "Male|Female", message = "Gender must be either 'Male' or 'Female'")
    private String gender;
    @PositiveOrZero(message = "Salary must not be negative")
    private double salary;
    private String title;
}
