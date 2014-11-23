package com.makingdevs.practica8;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.makingdevs.model.Project;
import com.makingdevs.model.Sprint;

public class SprintCommand {
  private Long id;
  @NotBlank
  @Size(min = 1, max = 100)
  private String name;
  @Size(min = 0, max = 1000)
  private String description;
  @NotNull
  private Date startDate;
  @Future
  @NotNull
  private Date endDate;
  @NotNull
  private Project project;

  public SprintCommand() {
    super();
    project = new Project();
  }

  public Sprint getSprint() {
    Sprint sprint = new Sprint();
    sprint.setId(id);
    sprint.setDescription(description);
    sprint.setEndDate(endDate);
    sprint.setStartDate(startDate);
    sprint.setName(name);
    sprint.setProject(project);
    return sprint;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

}
