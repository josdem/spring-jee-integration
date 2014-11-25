package com.makingdevs.jms.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.makingdevs.jms.MessageProducer;
import com.makingdevs.model.Project;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service("messageProducer")
public class MessageProducerImpl implements MessageProducer {

  @Autowired
  private JmsTemplate jmsTemplate;
  @Autowired
  @Qualifier("queue")
  private Destination destination;

  private Log log = LogFactory.getLog(MessageProducerImpl.class);

  @Override
  public void heavyOperationForDelegationAndProcessing(Project project) {
    log.debug("Sending message: ");
    XStream stream = new XStream(new DomDriver());
    final String xml = stream.toXML(project);
    log.debug("Message: " + xml);
    jmsTemplate.send(destination, new MessageCreator() {
      public Message createMessage(Session session) throws JMSException {
        TextMessage message = session.createTextMessage();
        message.setText(xml);
        return message;
      }
    });

  }

}
