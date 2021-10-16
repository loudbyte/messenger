package com.epam.ld.module2.testing.template;


import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemplateEngineTest {

    private TemplateEngine templateEngine;

    @BeforeEach
    public void setUp() {
        templateEngine = new TemplateEngine();
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
}
