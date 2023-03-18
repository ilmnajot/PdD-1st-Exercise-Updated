package com.company.company.entity.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    @NotNull(message ="street cannot be blank")
    private String street;

    @NotNull(message = "you should enter your address")
    private int homeNumber;

    private WorkerDto worker;
}
