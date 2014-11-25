package com.makingdevs.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.makingdevs.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:JmsAppCtx.xml"})
public class MessageProducerTest {
  
  @Autowired
  private MessageProducer messageProducer;

  @Test
  public void testMessageProducer() {
    messageProducer.heavyOperationForDelegationAndProcessing(new Project());
  }

}
