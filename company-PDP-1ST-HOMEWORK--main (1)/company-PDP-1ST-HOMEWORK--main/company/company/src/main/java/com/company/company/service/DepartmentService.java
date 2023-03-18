package com.company.company.service;
import com.company.company.entity.Department;
import com.company.company.entity.mappers.DepartmentMapper;
import com.company.company.repository.DepartmentRepository;
import com.company.company.entity.dto.ApiResponse;
import com.company.company.entity.dto.DepartmentDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper mapper;
    /**
     * we can get list of department here
     * @return list of departments
     */
    public List<DepartmentDto> getDepartments(){
        return departmentRepository.findAll(Sort.by("id"))
                .stream()
                .map(mapper::departmentToDepartmentDto)
                .collect(Collectors.toList());
    }


//    private  DepartmentDto departmentDtoConvertToDto(Department department){
////        DepartmentDto departmentDto = new DepartmentDto();
////        departmentDto.setCompany(department.getCompany());
////        departmentDto.setName(department.getName());
//    return mapper.map(department, DepartmentDto.class);
//    }//     private Department departmentDtoToDepartmentEntity(DepartmentDto departmentDto){
////        Department department = new Department();
////        department.setCompany(departmentDto.getCompany());
////        department.setId(departmentDto.getId());
////        department.setCompany(departmentDto.getCompany());
//        return mapper.map(departmentDto, Department.class);
//
//
//     }
    /**
     * we can get any department by its id
     * @param id
     * @return department
     */
    public Department getById(Long id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()){
            return departmentRepository.getReferenceById(id);
        }
        return null;
    }
    /**
     * we can create department
     * @param departmentDto
     * @return ApiResponse
     */
    public ApiResponse saveDepartment(DepartmentDto departmentDto){
        boolean existsByName = departmentRepository.existsByName(departmentDto.getName());
        if (existsByName){
            return new ApiResponse("this name is already taken", false);
        }
        Department department = mapper.departmentDtoDepartment(departmentDto);
//        Department department = new Department();
//        department.setCompany(departmentDto.getCompany());
//        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("saved!", true);
    }
    /**
     * we can delete by department by its id
     * @param id
     * @return APiResponse
     */
    public ApiResponse deleteDepartment(Long id){
        departmentRepository.deleteById(id);
        return new ApiResponse("the data deleted", true);
    }
    /**
     * we can update any department and save it
     * @param id
     * @param departmentDto
     * @return department
     */
    public ApiResponse editDepartment(Long id, DepartmentDto departmentDto){
        boolean byNameAndIdNot = departmentRepository.existsByNameAndId(departmentDto.getName(), id);
        if (byNameAndIdNot){
            return new ApiResponse("this name is already is taken", false);
        }
        Optional<Department> byId = departmentRepository.findById(id);
        if (byId.isEmpty()){
            return new ApiResponse("this name is not here", false);
        }
        Department department = mapper.departmentDtoDepartment(departmentDto);
        departmentDto.setId(id);
//        Department department = new Department();
//        department.setName(departmentDto.getName());
//        department.setCompany(departmentDto.getCompany());d
        departmentRepository.save(department);
        return new ApiResponse("saved seccssefully", true);
    }


}
