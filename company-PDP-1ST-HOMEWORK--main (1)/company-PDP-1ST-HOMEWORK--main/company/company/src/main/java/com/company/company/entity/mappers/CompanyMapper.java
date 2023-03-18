package com.company.company.entity.mappers;

import com.company.company.entity.Company;
import com.company.company.entity.dto.CompanyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto companyToCompanyDto(Company company);
    Company companyDtoToCompanyEntity(CompanyDto companyDto);
}
