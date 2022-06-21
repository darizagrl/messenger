package com.epam.ld.module2.testing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileMailServer implements MailServer {

    private final File file;

    public FileMailServer(File file) {
        this.file = file;
    }

    @Override
    public void send(String addresses, String messageContent) {
        try {
            Files.write(file.toPath(), messageContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
