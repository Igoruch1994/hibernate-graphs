package com.cnhindustrial.dtc.repository;

import com.cnhindustrial.dtc.model.data.AbstractVersionEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

public interface CommonRepository<K> {

    K fetchGraph(Long id, String graphName, Class<K> clazz);

    List<K> fetchGraph(Specification<K> specification, String graphName, Class<K> clazz);

    List<K> fetchGraph(String graphName, Class<K> clazz);

    <K extends AbstractVersionEntity> List<K> patchAll(Map<Long, Map<String, Object>> attributes, Class<K> clazz);

    <K extends AbstractVersionEntity> K patch(Long id, Map<String, Object> attributes, Class<K> clazz);

}
