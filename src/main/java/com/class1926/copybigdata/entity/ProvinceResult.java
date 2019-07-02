package com.class1926.copybigdata.entity;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author zell
 */
@Data
@Builder
public class ProvinceResult {

    /**
     * 城市
     */
    private Object[] province;
    /**
     * 平均工资
     */
    private Object[] avgByProvince;
    /**
     * 人数需求(总和)
     */
    private Object[] countByProvince;
    /**
     * 工作经验(平均值)
     */
    private Object[] experience;
    /**
     * 总投入
     */
    private Object[] budget;

}
