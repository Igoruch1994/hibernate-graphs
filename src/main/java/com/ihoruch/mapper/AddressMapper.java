package com.ihoruch.mapper;

import com.ihoruch.dto.AddressDto;
import com.ihoruch.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AddressMapper {

    Address map(final AddressDto addressDto);

}
