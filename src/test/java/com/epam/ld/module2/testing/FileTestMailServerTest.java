package com.epam.ld.module2.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestOutputExecution.class)
class FileTestMailServerTest {

    @FileTest
    @Test
    void send_shouldPrintToFile() {
        String outputFile = "template.txt";
        String message = "Subject: #{subject} " + "Body: #{body}";

        FileMailServer fileMailServer = new FileMailServer(outputFile);

        assertAll(() -> fileMailServer.send("mail@email.com", message));
    }

    @FileTest
    @Test
    void send_shouldPrintToFile(@TempDir Path tempDir) {
        Path templateFile = tempDir.resolve("template.txt");

        String message = "Subject: #{subject} " + "Body: #{body}";

        FileMailServer fileMailServer = new FileMailServer(templateFile.toString());

        assertAll(() -> fileMailServer.send("mail@email.com", message),
                () -> assertTrue(Files.exists(templateFile), "File should exist"));
    }


}