package com.company.company.controller;
import com.company.company.entity.Address;
import com.company.company.entity.dto.AddressDto;
import com.company.company.entity.dto.ApiResponse;
import com.company.company.service.AddressService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/address")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /**
     * here we can get list of address
     * @return address
     */

    @GetMapping("/all")
    public ResponseEntity<?> getAllAddresses() {
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddresses());
    }

    /**
     * we can create address here
     * @param addressDto
     * @return ApiResponse
     */

    @PostMapping
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressDto addressDto) {
        ApiResponse addingAddress = addressService.saveAddress(addressDto);
        return ResponseEntity.status(addingAddress.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(addingAddress);

    }
    /**
     * we can get any address by id
     * @param id
     * @return address
     */

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return new ResponseEntity<Address>(addressService.getAddress(id), HttpStatus.OK);
    }

    /**
     * we can delete any address by id
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return addressService.deleteAddress(id);
    }

    /**
     * we can update any address by id and save it
     * @param id
     * @param addressDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ApiResponse updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto){
        return addressService.editAddress(id, addressDto);
    }

}

