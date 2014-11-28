package com.makingdevs.jobs;

public interface SystemScheduledJob {
  void generateAndSendSystemInfo();
  void checkTasksDoneAndPending();
}
