package com.makingdevs.jms.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makingdevs.jms.DelegationService;
import com.makingdevs.model.Project;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Component("customMessageListener")
public class CustomMessageListener implements MessageListener {

  private Log log = LogFactory.getLog(CustomMessageListener.class);
  
  @Autowired
  private DelegationService delegationService;

  @Override
  public void onMessage(Message message) {
    log.debug("Starting heavy process in JMS!!!");
    if(message instanceof TextMessage) {
      TextMessage mensaje = (TextMessage)message;
      String xml = "";
      try {
        xml = mensaje.getText();
        XStream stream = new XStream(new DomDriver());
        Project project = (Project)stream.fromXML(xml);
        delegationService.processProject(project);
      } catch (JMSException e) {
        log.error(e.getMessage());
      }
    }
    log.debug("Message processed!!!");
  }
}