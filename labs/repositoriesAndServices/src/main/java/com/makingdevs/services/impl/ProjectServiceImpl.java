package com.makingdevs.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makingdevs.model.Project;
import com.makingdevs.repositories.ProjectRepository;
import com.makingdevs.services.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
  
  @Autowired
  ProjectRepository projectRepository;

  @Override
  public void createNewProject(Project project) {
    project.setDateCreated(new Date());
    project.setLastUpdated(new Date());
    project.setCodeName(project.getCodeName().toUpperCase());
    projectRepository.save(project);
  }

  @Override
  public Project findProjectByCodeName(String codeName) {
    return projectRepository.findByCodeName(codeName);
  }

  @Override
  public Integer totalEffortForProject(String codeName) {
    // TODO Auto-generated method stub
    return null;
  }

}
