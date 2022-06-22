package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.util.List;

/**
 * The type Template engine.
 */
public class TemplateEngine {

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(List<String> values, Template template, Client client) {
        String messageWithSubject = template.getContent().replace("#{subject}", values.get(0));
        return messageWithSubject.replace("#{body}", values.get(1));
    }

}

