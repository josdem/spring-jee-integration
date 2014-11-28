package com.makingdevs.jobs.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.makingdevs.jobs.SimpleScheduledJob;

public class SimpleScheduledJobImpl implements SimpleScheduledJob {
  
  private Log log = LogFactory.getLog(SimpleScheduledJobImpl.class);

  public String welcome() {
    String result = "welcome() at " + new Date();
    log.debug(result);
    return result;
  }

  public String foo() {
    String result = "foo() at " + new Date();
    log.debug(result);
    return result;
  }

  public String bar() {
    String result = "bar() at " + new Date();
    log.debug(result);
    return result;
  }

}
