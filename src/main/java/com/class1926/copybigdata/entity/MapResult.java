package com.class1926.copybigdata.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author zell
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MapResult {

    private Object name;
    private Object value;

}
