package com.ihoruch.facade;

import com.ihoruch.constants.SpecificationConstants;
import com.ihoruch.dto.AddressDto;
import com.ihoruch.model.Address;
import com.ihoruch.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
@AllArgsConstructor
public class AddressFacade {

    private AddressService addressService;

    public Address getOrCreate(final AddressDto addressDto) {
        final Address address = addressService.filterBy(
                Map.of(
                        SpecificationConstants.Address.STREET, addressDto.getStreet()
                )
        );
        return Objects.isNull(address) ? addressService.create(addressDto) : address;
    }

}
