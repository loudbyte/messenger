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
     * Send notification to console.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     */
    public void send(String addresses, String messageContent) {
        System.out.print(messageContent);
    }

    /**
     * Send notification to file.
     *
     * @param addresses  the addresses
     * @param messageContentFromFile the message content
     */
    public void sendToFile(String addresses, String messageContentFromFile) {
        try {
            ioService.writeToFile(messageContentFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
