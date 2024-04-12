package info.dmerej;

import info.dmerej.mailprovider.SendMailRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MailSenderTest {
    @Test
    void should_make_a_valid_http_request() {
        FakeHttpClient httpClient = new FakeHttpClient();
        MailSender mailSender = new MailSender(httpClient);
        User user = new User("name1", "email1");
        mailSender.sendV1(user, "testing");

        SendMailRequest expectedRequest = new SendMailRequest("email1", "New notification", "testing");
        assertTrue(httpClient.verifyPostedRequest(expectedRequest));
    }

    @Test
    void should_retry_when_getting_a_503_error() {
        FakeHttpClient httpClient = new FakeHttpClient();
        MailSender mailSender = new MailSender(httpClient);
        User user = new User("name1", "email1");
        mailSender.sendV2(user, "testing");

        assertTrue(httpClient.verifyExpectedNumberOfTries(2));
    }
}
