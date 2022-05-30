package com.ihoruch.service.impl;

import com.ihoruch.dto.CarDto;
import com.ihoruch.dto.FilterCriteria;
import com.ihoruch.mapper.CarMapper;
import com.ihoruch.model.Car;
import com.ihoruch.repository.CarRepository;
import com.ihoruch.service.CarService;
import com.ihoruch.specification.CarSpecification;
import com.ihoruch.specification.FilterSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarMapper carMapper;
    private final CarRepository carRepository;

    @Override
    public Car create(final CarDto carDto) {
        final Car car = carMapper.map(carDto);
        car.setCreatedAt(LocalDate.now());
        return carRepository.save(car);
    }

    @Override
    public List<Car> doFilterBy(final Map<String, Object> attributes) {
        return carRepository.findAll(CarSpecification.create(attributes));
    }

    @Override
    public List<Car> filterBy(final List<FilterCriteria> filters) {
        final Specification<Car> specification = FilterSpecification.create(filters);
        return carRepository.findAll(specification);
    }

}
