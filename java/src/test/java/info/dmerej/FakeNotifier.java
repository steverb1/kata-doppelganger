package info.dmerej;

import java.util.ArrayList;
import java.util.List;

class FakeNotifier implements Notifier {
    int numberOfCalls;
    List<User> usersNotified = new ArrayList<>();

    @Override
    public void notify(User user, String message) {
        numberOfCalls++;
        usersNotified.add(user);
    }

    public boolean verifyExpectedNumberOfCalls(int expected) {
        return expected == numberOfCalls;
    }

    public boolean verifyUserCalled(User userCalled) {
        for (User user : usersNotified) {
            if (user.name().equals(userCalled.name())) {
                return true;
            }
        }

        return false;
    }
}
