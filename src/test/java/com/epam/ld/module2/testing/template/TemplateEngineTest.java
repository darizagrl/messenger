package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TemplateEngineTest {
    @AfterEach
    void tearDown() {
        System.setIn(System.in);
    }

    private TemplateEngine templateEngine;
    private Template template;
    private Client client;


    /**
     * The system replaces variable placeholders like #{subject} from a template with values provided at runtime.
     */
    @ParameterizedTest()
    @ValueSource(strings = {"subject", "test"})
    void generateMessage_shouldReplacePlaceholderWhenReceivesValue(String subject) {
        ByteArrayInputStream input = new ByteArrayInputStream(subject.getBytes());
        System.setIn(input);
        template = new Template();
        templateEngine = new TemplateEngine();

        String actualSubject = templateEngine.generateMessage(template, client);

        Assertions.assertEquals(subject, actualSubject);
    }

    /**
     * If at least one placeholder value is not provided at runtime – template generator should throw an exception.
     */
    @ParameterizedTest()
    @ValueSource(strings = {" ", ""})
    void generateMessage_shouldThrowExceptionWhenReceivesEmptyValue(String subject) {
        ByteArrayInputStream input = new ByteArrayInputStream(subject.getBytes());
        System.setIn(input);
        template = new Template();
        templateEngine = new TemplateEngine();

        assertThrows(IllegalArgumentException.class,
                () -> templateEngine.generateMessage(template, client), "Subject shouldn't be empty");
    }
}
