package ee.viinamae.tuum.mapper;

import ee.viinamae.tuum.model.Account;
import org.apache.ibatis.annotations.*;

public interface AccountMapper {
  @Insert("INSERT INTO accounts (customerId) VALUES (#{customerId})")
  @Result(property = "accountId", column = "accountId")
  Long insertAccount(@Param("customerId") long customerId);

  @Select("SELECT * FROM accounts WHERE accountId=#{accountId}")
  @Results(
      value = {
        @Result(property = "accountId", column = "accountId"),
        @Result(property = "customerId", column = "customerId"),
      })
  Account getAccountById(@Param("accountId") long accountId);
}
