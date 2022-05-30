package com.ihoruch.mapper;

import com.ihoruch.dto.UserRegistrationDto;
import com.ihoruch.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "address.street", target = "address.street")

    })
    User map(final UserRegistrationDto registrationDto);

}
