package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.exception.BusinessException;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

import static com.epam.ld.module2.testing.Properties.setProps;

public class Main {
    public static void main(String[] args) throws BusinessException {
        setProps(args);
        Messenger messenger = new Messenger(new MailServer(new IOService()), new TemplateEngine());
        messenger.sendMessage(new Client(), new Template());
        messenger.sendMessageToFile(new Client(), new Template());
    }

}
