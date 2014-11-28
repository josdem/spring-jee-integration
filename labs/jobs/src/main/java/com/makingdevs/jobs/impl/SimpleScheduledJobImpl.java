package com.makingdevs.jobs.impl;

import java.util.Date;

import com.makingdevs.jobs.SimpleScheduledJob;

public class SimpleScheduledJobImpl implements SimpleScheduledJob {

  public String welcome() {
    String result = "welcome() at " + new Date();
    System.out.println(result);
    return result;
  }

  public String foo() {
    String result = "foo() at " + new Date();
    System.out.println(result);
    return result;
  }

  public String bar() {
    String result = "bar() at " + new Date();
    System.out.println(result);
    return result;
  }

}
