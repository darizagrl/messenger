package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.util.Scanner;

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
    public String generateMessage(Template template, Client client) {
        try (Scanner scanner = new Scanner(System.in)) {
            String subject = this.getInput(scanner, "Enter subject: ");
            String messageWithSubject = template.getContent().replace("#{subject}", subject);
            String body = this.getInput(scanner, "Enter body: ");
            return messageWithSubject.replace("#{body}", body);
        }
    }

    private String getInput(Scanner scanner, String output) throws IllegalArgumentException {
        System.out.print(output);
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        throw new IllegalArgumentException("Input shouldn't be empty");
    }
}

