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
        String messageTemplate = template.getSubject() + "\n" + template.getBody();
        Scanner scanner = new Scanner(System.in);
        String subject = scanner.nextLine();
        return messageTemplate.replace("subject", subject);
    }
}
