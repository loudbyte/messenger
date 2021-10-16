package com.epam.ld.module2.testing.template;


import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TemplateEngineTest {

    @Test
    public void messageShouldBeNotNUll() {
        TemplateEngine templateEngine = new TemplateEngine();
        String message = templateEngine.generateMessage(new Template(), new Client());
        assertNotNull(message);
    }
}
