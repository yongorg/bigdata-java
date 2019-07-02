package com.class1926.copybigdata.controller;

import com.class1926.copybigdata.entity.CityResult;
import com.class1926.copybigdata.entity.MapResult;
import com.class1926.copybigdata.entity.ProvinceResult;
import com.class1926.copybigdata.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Swagger注解：
 * #@Api
 *   1. 使用在类上
 *   2. 表明可供Swagger展示的接口类
 *
 * #@ApiOperation
 *   1. 描述API方法(用在方法上)
 *
 * #@ApiParam
 *   1. 单个参数描述
 *
 * #@ApiModel
 *   1. 用对象接收参数（用在类上）
 * # @ApiModelProperty
 *   1. 用对象接收参数是，描述对象的一个字段（用在属性上面）
 * @author yongz
 */
@Api(value = "JobController相关api")
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * value : 介绍
     * httpMethod : 请求方式
     * protocols ：协议
     * produces ：数据格式
     * notes ：详细介绍
     *
     * @param type
     * @return
     */
    @ApiOperation(value = "查询所有城市相关信息",httpMethod = "GET",protocols = "HTTP",produces = "application/json",notes = "查询数据库中所有城市平均薪资，岗位需求量，平均工作年限")
    @ApiImplicitParam(name = "type",value = "岗位类型（如java）",dataType = "String")
    @GetMapping("city")
    public CityResult getInfoByCity(String type) {
        Object[][] citys = jobService.getCity(type);

        return CityResult.builder().city(citys[0])
                .avgByCity(citys[1])
                .countByCity(citys[2])
                .experience(citys[3])
                .budget(citys[4]).build();
    }

    @ApiOperation(value = "查询所有省份平均薪资，岗位需求量，平均工作年限",notes = "查询数据库中所有省份平均薪资，岗位需求量，平均工作年限")
    @GetMapping("province")
    public ProvinceResult getInfoByProvince(String type) {
        Object[][] provinces = jobService.getProvince(type);

        return ProvinceResult.builder().province(provinces[0])
                .avgByProvince(provinces[1])
                .countByProvince(provinces[2])
                .experience(provinces[3])
                .budget(provinces[4]).build();
    }

    @GetMapping("cityMap")
    @ApiOperation(value = "查询所有城市城市地图数据",notes = "查询数据库中所有城市地图数据")
    public List<MapResult> getMapByCity(String type) {
        return jobService.getMapByCity(type);
    }

    @GetMapping("cityAvgSalaryMap")
    @ApiOperation(value = "查询所有城市城市地图平均工资",notes = "查询数据库中所有城市地图平均工资")
    public List<MapResult> getAvgSalaryByCity(String type) {
        return jobService.getAvgSalaryByCity(type);
    }

    @GetMapping("cityAvgExperienceMap")
    @ApiOperation(value = "查询所有城市城市地图平均工作年限",notes = "查询数据库中所有城市地图平均工作年限")
    public List<MapResult> getAvgExperienceByCity(String type) {
        return jobService.getAvgExperienceByCity(type);
    }

    @GetMapping("provinceMap")
    public List<MapResult> getMapByProvince(String type) {
        return jobService.getMapByProvince(type);
    }

    @GetMapping("provinceAvgSalaryMap")
    public List<MapResult> getAvgSalaryByProvince(String type) {
        return jobService.getAvgSalaryByProvince(type);
    }

    @GetMapping("provinceAvgExperienceMap")
    public List<MapResult> getAvgExperienceByProvince(String type) {
        return jobService.getAvgExperienceByProvince(type);
    }

    @GetMapping("education")
    public List<Object> getCountByEducation(String type){
        return jobService.getCountByEducation(type);
    }

    @GetMapping("all")
    public List<Object> getAllJob(){
        return jobService.getAllJob();
    }

}
