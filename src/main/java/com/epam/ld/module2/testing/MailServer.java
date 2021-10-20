package com.epam.ld.module2.testing;

import java.io.IOException;

/**
 * Mail server class.
 */
public class MailServer {

    private final IOService ioService;

    public MailServer(IOService ioService) {
        this.ioService = ioService;
    }

    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        System.out.print(messageContent);
    }

    public void sendToFile(String addresses, String messageContentFromFile) {
        try {
            ioService.writeToFile(messageContentFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
