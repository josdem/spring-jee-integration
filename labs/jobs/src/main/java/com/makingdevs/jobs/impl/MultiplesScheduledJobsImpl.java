package com.makingdevs.jobs.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.makingdevs.jobs.MultiplesScheduledJobs;

@Component
public class MultiplesScheduledJobsImpl implements MultiplesScheduledJobs {
  
  private Log log = LogFactory.getLog(MultiplesScheduledJobsImpl.class);

  @Async
  @Scheduled(cron="20 0/1 * * * ?")
  public void heavyWorkAsync1() {
    theSameJobWithNameAndCode("heavyWorkAsync1", "HWA1*");
  }

  @Async
  @Scheduled(cron="30 0/1 * * * ?")
  public void heavyWorkAsync2() {
    theSameJobWithNameAndCode("heavyWorkAsync2", "HWA2-");
  }

  @Scheduled(cron="10 0/1 * * * ?")
  public void heavyWorkSync1() {
    theSameJobWithNameAndCode("heavyWorkSync1", "HWS1$");
  }

  @Scheduled(cron="15 0/1 * * * ?")
  public void heavyWorkSync2() {
    theSameJobWithNameAndCode("heavyWorkSync2", "HWS2%");
  }

  private void theSameJobWithNameAndCode(String name, String code) {
    try {
      log.debug("Initiating " + name + "...");
      for (int i = 0; i < 1500; i++) {
        log.debug(code + " ");
        Thread.sleep(10);
      }
      log.debug("Finishing " + name + "...");
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    }
  }

}
