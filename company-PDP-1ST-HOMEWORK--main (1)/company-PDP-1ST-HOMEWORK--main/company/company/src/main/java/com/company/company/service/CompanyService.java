package com.company.company.service;

import com.company.company.entity.Company;
import com.company.company.entity.mappers.CompanyMapper;
import com.company.company.repository.CompanyRepository;
import com.company.company.entity.dto.ApiResponse;
import com.company.company.entity.dto.CompanyDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {


    private final CompanyRepository companyRepository;
    private final CompanyMapper mapper;
    /**
     * here we can list of companies
     * @return list of companies
     */
    public List<CompanyDto> getCompanies(){
        return companyRepository.findAll(Sort.by("id"))
                .stream()
                .map(mapper::companyToCompanyDto)
                .collect(Collectors.toList());


    }

    /**
     * here we will convert entity of Company to CompanyDto
     * @param company
     * @return list of companyDto
     */
    //        private CompanyDto convertCompanyToDto(Company company){
////        CompanyDto companyDto = new CompanyDto();
////        companyDto.setAddress(company.getAddress());
////        companyDto.setCorpName(company.getCorpName());
////        companyDto.setDirectorName(company.getDirectorName());
//        return mapper.map(company, CompanyDto.class);
//        }
//        private Company convertCompanyDtoToCompany(CompanyDto companyDto){
////            Company company =  new Company();
////            company.setId(companyDto.getId());
////            company.setCorpName(companyDto.getCorpName());
////            company.setDirectorName(companyDto.getDirectorName());
////            company.setAddress(companyDto.getAddress());
//            return mapper.map(companyDto, Company.class);
//        }

    /**
     * we can get any address by its id
     * @param id
     * @return company
     */
    public Company getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()){
            return companyRepository.getReferenceById(id);
        }
        return null ;
    }
    /**
     * we can create company here
     * @param companyDto
     * @return ApiResponse
     */
    public ApiResponse saveCompany(CompanyDto companyDto){
        boolean byName = companyRepository.existsByCorpName(companyDto.getCorpName());

        if (byName){
            return new ApiResponse("this name is not aviable", false);
        }
        Company company = mapper.companyDtoToCompanyEntity(companyDto);
//        Company company = new Company();
//        company.setAddress(companyDto.getAddress());
//        company.setCorpName(companyDto.getCorpName());
//        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("data saved!", true);
    }
    /**
     * we can delete any company data by its id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteById(Long id){
        companyRepository.deleteById(id);
        return new ApiResponse("the data deleted", true);
    }
    /**
     * we can update by its id and save it
     * @param id
     * @param companyDto
     * @return ApiResponse
     */
    public ApiResponse editCompany(Long id, CompanyDto companyDto){
        boolean nameAndIdNot = companyRepository.existsByCorpNameAndId(companyDto.getCorpName(), id);
        if (nameAndIdNot){
            return new ApiResponse("this name is taken already", false);
        }
        Optional<Company> byId = companyRepository.findById(id);
        if (byId.isEmpty()){
            return new ApiResponse("the comapny taken by id is not aviable here", false);
        }
        Company company = mapper.companyDtoToCompanyEntity(companyDto);
        companyDto.setId(id);
//        company.setAddress(companyDto.getAddress());
//        company.setCorpName(companyDto.getCorpName());
//        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("the company saved successfully", true);
    }
}
