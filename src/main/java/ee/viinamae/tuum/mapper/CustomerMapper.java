package ee.viinamae.tuum.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CustomerMapper {
  @Insert("INSERT INTO customers(customerId, country) VALUES (#{customerId}, #{country})")
  void insertCustomer(@Param("customerId") Long customerId, @Param("country") String country);

  @Select("SELECT EXISTS (SELECT 1 FROM customers WHERE customerId = #{customerId})")
  boolean checkIfCustomerExists(@Param("customerId") Long customerId);
}
