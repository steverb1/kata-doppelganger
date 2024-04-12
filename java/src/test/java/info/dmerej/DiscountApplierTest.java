package info.dmerej;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiscountApplierTest {
  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v1() {
    FakeNotifier notifier = new FakeNotifier();
    DiscountApplier discountApplier = new DiscountApplier(notifier);
    List<User> users = new ArrayList<>();
    User user1 = new User("name1", "email1");
    User user2 = new User("name2", "email2");
    User user3 = new User("name3", "email3");
    users.add(user1);
    users.add(user2);
    users.add(user3);
    discountApplier.applyV1(0, users);

    assertTrue(notifier.verifyExpectedNumberOfCalls(3));
  }

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v2() {
    FakeNotifier notifier = new FakeNotifier();
    DiscountApplier discountApplier = new DiscountApplier(notifier);
    List<User> users = new ArrayList<>();
    User user1 = new User("name1", "email1");
    User user2 = new User("name2", "email2");
    User user3 = new User("name3", "email3");
    users.add(user1);
    users.add(user2);
    users.add(user3);
    discountApplier.applyV2(0, users);

    for (User user : users) {
      assertTrue(notifier.verifyUserCalled(user));
    }
  }
}
