package com.ihoruch.mapper;

import com.ihoruch.dto.CarDto;
import com.ihoruch.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CarMapper {

    Car map(final CarDto carDto);

}
