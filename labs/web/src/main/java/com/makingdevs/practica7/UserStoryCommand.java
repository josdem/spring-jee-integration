package com.makingdevs.practica7;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.makingdevs.model.Project;
import com.makingdevs.model.UserStory;

public class UserStoryCommand {
  
  private Long id;
  
  @NotNull
  @Size(min = 1, max = 1000)
  private String description;
  
  @Min(1)
  @Max(99)
  @NotNull
  private Integer priority;
  
  @Range(min = 1, max = 5)
  @NotNull
  private Integer effort;
  
  private Project project;

  public UserStoryCommand() {
    super();
    this.project = new Project();
  }
  
  public UserStory getUserStory(){
    UserStory us = new UserStory();
    us.setId(id);
    us.setDateCreated(new Date());
    us.setLastUpdated(new Date());
    us.setDescription(description);
    us.setPriority(priority);
    us.setEffort(effort);
    us.setProject(project);
    return us;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Integer getEffort() {
    return effort;
  }

  public void setEffort(Integer effort) {
    this.effort = effort;
  }

}
