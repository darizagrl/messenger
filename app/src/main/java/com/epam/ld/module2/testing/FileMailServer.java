package com.epam.ld.module2.testing;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileMailServer implements MailServer {

    private final String outputFile;

    public FileMailServer(String outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void send(String addresses, String messageContent) {
        try (FileWriter fileWriter = new FileWriter(outputFile);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.print(messageContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
