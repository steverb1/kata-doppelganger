package info.dmerej;

import java.util.List;

public class DiscountApplier {
  private final Notifier notifier;

  public DiscountApplier(Notifier notifier) {
    this.notifier = notifier;
  }

  void applyV1(int discount, List<User> users) {
    for (int i = 0; i < users.size(); i++) { // <- Bug, should be `i = 0`
      String message = String.format("You've got a new discount of %d%%", discount);
      User user = users.get(i);
      notifier.notify(user, message);
    }
  }

  void applyV2(int discount, List<User> users) {
    for (int i = 0; i < users.size(); i++) {
      String message = String.format("You've got a new discount of %d%%", discount);
      User user = users.get(i); // <- Bug, should be .get(i), not .get(0);
      notifier.notify(user, message);
    }
  }
}
