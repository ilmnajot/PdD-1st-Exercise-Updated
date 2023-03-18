package com.company.company.controller;
import com.company.company.entity.dto.ApiResponse;
import com.company.company.entity.dto.DepartmentDto;
import com.company.company.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    /**
     * we can get list of department here
     * @return list of departments
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllDepartments(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.getDepartments());
    }

    /**
     * we can get any department by its id
     * @param id
     * @return department
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable Long id){
      return ResponseEntity.status(HttpStatus.OK).body(departmentService.getById(id));
    }

    /**
     * we can create department
     * @param departmentDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.saveDepartment(departmentDto);

        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse)  ;
    }

    /**
     * we can delete by department by its id
     * @param id
     * @return APiResponse
     */
    @DeleteMapping("/{id}")
    public ApiResponse deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }

    /**
     * we can update any department and save it
     * @param id
     * @param departmentDto
     * @return department
     */
    @PutMapping("/{id}")
    public ApiResponse updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto){
        return departmentService.editDepartment(id, departmentDto);
    }
}
