package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TemplateEngineTest {

    private TemplateEngine templateEngine;
    private Template template;
    private Client client;


    //The system replaces variable placeholders like #{subject} from a template with values provided at runtime.
    @ParameterizedTest()
    @ValueSource(strings = {"subject", "", "test"})
    void generateMessage_shouldReplacePlaceholderWhenReceivesValue(String subject) {
        template = new Template();
        templateEngine = new TemplateEngine();
        client = new Client();
        client.setAddresses("address@email.com");

        String actualMessage = templateEngine.generateMessage(template, client);

        Assertions.assertEquals(subject, actualMessage);
    }
}
