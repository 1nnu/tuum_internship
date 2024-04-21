package ee.viinamae.tuum.service;

import com.google.gson.Gson;
import ee.viinamae.tuum.exceptions.InvalidCurrencyException;
import ee.viinamae.tuum.mapper.AccountMapper;
import ee.viinamae.tuum.mapper.BalanceMapper;
import ee.viinamae.tuum.mapper.CurrencyMapper;
import ee.viinamae.tuum.mapper.CustomerMapper;
import ee.viinamae.tuum.model.Account;
import ee.viinamae.tuum.model.AccountBody;
import ee.viinamae.tuum.model.AccountResponse;
import ee.viinamae.tuum.model.Balance;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

  @Value("${currencies.allowed-currencies}")
  private final HashSet<String> ALLOWED_CURRENCIES = new HashSet<>();

  private final SqlSessionFactory sqlSessionFactory;

  public AccountService(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  /**
   * Function for adding an acc
   *
   * @return json a
   */
  public String addAccount(AccountBody accountBody) throws InvalidCurrencyException {
    ArrayList<String> currencies = accountBody.getCurrencies();
    Long customerId = accountBody.getCustomerId();
    String country = accountBody.getCountry();
    for (String currency : currencies) {
      if (!ALLOWED_CURRENCIES.contains(currency)) {
        throw new InvalidCurrencyException("Given currency '" + currency + "' is not allowed!");
      }
    }
    try (SqlSession session = sqlSessionFactory.openSession()) {
      CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
      if (customerMapper.checkIfCustomerExists(customerId)) {
        customerMapper.insertCustomer(customerId, country);
      }
      AccountMapper accountMapper = session.getMapper(AccountMapper.class);
      Long accountId = accountMapper.insertAccount(customerId);
      for (String currency : currencies) {
        BalanceMapper balanceMapper = session.getMapper(BalanceMapper.class);
        CurrencyMapper currencyMapper = session.getMapper(CurrencyMapper.class);
        Integer currencyId = currencyMapper.getCurrencyId(currency);
        balanceMapper.insertBalance(accountId, currencyId, 0.0);
      }
      List<Balance> balanceList = getBalancesByAccountId(accountId, session);
      Gson gson = new Gson();
      AccountResponse accountResponse = new AccountResponse(accountId, customerId, balanceList);
      return gson.toJson(accountResponse);
    }
  }

  private List<Balance> getBalancesByAccountId(Long accountId, SqlSession session) {
    BalanceMapper balanceMapper = session.getMapper(BalanceMapper.class);
    return balanceMapper.getBalances(accountId);
  }

  public String getAccount(Long accountId) {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      AccountMapper accountMapper = session.getMapper(AccountMapper.class);
      Account account = accountMapper.getAccountById(accountId);
      List<Balance> balanceList = getBalancesByAccountId(accountId, session);
      Gson gson = new Gson();
      AccountResponse accountResponse =
          new AccountResponse(accountId, account.getCustomerId(), balanceList);
      return gson.toJson((accountResponse));
    }
  }
}
