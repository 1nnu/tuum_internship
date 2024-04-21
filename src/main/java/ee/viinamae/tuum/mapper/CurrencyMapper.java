package ee.viinamae.tuum.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

public interface CurrencyMapper {
    @Select("SELECT * FROM currencies WHERE currencyType = #{currencyType}")
    @Result(property = "currencyId", column = "currencyId")
    Integer getCurrencyId(@Param("currencyType") String currencyType);
}
