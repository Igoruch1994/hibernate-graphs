package com.ihoruch.facade;

import com.ihoruch.constants.SpecificationConstants;
import com.ihoruch.dto.CarDto;
import com.ihoruch.dto.FilterCriteria;
import com.ihoruch.enums.FilterOperation;
import com.ihoruch.model.Car;
import com.ihoruch.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@AllArgsConstructor
public class CarFacade {

    private final CarService carService;

    public Car getOrCreate(final CarDto carDto) {
        final List<Car> cars = carService.filterBy(
                List.of(
                        new FilterCriteria(SpecificationConstants.Car.MODEL, FilterOperation.EQUALS, carDto.getModel()),
                        new FilterCriteria(SpecificationConstants.Car.COLOUR, FilterOperation.EQUALS, carDto.getColour())
                )
        );

        return CollectionUtils.isEmpty(cars) ? carService.create(carDto) : cars.get(0);

    }


}
