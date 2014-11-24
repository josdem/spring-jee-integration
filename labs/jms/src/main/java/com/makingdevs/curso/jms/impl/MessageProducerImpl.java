package com.makingdevs.curso.jms.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.makingdevs.asembly.vo.Cliente;
import com.makingdevs.curso.jms.MyMessageProducer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service("messageProducer")
public class MessageProducerImpl implements MyMessageProducer {

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination topico;
	
	@Override
	public void operacionPesadaConCliente(Cliente cliente) {
		XStream stream = new XStream(new DomDriver());
		final String xml = stream.toXML(cliente);
		
		jmsTemplate.send(topico, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText(xml);
				return message;
			}
		});
		
	}
	
}
