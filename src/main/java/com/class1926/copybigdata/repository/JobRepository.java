package com.class1926.copybigdata.repository;

import com.class1926.copybigdata.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author zell
 */
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {


    /**
     * 根据城市分组查询各个城市的评价薪资、岗位需求人数、评价工作经验、城市对该岗位总投入
     * @param type:参数表示所要查找的行业，不带参数表示所有行业一起查询
     * @return
     */
    @Query(value = "select address as city,AVG(salary) as avgByCity,SUM(hiring) as countByCity,AVG(experience) as experience,SUM(salary) as budget FROM job_java where if(?1 != '',type =?1,1=1) group by address", nativeQuery = true)
    List<Object[]> findGroupByCity(String type);

    /**
     * 根据省份分组查询各个省份的评价薪资、岗位需求人数、评价工作经验、城市对该岗位总投入
     * @param type:参数表示所要查找的行业，不带参数表示所有行业一起查询
     * @return
     */
    @Query(value = "select province,AVG(salary) as avgByProvince,SUM(hiring) as countByProvince,AVG(experience) as experience,SUM(salary) as budget FROM job_java where if(?1 != '',type =?1,1=1)  group by province", nativeQuery = true)
    List<Object[]> findGroupByProvince(String type);

    /**
     * 查询不同学历所需的人数
     * @param type:参数表示所要查找的行业，不带参数表示所有行业一起查询
     * @return
     */
    @Query(value = "SELECT education,count(hiring) FROM job_java where if(?1 != '',type =?1,1=1) GROUP BY education",nativeQuery = true)
    List<Object[]> findCountGroupByEducation(String type);


    /**
     * 查询各个行业的平均薪资
     *
     * @return
     */
    @Query(value = "SELECT u.address,u.uisalary,j.javasalary,b.bigdatasalary,c.cloudsalary " +
            "FROM(select address,AVG(salary) as uisalary FROM job_java where type='ui' GROUP BY address)u," +
            "(select address,AVG(salary) as javasalary FROM job_java where type='java' GROUP BY address)j," +
            "(select address,AVG(salary) as bigdatasalary FROM job_java where type='bigdata' GROUP BY address)b," +
            "(select address,AVG(salary) as cloudsalary FROM job_java where type='cloud' GROUP BY address)c " +
            "WHERE u.address=j.address and u.address=b.address and u.address=c.address", nativeQuery = true)
    List<Object[]> findAvg();


}

