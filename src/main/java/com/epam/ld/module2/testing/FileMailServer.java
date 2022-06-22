package com.epam.ld.module2.testing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileMailServer implements MailServer {

    private final String outputFile;

    public FileMailServer(String outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void send(String addresses, String messageContent) {
        try {
            Files.write(new File(outputFile).toPath(), messageContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
