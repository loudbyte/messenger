package com.epam.ld.module2.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MailServerTest {

    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() throws UnsupportedEncodingException {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut, false, StandardCharsets.UTF_8.toString()));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        System.setIn(testIn);
    }

    private String getOutputFromConsole() {
        try {
            return testOut.toString("UTF-8");
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
    public void mailServerShouldSendMessageToConsole() {
        final String testString = "Subject: #{subject}\nBody: #{body}";
        provideInput(testString);

        MailServer mailServer = new MailServer(new IOService());
        mailServer.send("", testString);

        assertEquals(testString, getOutputFromConsole());
    }

    @Test
    public void mailServerShouldReadAndSendMessageToFile() {
        String subject = "test subject";
        String body = "test body";
        final String testString = "Subject: #{" + subject + "}\nBody: #{" + body + "}";
        provideInput(testString);
        List<String> testInput = Arrays.asList(subject, body);

        MockIOService mockIOService = new MockIOService(testInput);

        MailServer mailServer = new MailServer(mockIOService);
        mailServer.sendToFile("", testString);

        assertAll(() -> mockIOService.writeToFile(testString));
    }
}
