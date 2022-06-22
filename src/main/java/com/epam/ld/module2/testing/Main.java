package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

public class Main {

    public static void main(String[] args) {
        MailServer mailServer;
        if (args.length == 2) {
            String inputFile = args[0];
            String outputFile = args[1];
            mailServer = new FileMailServer(inputFile, outputFile);
        } else {
            mailServer = new ConsoleMailServer();

        }
        Messenger messenger = new Messenger(mailServer, new TemplateEngine());
        Template template = new Template("Subject: #{subject} " + "Body: #{body}");
        messenger.sendMessage(new Client(), template);
    }
}
