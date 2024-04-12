package info.dmerej;

import info.dmerej.mailprovider.SendMailRequest;
import info.dmerej.mailprovider.SendMailResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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

    @Test
    void should_make_a_valid_http_request_mock() {
        HttpClient mockClient = mock(HttpClient.class);
        MailSender mailSender = new MailSender(mockClient);
        User user = new User("name1", "email1");
        mailSender.sendV1(user, "testing");

        SendMailRequest expectedRequest = new SendMailRequest("email1", "New notification", "testing");
        verify(mockClient).post("https://api.mailprovider.com/v3/", expectedRequest);
    }

    @Test
    void should_retry_when_getting_a_503_error_mock() {
        HttpClient mockClient = mock(HttpClient.class);
        MailSender mailSender = new MailSender(mockClient);
        User user = new User("name1", "email1");
        SendMailRequest expectedRequest = new SendMailRequest("email1", "New notification", "testing");
        SendMailResponse response = new SendMailResponse(503, "test");
        when(mockClient.post("https://api.mailprovider.com/v3/", expectedRequest)).thenReturn(response);
        mailSender.sendV2(user, "testing");

        verify(mockClient, times(2)).post(anyString(), any());
    }
}
