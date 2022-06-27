package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class MessengerTest {

    private static final String EMAIL = "mail@email.com";
    private final String expectedMessage = "Subject: testSubject " + "Body: testBody";
    private final List<String> values = Arrays.asList("testSubject", "testBody");
    private final Template template = new Template("Subject: #{subject} " + "Body: #{body}");
    private MailServer mailServer;
    private TemplateEngine templateEngine;
    private Client client;
    private Messenger messenger;


    @BeforeEach
    void setUp() {
        templateEngine = Mockito.mock(TemplateEngine.class);
        client = mock(Client.class);

    }

    /**
     * Use partial mock.
     */
    @Test
    void sendMessage_shouldCallConsoleMailServer_WhenConsoleMode() {
        mailServer = Mockito.mock(ConsoleMailServer.class);
        messenger = new Messenger(mailServer, templateEngine);

        when(client.getAddresses()).thenReturn(EMAIL);
        when(templateEngine.generateMessage(values, template, client)).thenCallRealMethod();
        doCallRealMethod().when(mailServer).send(EMAIL, expectedMessage);

        messenger.sendMessage(values, client, template);

        verify(mailServer).send(EMAIL, expectedMessage);
        Assertions.assertEquals(expectedMessage, templateEngine.generateMessage(values, template, client));
    }


    /**
     * Use partial mock and spy.
     */
    @Test
    void sendMessage_shouldCallFileMailServer_WhenFileMode() {
        mailServer = Mockito.spy(new FileMailServer("output.txt"));
        messenger = new Messenger(mailServer, templateEngine);

        when(client.getAddresses()).thenReturn(EMAIL);
        when(templateEngine.generateMessage(values, template, client)).thenCallRealMethod();
        doCallRealMethod().when(mailServer).send(EMAIL, expectedMessage);

        messenger.sendMessage(values, client, template);

        verify(mailServer).send(EMAIL, expectedMessage);
        Assertions.assertEquals(expectedMessage, templateEngine.generateMessage(values, template, client));
    }
}