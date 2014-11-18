package com.makingdevs.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MailConfig.class})
public class MailServiceIntegrationTest {

  @Autowired
  MailService mailService;

  @Test
  public void sendingSimpleMail() {
    mailService.sendMail("juan@makingdevs.com",
        "Mensaje desde la aplicación...");
  }

  @Test
  public void sendingMimeMail() {
    mailService.sendMimeMail("juan@makingdevs.com",
        "Mensaje MIME desde la aplicación...", "Notificación de sistema");
  }



}
