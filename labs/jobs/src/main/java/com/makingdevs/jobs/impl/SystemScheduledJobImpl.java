package com.makingdevs.jobs.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makingdevs.jobs.SystemScheduledJob;
import com.makingdevs.model.Project;
import com.makingdevs.model.Task;
import com.makingdevs.model.TaskStatus;
import com.makingdevs.model.UserStory;
import com.makingdevs.repositories.ProjectRepository;
import com.makingdevs.repositories.TaskRepository;
import com.makingdevs.repositories.UserStoryRepository;

@Component("systemScheduledJob")
public class SystemScheduledJobImpl implements SystemScheduledJob {
  
  private Log log = LogFactory.getLog(SystemScheduledJobImpl.class);

  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  UserStoryRepository userStoryRepository;
  @Autowired
  TaskRepository taskRepository;

  @Override
  public void generateAndSendSystemInfo() {
    List<Project> projects = (List<Project>) projectRepository.findAll();
    StringBuffer s = new StringBuffer();
    for (Project p : projects) {
      s.append("* " + p.getCodeName() + " - " + p.getName() + "\n");
      List<UserStory> userStoriesForProject = userStoryRepository
          .findAllByProject(p);
      for (UserStory us : userStoriesForProject) {
        s.append("\t** " + us.getDescription() + "[ Effort: " + us.getEffort()
            + " ]" + "\n");
        List<Task> tasks = taskRepository.findAllByUserStoryAndStatusEquals(us,
            TaskStatus.TODO);
        for (Task t : tasks) {
          s.append("\t\t*** " + t.getDescription() + "\n");
        }
      }
    }
    log.debug(s.toString());
  }

  @Override
  public void checkTasksDoneAndPending() {
    StringBuffer s = new StringBuffer();
    List<Task> tasks = (List<Task>) taskRepository.findAll();
    for (Task t : tasks) {
      if (t.getStatus() == TaskStatus.TODO || t.getStatus() == TaskStatus.WIP)
        s.append("* " + t.getDescription() + " - " + t.getStatus() + "\n");
    }
    log.debug(s.toString());
  }

}
