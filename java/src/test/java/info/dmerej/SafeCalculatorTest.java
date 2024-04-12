package info.dmerej;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class SafeCalculatorTest {
  @Test
  void should_not_throw_when_authorized() {
    Authorizer authorizer = new FakeAuthorizer();
    SafeCalculator calculator = new SafeCalculator(authorizer);
    calculator.add(3, 4);
  }

  @Test
  void should_not_throw_when_authorized_mock() {
    Authorizer mockAuthorizer = mock(Authorizer.class);
    when(mockAuthorizer.authorize()).thenReturn(true);
    SafeCalculator calculator = new SafeCalculator(mockAuthorizer);
    calculator.add(3, 4);
  }
}
