package com.ihoruch.service.impl;

import com.ihoruch.dto.AddressDto;
import com.ihoruch.mapper.AddressMapper;
import com.ihoruch.model.Address;
import com.ihoruch.repository.AddressRepository;
import com.ihoruch.service.AddressService;
import com.ihoruch.specification.AddressSpecification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    @Override
    public Address create(final AddressDto addressDto) {
        final Address address = addressMapper.map(addressDto);
        return addressRepository.save(address);
    }

    @Override
    public Address filterBy(final Map<String, Object> attributes) {
        return addressRepository.findOne(AddressSpecification.create(attributes)).orElse(null);
    }
}
