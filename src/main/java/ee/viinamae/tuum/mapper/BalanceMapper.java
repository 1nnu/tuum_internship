package ee.viinamae.tuum.mapper;

import ee.viinamae.tuum.model.Balance;
import java.util.List;
import org.apache.ibatis.annotations.*;

public interface BalanceMapper {
  @Insert("INSERT INTO balances (accountId, currencyId, balance) VALUES (#{accountId}, #{currencyId}, #{balance})")
  void insertBalance(@Param("accountId") Long accountId, @Param("currencyId") Integer currencyId, @Param("balance") Double balance);

  @Select("SELECT * FROM balances WHERE accountId=#{accountId}")
  @Results(
      value = {
        @Result(property = "accountId", column = "accountId"),
        @Result(property = "currencyId", column = "currencyId"),
        @Result(property = "balance", column = "balance"),
      })
  List<Balance> getBalances(@Param("accountId") Long accountId);
}
