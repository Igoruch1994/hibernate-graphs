package com.cnhindustrial.dtc.repository.impl;

import com.cnhindustrial.dtc.constants.Constants;
import com.cnhindustrial.dtc.exception.ConflictException;
import com.cnhindustrial.dtc.exception.EntityNotFoundException;
import com.cnhindustrial.dtc.model.data.AbstractVersionEntity;
import com.cnhindustrial.dtc.repository.CommonRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Repository
public class CommonRepositoryImpl<K> implements CommonRepository<K> {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("all")
    @Override
    public K fetchGraph(Long id, String graphName, Class<K> clazz) {
        EntityGraph<?> graph = entityManager.getEntityGraph(graphName);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", graph);
        K entity = entityManager.find(clazz, id, properties);
        if (isNull(entity)) {
            throw new EntityNotFoundException(clazz.getSimpleName() + " with id " + id + " is not found");
        }
        return entity;
    }

    @Override
    public List<K> fetchGraph(Specification<K> specification, String graphName, Class<K> clazz) {
        EntityGraph<?> graph = entityManager.getEntityGraph(graphName);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<K> query = builder.createQuery(clazz);
        Root<K> root = query.from(clazz);
        Predicate predicate = specification.toPredicate(root, query, builder);
        query.where(predicate);
        return entityManager.createQuery(query.select(root))
                .setHint("javax.persistence.fetchgraph", graph)
                .getResultList();
    }

    @Override
    public List<K> fetchGraph(String graphName, Class<K> clazz) {
        EntityGraph<?> graph = entityManager.getEntityGraph(graphName);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<K> query = builder.createQuery(clazz);
        Root<K> root = query.from(clazz);
        return entityManager.createQuery(query.select(root))
                .setHint("javax.persistence.fetchgraph", graph)
                .getResultList();
    }

    @Override
    public <K extends AbstractVersionEntity> List<K> patchAll(Map<Long, Map<String, Object>> attributes, Class<K> clazz) {
        return attributes.entrySet()
                .stream()
                .map(entry -> patch(entry.getKey(), entry.getValue(), clazz))
                .collect(Collectors.toList());
    }

    @Override
    public <K extends AbstractVersionEntity> K patch(Long id, Map<String, Object> attributes, Class<K> clazz) {
        if (!Objects.equals(attributes.get(Constants.CURRENT_VERSION), attributes.get(Constants.VERSION))) {
            throw new ConflictException(
                String.format(
                    "The %s with id %s was modified. The current version is %s",
                    clazz,
                    id,
                    attributes.get(Constants.CURRENT_VERSION)
                )
            );
        }
        K entity = entityManager.find(clazz, id);
        attributes.forEach((name, value) -> {
            Field field = ReflectionUtils.findField(clazz, name);
            if (nonNull(field) && !Objects.equals(Constants.CURRENT_VERSION, name) && !Objects.equals(Constants.ID, name)) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, entity, value);
            }
        });
        return entityManager.merge(entity);
    }
}
