package com.makingdevs.jobs.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import com.makingdevs.jobs.MultiplesScheduledJobs;

public class MultiplesScheduledJobsImpl implements MultiplesScheduledJobs {

  @Async
  @Scheduled(cron="20 0/1 * * * ?")
  public void heavyWorkSync1() {
    theSameJobWithNameAndCode("heavyWorkSync1", "HWS1*");
  }

  @Async
  @Scheduled(cron="30 0/1 * * * ?")
  public void heavyWorkSync2() {
    theSameJobWithNameAndCode("heavyWorkSync2", "HWS2-");
  }

  @Scheduled(cron="10 0/1 * * * ?")
  public void heavyWorkAsync1() {
    theSameJobWithNameAndCode("heavyWorkAsync1", "HWA1$");
  }

  @Scheduled(cron="15 0/1 * * * ?")
  public void heavyWorkAsync2() {
    theSameJobWithNameAndCode("heavyWorkAsync1", "HWA2%");
  }

  private void theSameJobWithNameAndCode(String name, String code) {
    try {
      System.out.println("Initiating " + name + "...");
      for (int i = 0; i < 1500; i++) {
        System.out.print(code + " ");
        Thread.sleep(10);
      }
      System.out.println("Finishing " + name + "...");
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }

}
