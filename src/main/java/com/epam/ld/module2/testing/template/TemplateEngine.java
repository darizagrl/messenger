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
            String subject;
            System.out.print("Enter subject: ");
            if (scanner.hasNext()) {
                subject = scanner.nextLine();
            } else {
                throw new IllegalArgumentException("Subject shouldn't be empty");
            }
            return template.replace("#{subject}", subject);
        }
    }

}
