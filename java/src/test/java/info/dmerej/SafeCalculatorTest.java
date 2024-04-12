package info.dmerej;

import org.junit.jupiter.api.Test;

public class SafeCalculatorTest {
  @Test
  void should_not_throw_when_authorized() {
    Authorizer authorizer = new FakeAuthorizer();
    SafeCalculator calculator = new SafeCalculator(authorizer);
    calculator.add(3, 4);
  }
}
