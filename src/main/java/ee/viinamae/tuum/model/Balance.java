package ee.viinamae.tuum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Balance {
    private Long accountId;
    private Integer currencyId;
    private Double balance;
}
