package com.company.company.entity.dto;

import com.company.company.entity.Department;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    @NotNull(message = "corpName cannot be blank, pls enter corpName!")
    private String corpName;

    @NotNull(message="director name cannot be blank, pls enter the director name")
    private String directorName;

    @NotNull(message="address cannot be blank")
    private String address;

    private List<DepartmentDto> departmentDtos;
}
