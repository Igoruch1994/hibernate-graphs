package com.ihoruch.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

public class PredicateBuilder {

    private final List<Predicate> predicates;
    private final CriteriaBuilder criteriaBuilder;
    private final Root<?> root;

    public PredicateBuilder(final CriteriaBuilder criteriaBuilder,
                            final Root<?> root) {
        this.predicates = new ArrayList<>();
        this.criteriaBuilder = criteriaBuilder;
        this.root = root;
    }

    PredicateBuilder equals(final String key,
                            final Object value) {
        if (nonNull(value)) {
            predicates.add(criteriaBuilder.equal(root.get(key), value));
        }
        return this;
    }

    PredicateBuilder in(final String key,
                        final List<?> values) {
        if (isNotEmpty(values)) {
            predicates.add(root.get(key).in(values));
        }

        return this;
    }

    @SuppressWarnings("all")
    Predicate build() {
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}
