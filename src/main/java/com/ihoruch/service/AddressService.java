package com.ihoruch.service;

import com.ihoruch.dto.AddressDto;
import com.ihoruch.model.Address;

import java.util.Map;

public interface AddressService {

    Address create(final AddressDto addressDto);

    Address filterBy(final Map<String, Object> attributes);


}
