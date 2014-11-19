package com.makingdevs.integration;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MailConfig.class})
public class MailServiceIntegrationTest {

  @Autowired
  MailService mailService;

  @Test
  public void sendingSimpleMailTest() {
    mailService.sendMail("username@domain.com", // <1>
        "Mensaje desde la aplicación...");
  }

  @Test
  public void sendingMimeMailTest() {
    mailService.sendMimeMail("username@domain.com",
        "Mensaje MIME desde la aplicación...", "Notificación de sistema");
  }

  @Test
  public void sendingMailWithAttachmentTest() throws MessagingException {
    FileSystemResource attach = new FileSystemResource(
        "/home/makingdevs/ubuntu-logo/ubuntu-logo16.png");
    mailService.sendMailWithAttach("username@domain.com",
        "Mensaje con documento adjunto...", "Notificación de informe", attach); // <2>
  }

  @Test
  public void sendingMailWithInlineTest() throws MessagingException {
    FileSystemResource attach = new FileSystemResource(
        "/home/makingdevs/ubuntu-logo/ubuntu-logo16.png");
    mailService.sendMailWithInline("username@domain.com",
        "Mensaje con documento adjunto...", "Notificación de informe", attach); // <3>
  }

  @Test
  public void sendingMailWithEngineTest() throws MessagingException {
    // <4>
  }

}
