package com.ihoruch.service;

import com.ihoruch.dto.CarDto;
import com.ihoruch.dto.FilterCriteria;
import com.ihoruch.model.Car;

import java.util.List;
import java.util.Map;

public interface CarService {

    Car create(final CarDto carDto);

    List<Car> doFilterBy(final Map<String, Object> attributes);

    List<Car> filterBy(final List<FilterCriteria> attributes);

}
