package com.class1926.copybigdata.service;

import com.class1926.copybigdata.entity.MapResult;
import com.class1926.copybigdata.repository.JobRepository;
import com.class1926.copybigdata.utile.JackjsonToJedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * service层
 *
 * @author zell
 */
@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Object[][] getCity(String type) {

        String redisKey = "getCity" + type;
        Object[][] citys = null;
        String tmp = JackjsonToJedisUtil.get(redisKey);
        if (tmp == null || "null".equals(tmp) || tmp.length() == 0) {
            //redis中没有值，则从数据库中查找
            System.out.println(redisKey + "\t从数据库中查找");

            List<Object[]> groupByCity = jobRepository.findGroupByCity(type);
            citys = new Object[5][groupByCity.size()];
            for (int i = 0; i < groupByCity.size(); i++) {
                citys[0][i] = groupByCity.get(i)[0];
                citys[1][i] = groupByCity.get(i)[1];
                citys[2][i] = groupByCity.get(i)[2];
                citys[3][i] = groupByCity.get(i)[3];
                citys[4][i] = groupByCity.get(i)[4];
            }

            JackjsonToJedisUtil.setObject(citys, redisKey);
        } else {
            System.out.println(redisKey + "\t从缓存中查找");
            //从redis取值
            citys = JackjsonToJedisUtil.getObjectArrayFastJson(redisKey);
        }
        return citys;
    }


    public Object[][] getProvince(String type) {

        String redisKey = "getProvince" + type;
        Object[][] provinces = null;
        String tmp = JackjsonToJedisUtil.get(redisKey);
        if (tmp == null || "null".equals(tmp) || tmp.length() == 0) {
            //redis中没有值，则从数据库中查找
            System.out.println(redisKey + "\t从数据库中查找");

            List<Object[]> groupByProvince = jobRepository.findGroupByProvince(type);
            provinces = new Object[5][groupByProvince.size()];
            for (int i = 0; i < groupByProvince.size(); i++) {
                provinces[0][i] = groupByProvince.get(i)[0];
                provinces[1][i] = groupByProvince.get(i)[1];
                provinces[2][i] = groupByProvince.get(i)[2];
                provinces[3][i] = groupByProvince.get(i)[3];
                provinces[4][i] = groupByProvince.get(i)[4];
            }

            JackjsonToJedisUtil.setObject(provinces, redisKey);
        } else {
            System.out.println(redisKey + "\t从缓存中查找");
            //从redis取值
            provinces = JackjsonToJedisUtil.getObjectArrayFastJson(redisKey);
        }
        return provinces;
    }

    public List<MapResult> getMapByCity(String type) {
        List<MapResult> mapResult = new ArrayList<>();

        String redisKey = "getMapByCity" + type;
        String tmp = JackjsonToJedisUtil.get(redisKey);
        if (tmp == null || "null".equals(tmp) || tmp.length() == 0) {
            //redis中没有值，则从数据库中查找
            System.out.println(redisKey + "\t从数据库中查找");

            List<Object[]> groupByCity = jobRepository.findGroupByCity(type);
            for (Object[] objects : groupByCity) {
                mapResult.add(MapResult.builder().name(objects[0]).value(objects[2]).build());
            }

            JackjsonToJedisUtil.setObject(mapResult, redisKey);
        } else {
            System.out.println(redisKey + "\t从缓存中查找");
            //从redis取值
            mapResult = JackjsonToJedisUtil.getListMapResult(redisKey);
        }
        return mapResult;
    }

    public List<MapResult> getAvgSalaryByCity(String type) {
        List<MapResult> mapResult = new ArrayList<>();

        String redisKey = "getAvgSalaryByCity" + type;
        String tmp = JackjsonToJedisUtil.get(redisKey);
        if (tmp == null || "null".equals(tmp) || tmp.length() == 0) {
            //redis中没有值，则从数据库中查找
            System.out.println(redisKey + "\t从数据库中查找");

            List<Object[]> groupByCity = jobRepository.findGroupByCity(type);
            for (Object[] objects : groupByCity) {
                mapResult.add(MapResult.builder().name(objects[0]).value(objects[1]).build());
            }

            JackjsonToJedisUtil.setObject(mapResult, redisKey);
        } else {
            System.out.println(redisKey + "\t从缓存中查找");
            //从redis取值
            mapResult = JackjsonToJedisUtil.getListMapResult(redisKey);
        }
        return mapResult;
    }

    public List<MapResult> getAvgExperienceByCity(String type) {
        List<MapResult> mapResult = new ArrayList<>();

        String redisKey = "getAvgExperienceByCity" + type;
        String tmp = JackjsonToJedisUtil.get(redisKey);
        if (tmp == null || "null".equals(tmp) || tmp.length() == 0) {
            //redis中没有值，则从数据库中查找
            System.out.println(redisKey + "\t从数据库中查找");

            List<Object[]> groupByCity = jobRepository.findGroupByCity(type);
            for (Object[] objects : groupByCity) {
                mapResult.add(MapResult.builder().name(objects[0]).value(objects[3]).build());
            }

            JackjsonToJedisUtil.setObject(mapResult, redisKey);
        } else {
            System.out.println(redisKey + "\t从缓存中查找");
            //从redis取值
            mapResult = JackjsonToJedisUtil.getListMapResult(redisKey);
        }
        return mapResult;
    }

    public List<MapResult> getMapByProvince(String type) {

        List<MapResult> mapResult = new ArrayList<>();

        String redisKey = "getMapByProvince" + type;
        String tmp = JackjsonToJedisUtil.get(redisKey);
        if (tmp == null || "null".equals(tmp) || tmp.length() == 0) {
            //redis中没有值，则从数据库中查找
            System.out.println(redisKey + "\t从数据库中查找");

            List<Object[]> groupByCity = jobRepository.findGroupByProvince(type);
            for (Object[] objects : groupByCity) {
                mapResult.add(MapResult.builder().name(objects[0]).value(objects[2]).build());
            }

            JackjsonToJedisUtil.setObject(mapResult, redisKey);
        } else {
            System.out.println(redisKey + "\t从缓存中查找");
            //从redis取值
            mapResult = JackjsonToJedisUtil.getListMapResult(redisKey);
        }
        return mapResult;
    }

    public List<MapResult> getAvgSalaryByProvince(String type) {

        List<MapResult> mapResult = new ArrayList<>();


        String redisKey = "getAvgSalaryByProvince" + type;
        String tmp = JackjsonToJedisUtil.get(redisKey);
        if (tmp == null || "null".equals(tmp) || tmp.length() == 0) {
            //redis中没有值，则从数据库中查找
            System.out.println(redisKey + "\t从数据库中查找");

            List<Object[]> groupByCity = jobRepository.findGroupByProvince(type);
            for (Object[] objects : groupByCity) {
                mapResult.add(MapResult.builder().name(objects[0]).value(objects[1]).build());
            }

            JackjsonToJedisUtil.setObject(mapResult, redisKey);
        } else {
            System.out.println(redisKey + "\t从缓存中查找");
            //从redis取值
            mapResult = JackjsonToJedisUtil.getListMapResult(redisKey);
        }
        return mapResult;
    }

    public List<MapResult> getAvgExperienceByProvince(String type) {
        List<MapResult> mapResult = new ArrayList<>();

        String redisKey = "getAvgExperienceByProvince" + type;
        String tmp = JackjsonToJedisUtil.get(redisKey);
        if (tmp == null || "null".equals(tmp) || tmp.length() == 0) {
            //redis中没有值，则从数据库中查找
            System.out.println(redisKey + "\t从数据库中查找");

            List<Object[]> groupByCity = jobRepository.findGroupByProvince(type);
            for (Object[] objects : groupByCity) {
                mapResult.add(MapResult.builder().name(objects[0]).value(objects[3]).build());
            }

            JackjsonToJedisUtil.setObject(mapResult, redisKey);
        } else {
            System.out.println(redisKey + "\t从缓存中查找");
            //从redis取值
            mapResult = JackjsonToJedisUtil.getListMapResult(redisKey);
        }
        return mapResult;
    }

    public List<Object> getCountByEducation(String type) {
        List<Object> all = new ArrayList<>();

        String redisKey = "getCountByEducation" + type;
        String tmp = JackjsonToJedisUtil.get(redisKey);
        if (tmp == null || "null".equals(tmp) || tmp.length() == 0) {
            //redis中没有值，则从数据库中查找
            System.out.println(redisKey + "\t从数据库中查找");

            List<Object[]> count = jobRepository.findCountGroupByEducation(type);

            double item = 0;
            for (Object[] objects : count) {
                item = item + Double.valueOf(objects[1].toString());
            }
            for (int i = 1; i < count.size(); i++) {
                List<Object> data = new ArrayList<>();
                double x = Double.valueOf(count.get(i)[1].toString()) / item * 100;
                MapResult other = MapResult.builder().name("other").value(100 - (x)).build();
                MapResult result = MapResult.builder().name(count.get(i)[0]).value(x).build();
                data.add(other);
                data.add(result);
                all.add(data);
            }

            JackjsonToJedisUtil.setObject2(all, redisKey);
        } else {
            System.out.println(redisKey + "\t从缓存中查找");
            //从redis取值
            all = JackjsonToJedisUtil.getObjectList(redisKey);
        }

        return all;
    }

    public List<Object> getAllJob() {
        List<Object> allJob = new ArrayList<>();

        String redisKey = "getAllJob";
        String tmp = JackjsonToJedisUtil.get(redisKey);
        if (tmp == null || "null".equals(tmp) || tmp.length() == 0) {
            //redis中没有值，则从数据库中查找
            System.out.println(redisKey + "\t从数据库中查找");

            List<Object> city = new ArrayList<>();
            List<Object> uiSalary = new ArrayList<>();
            List<Object> javaSalary = new ArrayList<>();
            List<Object> bigdataSalary = new ArrayList<>();
            List<Object> cloudSalary = new ArrayList<>();
            city.add("city");
            uiSalary.add("UI");
            javaSalary.add("JAVA");
            bigdataSalary.add("大数据");
            cloudSalary.add("云计算");
            List<Object[]> avgs = jobRepository.findAvg();
            for (Object[] avg : avgs) {
                if ("北京".equals(avg[0]) || "上海".equals(avg[0]) || "广州".equals(avg[0]) || "深圳".equals(avg[0]) || "杭州".equals(avg[0]) || "南京".equals(avg[0]) || "成都".equals(avg[0]) || "苏州".equals(avg[0]) || "武汉".equals(avg[0]) || "合肥".equals(avg[0])) {
                    city.add(avg[0]);
                    uiSalary.add(avg[1]);
                    javaSalary.add(avg[2]);
                    bigdataSalary.add(avg[3]);
                    cloudSalary.add(avg[4]);
                }
            }
            allJob.add(city);
            allJob.add(uiSalary);
            allJob.add(javaSalary);
            allJob.add(bigdataSalary);
            allJob.add(cloudSalary);


            JackjsonToJedisUtil.setObject2(allJob, redisKey);
        } else {
            System.out.println(redisKey + "\t从缓存中查找");
            //从redis取值
            allJob = JackjsonToJedisUtil.getObjectObject(redisKey);
        }

        return allJob;

    }
}
