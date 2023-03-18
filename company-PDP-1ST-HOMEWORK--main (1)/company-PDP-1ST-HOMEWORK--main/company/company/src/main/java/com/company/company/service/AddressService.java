package com.company.company.service;
import com.company.company.entity.Address;
import com.company.company.entity.mappers.AddressMapper;
import com.company.company.repository.AddressRepository;
import com.company.company.entity.dto.AddressDto;
import com.company.company.entity.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressMapper mapper;
    private final AddressRepository addressRepository;
    /**
     * here we can get list of address
     * @return address
     */
    public List<AddressDto> getAddresses(){
        return addressRepository.findAll(Sort.by("id"))
                .stream()
                .map(mapper::addressToAddressDto)
                .collect(Collectors.toList());
    }

    /**
     * we can create address here
     * @param addressDto
     * @return ApiResponse
     */
    public ApiResponse saveAddress(AddressDto addressDto){
        boolean save = addressRepository.existsByHomeNumber(addressDto.getHomeNumber());
        if (save){
            return new ApiResponse("your home number is existent already!!!", false);
        }
        Address address = mapper.addressDtoToAddress(addressDto);
        addressRepository.save(address);
        return new ApiResponse("your address saved!", true);
    }
    /**
     * we can get any address by id
     * @param id
     * @return address
     */
    public Address getAddress(Long id){
        Optional<Address> byId = addressRepository.findById(id);
        if (byId.isPresent()){
            return addressRepository.getReferenceById(id);
        }
        return null;
    }
    /**
     * we can delete any address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteAddress(Long id) {
        addressRepository.deleteById(id);
 return new ApiResponse("data removed", true);
}
    /**
     * we can update any address by id and save it
     * @param id
     * @param addressDto
     * @return ApiResponse
     */
    public ApiResponse editAddress(Long id, AddressDto addressDto){
        boolean numberAndIdNot = addressRepository.existsByHomeNumberAndId(id, addressDto.getHomeNumber());
        if (numberAndIdNot){
            return new ApiResponse("this number is already registered", false);
        }
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()){
            return new ApiResponse("this number is not existent", false);
        }
        Address address = mapper.addressDtoToAddress(addressDto);
        addressDto.setId(id);
//        Address address = new Address();
//        address.setHomeNumber(addressDto.getHomeNumber());
//        address.setStreet(addressDto.getStreet());
        addressRepository.save(address);
        return new ApiResponse("the data updated ", true);
    }
    }
