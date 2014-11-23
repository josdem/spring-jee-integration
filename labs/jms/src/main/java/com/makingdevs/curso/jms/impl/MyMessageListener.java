package com.synergyj.curso.jms.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import com.synergyj.asembly.vo.Cliente;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Component("myMessageListener")
public class MyMessageListener implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("En un momento disparamos la ejecuci—n, estamos disponiendo de tiempo m‡quina...");
			Thread.sleep(5000);
			// Este es un proceso largo, que requiere consumo de m‡quina y tiempo de procesamiento
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
			}
			//No tenemos que esperar a que termine para que la aplicaci—n siga funcionando
		} catch (InterruptedException e) {
			System.out.println(" ~_~ ****** ~_~ ERROR: "+e.getMessage());
		}
		if(message instanceof TextMessage) {
			TextMessage mensaje = (TextMessage)message;
			String xml = "";
			try {
				xml = mensaje.getText();
				XStream stream = new XStream(new DomDriver());
				Cliente cliente = (Cliente)stream.fromXML(xml);
				System.out.println(ToStringBuilder.reflectionToString(cliente));
			} catch (JMSException e) {
				System.out.println(" ~_~ ****** ~_~ ERROR: "+e.getMessage());
			}
			System.out.println(xml);
		}
	}

}
