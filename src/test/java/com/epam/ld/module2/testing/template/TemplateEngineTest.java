package com.epam.ld.module2.testing.template;


import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateEngineTest {

    private TemplateEngine templateEngine;
    private Template template;

    @BeforeEach
    public void setUp() {
        templateEngine = new TemplateEngine();
        template = new Template();
    }

    @Test
    public void messageShouldBeNotNUll() {
        String message = templateEngine.generateMessage(new Template(), new Client());
        assertNotNull(message);
    }

    @Test
    public void messageShouldNotBeBlank() {
        String message = templateEngine.generateMessage(new Template(), new Client());
        assertTrue(StringUtils.isNotBlank(message));
    }

    @Test
    public void templateShouldReturnSubject() {
        String subject = template.getSubject();
        assertEquals("Subject: #{subject}", subject);
    }

    @Test
    public void messageShouldBeWithSubjectAndBody() {
        String message = templateEngine.generateMessage(template, new Client());
        assertEquals(template.getSubject() + "\n" + template.getBody(), message);
    }

    @Test
    public void templateShouldReturnBody() {
        String body = template.getBody();
        assertEquals("Body: #{body}", body);
    }
}
