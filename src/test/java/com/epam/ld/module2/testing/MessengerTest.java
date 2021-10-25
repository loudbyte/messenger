package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.exception.BusinessException;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MessengerTest {

    private Messenger messenger;
    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() throws UnsupportedEncodingException {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut, false, StandardCharsets.UTF_8.toString()));
        MailServer mailServer = new MailServer(new IOService());
        TemplateEngine templateEngine = new TemplateEngine();
        messenger = new Messenger(mailServer, templateEngine);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        System.setIn(testIn);
    }

    private String getOutput() {
        try {
            return testOut.toString(StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            fail();
            return null;
        }
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void subjectShouldBeSet() throws BusinessException {
        Template template = new Template();
        String expectedSubject = "My subject\nMy body";
        provideInput(expectedSubject);

        messenger.sendMessage(new Client(), template);

        assertEquals("Enter the subject: Enter the body: Subject: #{My subject}\nBody: #{My body}", getOutput());
    }

}