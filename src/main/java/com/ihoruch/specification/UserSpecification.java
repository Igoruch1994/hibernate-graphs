package com.ihoruch.specification;

import com.ihoruch.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public class UserSpecification implements Specification<User> {

    private final Map<String, Object> attributes;

    private UserSpecification(final Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public static UserSpecification create(final Map<String, Object> attributes) {
        return new UserSpecification(attributes);
    }

    @Override
    public Predicate toPredicate(@NonNull final Root<User> root,
                                 @NonNull final CriteriaQuery<?> query,
                                 @NonNull final CriteriaBuilder criteriaBuilder) {
        return new PredicateBuilder(criteriaBuilder, root)
                //.equals(SpecificationConstants.User.STREET, attributes.get(SpecificationConstants.User.STREET))
                .build();
    }

}
