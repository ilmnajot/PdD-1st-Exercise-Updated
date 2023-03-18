package com.company.company.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    private Long id;
    @NotNull(message = "name cannot be blank, so please enter your name here ")
    private String name;
    @NotNull(message = "phone number cannot be blank, so please enter your name here ")
    private String  phoneNumber;

    private AddressDto address;
    private DepartmentDto department;
    private DepartmentDto departmentId;






}
