package ee.viinamae.tuum.model;

import java.util.ArrayList;
import lombok.Data;

@Data
public class AccountBody {
  private Long customerId;
  private String country;
  private ArrayList<String> currencies;
}
