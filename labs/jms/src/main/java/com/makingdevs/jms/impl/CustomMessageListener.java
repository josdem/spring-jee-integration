package com.makingdevs.jms.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.makingdevs.model.Project;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Component("customMessageListener")
public class CustomMessageListener implements MessageListener {

  private Log log = LogFactory.getLog(CustomMessageListener.class);

  @Override
  public void onMessage(Message message) {
    try {
      log.debug("Starting heavy process in JMS");
      Thread.sleep(1);
      for(int i=0;i<5000;i++){
        Thread.sleep(10);
        if(i%2==0)
          System.out.print("\\");
        if(i%3==0)
          System.out.print("|");
        if(i%4==0)
          System.out.print("/");
        if(i%5==0)
          System.out.print("-");
        System.out.print("\b");
      }
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    }
    if(message instanceof TextMessage) {
      TextMessage mensaje = (TextMessage)message;
      String xml = "";
      try {
        xml = mensaje.getText();
        XStream stream = new XStream(new DomDriver());
        Project project = (Project)stream.fromXML(xml);
        log.debug(project);
      } catch (JMSException e) {
        log.error(e.getMessage());
      }
    }
  }
}