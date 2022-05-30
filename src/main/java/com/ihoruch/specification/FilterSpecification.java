package com.ihoruch.specification;

import com.ihoruch.config.TriFunction;
import com.ihoruch.dto.FilterCriteria;
import com.ihoruch.enums.FilterOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class FilterSpecification<T> implements Specification<T> {

    private static final String PERSENTAGE_SYMBOL = "%";

    private final Map<FilterOperation, TriFunction<Root, CriteriaBuilder, FilterCriteria, Predicate>> OPERATION_CONTAINER = new HashMap<>() {{
        put(FilterOperation.IN, (root, query, criteria) -> in(root, query, criteria));
        put(FilterOperation.LIKE, (root, query, criteria) -> like(root, query, criteria));
        put(FilterOperation.EQUALS, (root, query, criteria) -> equal(root, query, criteria));
        put(FilterOperation.LESS_THAN, (root, query, criteria) -> lessThan(root, query, criteria));
        put(FilterOperation.GREATER_THAN, (root, query, criteria) -> greaterThan(root, query, criteria));
    }};

    private List<FilterCriteria> criterias;

    public static <T> Specification<T> create(final List<FilterCriteria> filters) {
        return new FilterSpecification<T>(filters);
    }

    @Override
    public Predicate toPredicate(@NonNull final Root<T> root,
                                 @NonNull final CriteriaQuery<?> criteriaQuery,
                                 @NonNull final CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = criterias.stream()
                .map(criteria -> OPERATION_CONTAINER
                        .get(criteria.getOperation())
                        .apply(root, criteriaBuilder, criteria)
                )
                .collect(Collectors.toList());
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    public Predicate equal(final Root<T> root,
                           final CriteriaBuilder builder,
                           final FilterCriteria criteria) {
        if (root.get(criteria.getKey()).getJavaType().getSuperclass() == Enum.class) {
            return builder.equal(root.get(criteria.getKey()).as(String.class), criteria.getValue());
        } else {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
    }

    public Predicate like(final Root<T> root,
                          final CriteriaBuilder builder,
                          final FilterCriteria criteria) {
        final String likeValue = PERSENTAGE_SYMBOL + criteria.getValue() + PERSENTAGE_SYMBOL;
        if (root.get(criteria.getKey()).getJavaType().getSuperclass() == Enum.class) {
            return builder.like(root.get(criteria.getKey()).as(String.class), likeValue);
        } else {
            return builder.like(root.get(criteria.getKey()), likeValue);
        }
    }

    public Predicate in(final Root<T> root,
                        final CriteriaBuilder builder,
                        final FilterCriteria criteria) {
        return root.get(criteria.getKey()).in((List<Objects>) criteria.getValue());
    }

    public Predicate greaterThan(final Root<T> root,
                                 final CriteriaBuilder builder,
                                 final FilterCriteria criteria) {
        final Class<?> javaType = root.get(criteria.getKey()).getJavaType();
        if (javaType == LocalDate.class) {
            return builder.greaterThan(root.get(criteria.getKey()), LocalDate.parse(String.valueOf(criteria.getValue())));
        } else
            return builder.greaterThan(root.get(criteria.getKey()), String.valueOf(criteria.getValue()));
    }

    public Predicate lessThan(final Root<T> root,
                              final CriteriaBuilder builder,
                              final FilterCriteria criteria) {
        final Class<?> javaType = root.get(criteria.getKey()).getJavaType();
        if (javaType == LocalDate.class) {
            return builder.lessThan(root.get(criteria.getKey()), LocalDate.parse(String.valueOf(criteria.getValue())));
        } else
            return builder.lessThan(root.get(criteria.getKey()), String.valueOf(criteria.getValue()));
    }

}
