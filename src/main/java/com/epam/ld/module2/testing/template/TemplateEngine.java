package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.IOService;
import com.epam.ld.module2.testing.exception.BusinessException;

import java.io.IOException;
import java.util.List;
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
     *
     * @throws BusinessException if subject is not specified
     */
    public String generateMessage(Template template, Client client) throws BusinessException {
        String messageTemplate = template.getSubject() + "\n" + template.getBody();
        Scanner scanner = new Scanner(System.in);
        String subject;
        String body;
            subject = getString(scanner, "Enter the subject: ");
            String messageWithSubject = messageTemplate.replace("subject", subject);
            body = getString(scanner, "Enter the body: ");
            return messageWithSubject.replace("body", body);
    }

    /**
     * Generate message string from file.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessageFromFile(Template template, Client client) {
        IOService ioService = new IOService();
        String messageTemplate = template.getSubject() + "\n" + template.getBody();
        String subject;
        String body;
        List<String> inputFromFile = null;
        try {
            inputFromFile = ioService.getInputFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        subject = inputFromFile.get(0);
        body = inputFromFile.get(1);
        String messageWithSubject = messageTemplate.replace("subject", subject);
        return messageWithSubject.replace("body", body);
    }

    private String getString(Scanner scanner, String prompt) throws BusinessException {
        System.out.print(prompt);
        if (scanner.hasNext()) {
            String string = scanner.nextLine();
            if (string == null || string.length() == 0 ) {
                throw new BusinessException("Message cannot be null");
            }
            return string;
        } else {
            throw new BusinessException("Message should be specified");
        }
    }
}
