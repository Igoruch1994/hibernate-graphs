package com.ihoruch.dto;

import com.ihoruch.enums.FilterOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterCriteria {

    private String key;
    private FilterOperation operation;
    private Object value;

}
