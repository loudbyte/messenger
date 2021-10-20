package com.epam.ld.module2.testing;


import com.epam.ld.module2.testing.exception.BusinessException;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

/**
 * The type Messenger.
 */
public class Messenger {
    private MailServer mailServer;
    private TemplateEngine templateEngine;

    /**
     * Instantiates a new Messenger.
     *
     * @param mailServer     the mail server
     * @param templateEngine the template engine
     */
    public Messenger(MailServer mailServer,
                     TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }

    /**
     * Send message.
     *
     * @param client   the client
     * @param template the template
     *
     * @throws BusinessException if message generation failed
     */
    public void sendMessage(Client client, Template template) throws BusinessException {
        String messageContent =
            templateEngine.generateMessage(template, client);
        mailServer.send(client.getAddresses(), messageContent);
    }

    public void sendMessageToFile(Client client, Template template) throws BusinessException {
        String messageContentFromFile =
                templateEngine.generateMessageFromFile(template, client);
        mailServer.sendToFile(client.getAddresses(), messageContentFromFile);
    }
}