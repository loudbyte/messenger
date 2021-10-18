package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.BusinessException;

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
        if (scanner.hasNext()) {
            subject = scanner.nextLine();
            if (subject == null) {
                throw new BusinessException("Subject cannot be null");
            }
            return messageTemplate.replace("subject", subject);
        } else {
            throw new BusinessException("Subject should be specified");
        }
    }
}
