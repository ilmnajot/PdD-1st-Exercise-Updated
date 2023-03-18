package com.company.company.entity.dto;

import com.company.company.entity.Worker;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    @NotNull(message="name cannot be blank")
    private String name;
    @NotNull(message="company cannot be blank")
    private String company;
    private WorkerDto worker;

}
