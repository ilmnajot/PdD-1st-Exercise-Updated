package com.company.company.entity.mappers;

import com.company.company.entity.Department;
import com.company.company.entity.dto.DepartmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDto departmentToDepartmentDto(Department department);
    Department departmentDtoDepartment(DepartmentDto departmentDto);
}
