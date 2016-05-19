package com.makingdevs.integration;

import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

//tag::injection[]
@Service("mailService")
public class MailServiceImpl implements MailService {

  @Autowired
  private Configuration configuration; // <1>
  @Autowired
  private SimpleMailMessage templateMessage; // <2>
  @Autowired
  private JavaMailSender javaMailSender; // <3>
  /// end::injection[]
  // tag::sendMail[]

  public void sendMail(String email, String message) {
    SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
    msg.setTo(email);
    msg.setText(message);
    javaMailSender.send(msg);
  }

  // end::sendMail[]
  // tag::sendMimeMail[]
  public void sendMimeMail(final String email, final String message, final String subject) {
    MimeMessagePreparator preparator = new MimeMessagePreparator() {
      public void prepare(MimeMessage mimeMessage) throws Exception {
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mimeMessage.setFrom(new InternetAddress("social@makingdevs.com"));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(message);
      }
    };
    javaMailSender.send(preparator);
  }

  // end::sendMimeMail[]
  // tag::sendMailWithAttach[]
  public void sendMailWithAttach(String email, String message, String subject, FileSystemResource attach) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setTo(email);
    helper.setFrom("social@makingdevs.com");
    helper.setText(message);
    helper.setSubject(subject);
    helper.addAttachment(attach.getFilename(), attach);
    javaMailSender.send(mimeMessage);
  }

  // end::sendMailWithAttach[]
  // tag::sendMailWithInline[]
  public void sendMailWithInline(String email, String message, String subject, FileSystemResource inline) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setTo(email);
    helper.setFrom("social@makingdevs.com");
    helper.setSubject(subject);
    helper.setText(message, true);
    helper.addInline("identifier1", inline);
    javaMailSender.send(mimeMessage);
  }

  // end::sendMailWithInline[]
  // tag::sendMailWithEngine[]
  public void sendMailWithEngine(final String email, final Map<String, Object> model, final String subject, final String template) {
    MimeMessagePreparator preparator = new MimeMessagePreparator() {
      public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        Template myTemplate = configuration.getTemplate(template);
        message.setTo(email);
        message.setFrom("contact@josdem.io");
        message.setSubject(subject);
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(myTemplate, model);
        message.setText(text, true);
      }
    };
    javaMailSender.send(preparator);
  }
  // end::sendMailWithEngine[]

}
