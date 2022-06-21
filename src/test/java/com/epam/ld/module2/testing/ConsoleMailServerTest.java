package com.epam.ld.module2.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleMailServerTest {

    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    void send_shouldPrintToConsole() {
        ByteArrayInputStream input = new ByteArrayInputStream("testSubject\ntestBody".getBytes());
        System.setIn(input);
        String message = "Subject: #{subject} " + "Body: #{body}";

        MailServer mailServer = new ConsoleMailServer();
        mailServer.send("mail@email.com", message);

        assertEquals(message, outputStream.toString());
    }
}