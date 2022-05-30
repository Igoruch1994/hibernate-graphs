package com.ihoruch.repository;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CommonRepository<K> {

    K fetchGraph(Long id, String graphName, Class<K> clazz);

    List<K> fetchGraph(Specification<K> specification, String graphName, Class<K> clazz);

    List<K> fetchGraph(String graphName, Class<K> clazz);

}
