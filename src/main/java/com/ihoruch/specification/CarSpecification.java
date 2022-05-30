package com.ihoruch.specification;

import com.ihoruch.constants.SpecificationConstants;
import com.ihoruch.model.Car;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public class CarSpecification implements Specification<Car> {

    private final Map<String, Object> attributes;

    private CarSpecification(final Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public static CarSpecification create(final Map<String, Object> attributes) {
        return new CarSpecification(attributes);
    }

    @Override
    @SuppressWarnings("all")
    public Predicate toPredicate(@NonNull final Root<Car> root,
                                 @NonNull final CriteriaQuery<?> query,
                                 @NonNull final CriteriaBuilder criteriaBuilder) {
        return new PredicateBuilder(criteriaBuilder, root)
                .equals(SpecificationConstants.Car.MODEL, attributes.get(SpecificationConstants.Car.MODEL))
                .equals(SpecificationConstants.Car.COLOUR, attributes.get(SpecificationConstants.Car.COLOUR))
                .in(SpecificationConstants.Car.ID, (List<Long>) attributes.get(SpecificationConstants.Car.IDS_IN))
                .in(SpecificationConstants.Car.MODEL, (List<String>) attributes.get(SpecificationConstants.Car.MODELS_IN))
                .build();
    }

}

