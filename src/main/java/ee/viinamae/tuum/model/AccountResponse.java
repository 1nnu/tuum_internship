package ee.viinamae.tuum.model;

import java.util.List;
import lombok.Data;

@Data
public class AccountResponse {
  private Long accountId;
  private Long customerId;
  private List<Balance> balances;

  public AccountResponse(Long accountId, Long customerId, List<Balance> balances) {
    this.accountId = accountId;
    this.customerId = customerId;
    this.balances = balances;
  }
}
