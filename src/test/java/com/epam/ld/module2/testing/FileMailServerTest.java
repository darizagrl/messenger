package com.epam.ld.module2.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileMailServerTest {

    @File
    @Test
    void send_shouldPrintToFile() {
        String outputFile = "template.txt";
        String message = "Subject: #{subject} " + "Body: #{body}";
        FileMailServer fileMailServer = new FileMailServer(outputFile);

        assertAll(() -> fileMailServer.send("mail@email.com", message));
    }
}