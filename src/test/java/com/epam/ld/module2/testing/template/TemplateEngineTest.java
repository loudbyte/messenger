package com.epam.ld.module2.testing.template;


import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemplateEngineTest {

    @Test
    public void messageShouldBeNotNUll() {
        TemplateEngine templateEngine = new TemplateEngine();
        String message = templateEngine.generateMessage(new Template(), new Client());
        assertNotNull(message);
    }

    @Test
    public void messageShouldNotBeBlank() {
        TemplateEngine templateEngine = new TemplateEngine();
        String message = templateEngine.generateMessage(new Template(), new Client());
        assertTrue(StringUtils.isNotBlank(message));
    }
}
