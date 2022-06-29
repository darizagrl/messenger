package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        MailServer mailServer;
        Template template;
        List<String> values = new ArrayList<>();
        if (args.length == 2) {
            String inputFile = args[0];
            String outputFile = args[1];
            mailServer = new FileMailServer(outputFile);
            List<String> content = Files.readAllLines(new File(inputFile).toPath());

            content.stream()
                    .filter(c -> c.matches("#(\\{.+\\})"))
                    .forEach(values::add);
            template = new Template(content.toString());

        } else {
            mailServer = new ConsoleMailServer();
            template = new Template("Subject: #{subject} " + "Body: #{body}");
        }
        try (Scanner scanner = new Scanner(System.in)) {
            values.add(getInput(scanner, "Enter subject: "));
            values.add(getInput(scanner, "Enter body: "));
        }

        Messenger messenger = new Messenger(mailServer, new TemplateEngine());
        messenger.sendMessage(values, new Client(), template);
    }

    private static String getInput(Scanner scanner, String output) throws IllegalArgumentException {
        System.out.print(output);
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        throw new IllegalArgumentException("Input shouldn't be empty");
    }

}
