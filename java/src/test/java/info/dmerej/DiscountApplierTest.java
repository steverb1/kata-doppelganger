package info.dmerej;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DiscountApplierTest {
  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v1() {
    FakeNotifier notifier = new FakeNotifier();
    DiscountApplier discountApplier = new DiscountApplier(notifier);
    List<User> users = create3Users();
    discountApplier.applyV1(0, users);

    assertTrue(notifier.verifyExpectedNumberOfCalls(3));
  }

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v2() {
    FakeNotifier notifier = new FakeNotifier();
    DiscountApplier discountApplier = new DiscountApplier(notifier);
    List<User> users = create3Users();
    discountApplier.applyV2(0, users);

    for (User user : users) {
      assertTrue(notifier.verifyUserCalled(user));
    }
  }

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v1_mock() {
    Notifier mockNotifier = mock(Notifier.class);
    DiscountApplier discountApplier = new DiscountApplier(mockNotifier);
    List<User> users = create3Users();
    discountApplier.applyV1(0, users);

    verify(mockNotifier, times(3)).notify(any(User.class), anyString());
  }

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v2_mock() {
    Notifier mockNotifier = mock(Notifier.class);
    DiscountApplier discountApplier = new DiscountApplier(mockNotifier);
    List<User> users = create3Users();
    discountApplier.applyV2(0, users);

    verify(mockNotifier, times(1)).notify(users.get(0), "You've got a new discount of 0%");
    verify(mockNotifier, times(1)).notify(users.get(1), "You've got a new discount of 0%");
    verify(mockNotifier, times(1)).notify(users.get(2), "You've got a new discount of 0%");
  }

  private List<User> create3Users() {
    List<User> users = new ArrayList<>();
    User user1 = new User("name1", "email1");
    User user2 = new User("name2", "email2");
    User user3 = new User("name3", "email3");
    users.add(user1);
    users.add(user2);
    users.add(user3);
    return users;
  }
}
