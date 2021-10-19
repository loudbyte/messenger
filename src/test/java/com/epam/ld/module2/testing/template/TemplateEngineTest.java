package com.epam.ld.module2.testing.template;


import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exception.BusinessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateEngineTest {

    private TemplateEngine templateEngine;
    private Template template;
    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() throws UnsupportedEncodingException {
        templateEngine = new TemplateEngine();
        template = new Template();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut, false, StandardCharsets.UTF_8.toString()));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        System.setIn(testIn);
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void messageShouldNotBeBlank() {
        provideInput("");
        Executable executable = () -> templateEngine.generateMessage(new Template(), new Client());
        assertThrows(BusinessException.class, executable, "Subject cannot be null");
    }

    @Test
    public void templateShouldReturnSubject() {
        String subject = template.getSubject();
        assertEquals("Subject: #{subject}", subject);
    }

    @Test
    public void messageShouldBeWithSubjectAndBody() throws BusinessException {
        provideInput("My subject\nMy body");
        String message = templateEngine.generateMessage(template, new Client());
        assertEquals("Subject: #{My subject}\nBody: #{My body}", message);
    }

    @Test
    public void templateShouldReturnBody() {
        String body = template.getBody();
        assertEquals("Body: #{body}", body);
    }
}
