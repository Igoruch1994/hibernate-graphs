package com.ihoruch.repository;

import com.ihoruch.model.Car;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long>,
        CommonRepository<Car>,
        JpaSpecificationExecutor<Car> {

}
