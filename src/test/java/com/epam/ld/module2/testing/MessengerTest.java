package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.exception.BusinessException;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessengerTest {

    private Messenger messenger;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        MailServer mailServer = new MailServer();
        TemplateEngine templateEngine = new TemplateEngine();
        messenger = new Messenger(mailServer, templateEngine);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void subjectShouldBeSet() throws BusinessException {
        Template template = new Template();
        String expectedSubject = "My subject";
        provideInput(expectedSubject);

        messenger.sendMessage(new Client(), template);

        assertEquals("Subject: #{" + expectedSubject +"}\nBody: #{body}", getOutput());
    }

}