package com.company.company.controller;
import com.company.company.entity.dto.ApiResponse;
import com.company.company.entity.dto.CompanyDto;
import com.company.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    /**
     * here we can list of companies
     * @return list of companies
     */
    @GetMapping("/all")
    public ResponseEntity<List<CompanyDto>> getCompanies(){
        return new ResponseEntity<List<CompanyDto>>( companyService.getCompanies(), HttpStatus.OK);
    }

    /**
     * we can get any address by its id
     * @param id
     * @return company
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompanyById(id));
    }

    /**
     * we can create company here
     * @param companyDto
     * @return ApiResponse
     */
    @PostMapping
    public ResponseEntity<?> addCompany(@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.saveCompany(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * we can delete any company data by its id
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ApiResponse deleteCompany(@PathVariable Long id){
        ApiResponse apiResponse = companyService.deleteById(id);
        return apiResponse;
    }

    /**
     * we can update by its id and save it
     * @param id
     * @param companyDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto){
        return ResponseEntity.status(HttpStatus.OK).body(companyService.editCompany(id, companyDto));
    }
}
