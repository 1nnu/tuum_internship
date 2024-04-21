package ee.viinamae.tuum.controller;

import com.google.gson.Gson;
import ee.viinamae.tuum.exceptions.InvalidCurrencyException;
import ee.viinamae.tuum.model.AccountBody;
import ee.viinamae.tuum.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/accounts")
public class RestController {

  public RestController(AccountService accountService) {
    this.accountService = accountService;
  }

  private final AccountService accountService;

  @PostMapping
  public String createAccount(@RequestBody Object requestBody) {
    Gson gson = new Gson();
    String jsonBody = gson.toJson(requestBody);
    AccountBody body = gson.fromJson(jsonBody, AccountBody.class);
    try {
      return accountService.addAccount(body);
    } catch (InvalidCurrencyException e) {
      return e.getMessage();
    }
  }
}
