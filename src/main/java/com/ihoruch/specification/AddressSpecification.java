package com.ihoruch.specification;

import com.ihoruch.constants.SpecificationConstants;
import com.ihoruch.model.Address;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public class AddressSpecification implements Specification<Address> {

    private final Map<String, Object> attributes;

    private AddressSpecification(final Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public static AddressSpecification create(final Map<String, Object> attributes) {
        return new AddressSpecification(attributes);
    }

    @Override
    public Predicate toPredicate(@NonNull final Root<Address> root,
                                 @NonNull final CriteriaQuery<?> query,
                                 @NonNull final CriteriaBuilder criteriaBuilder) {
        return new PredicateBuilder(criteriaBuilder, root)
                .equals(SpecificationConstants.Address.STREET, attributes.get(SpecificationConstants.Address.STREET))
                .build();
    }

}
