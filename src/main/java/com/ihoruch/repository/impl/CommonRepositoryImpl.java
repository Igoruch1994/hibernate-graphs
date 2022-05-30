package com.ihoruch.repository.impl;

import com.ihoruch.repository.CommonRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Repository
public class CommonRepositoryImpl<K> implements CommonRepository<K> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public K fetchGraph(final Long id,
                        final String graphName,
                        final Class<K> clazz) {
        final EntityGraph<?> graph = entityManager.getEntityGraph(graphName);
        final Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", graph);
        final K entity = entityManager.find(clazz, id, properties);
        if (isNull(entity)) {
            throw new EntityNotFoundException(clazz.getSimpleName() + " with id " + id + " is not found");
        }
        return entity;
    }

    @Override
    public List<K> fetchGraph(final Specification<K> specification,
                              final String graphName,
                              final Class<K> clazz) {
        final EntityGraph<?> graph = entityManager.getEntityGraph(graphName);
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K> query = builder.createQuery(clazz);
        final Root<K> root = query.from(clazz);
        final Predicate predicate = specification.toPredicate(root, query, builder);
        query.where(predicate);
        return entityManager.createQuery(query.select(root))
                .setHint("javax.persistence.fetchgraph", graph)
                .getResultList();
    }

    @Override
    public List<K> fetchGraph(final String graphName, final Class<K> clazz) {
        final EntityGraph<?> graph = entityManager.getEntityGraph(graphName);
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<K> query = builder.createQuery(clazz);
        final Root<K> root = query.from(clazz);
        return entityManager.createQuery(query.select(root))
                .setHint("javax.persistence.fetchgraph", graph)
                .getResultList();
    }

}
