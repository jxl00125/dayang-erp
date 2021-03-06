package com.dayanghome.dayangerp.mapper;

import com.dayanghome.dayangerp.form.AppointmentQuery;
import com.dayanghome.dayangerp.form.CustomerQuery;
import com.dayanghome.dayangerp.vo.Appointment;
import com.dayanghome.dayangerp.vo.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

@Mapper
public interface CustomerMapper {
    @Insert({"INSERT INTO customer(id, wxName, chineseName, mobile, gender, " +
            "provinceId, provinceName, cityId, cityName, createTime)" +
            "VALUES(null, #{wxName}, #{chineseName}, #{mobile}, #{gender}, " +
            "#{provinceId}, #{provinceName}, #{cityId}, #{cityName}, #{createTime})"})
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insertCustomer(Customer customer);

    @Select({"<script>" +
            "SELECT * FROM customer WHERE 1 = 1" +
            "<if test=\"chineseName!=null and chineseName != ''\">AND chineseName like '%${chineseName}%' </if>" +
            "<if test=\"mobile!=null and mobile != ''\">AND contactMobile = ${mobile} </if>" +
            "order by id desc " +
            "limit #{offset}, #{pageSize}" +
            "</script>"})
    @ResultType(Customer.class)
    List<Customer> searchCustomers(CustomerQuery query);

    @Select({"<script>" +
            "SELECT COUNT(1) FROM customer WHERE 1 = 1" +
            "<if test=\"chineseName!=null and chineseName != ''\">AND chineseName like '%${chineseName}%' </if>" +
            "<if test=\"mobile!=null and mobile != ''\">AND contactMobile = ${mobile} </if>" +
            "</script>"})
    int countCustomer(CustomerQuery query);

    @Select("SELECT * FROM customer WHERE mobile = #{mobile}")
    @ResultType(value = Customer.class)
    List<Customer> findByMobile(String mobile);

    @Select({"<script>",
            "SELECT * FROM customer WHERE id in ",
            "<foreach collection='customerIds' item='o' separator = ', ' open =\"(\" close=\")\" >#{o}</foreach>",
            "</script>"})
    @ResultType(value =  Customer.class)
    List<Customer> getCustomerInfoByIds(@Param("customerIds") Set<Integer> customerIds);
}
