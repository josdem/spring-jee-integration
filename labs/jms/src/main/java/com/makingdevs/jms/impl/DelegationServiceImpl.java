package com.makingdevs.jms.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.makingdevs.jms.DelegationService;
import com.makingdevs.model.Project;
import com.makingdevs.services.ProjectService;

@Service
public class DelegationServiceImpl implements DelegationService {
  
  @Autowired
  ProjectService projectService;
  
  private Log log = LogFactory.getLog(DelegationServiceImpl.class);

  @Override
  public void processProject(Project project) {
    log.debug("Processing message in delegate....");
    projectService.createNewProject(project);
    log.debug(project);
  }

}
