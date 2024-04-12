package info.dmerej;

class FakeAuthorizer implements Authorizer {
    @Override
    public boolean authorize() {
        return true;
    }
}
