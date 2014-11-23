package com.makingdevs.practica13;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.makingdevs.model.Project;
import com.makingdevs.repositories.ProjectRepository;
import com.makingdevs.services.ProjectService;

@Controller
public class ErrorController {
  
  @Autowired
  ProjectRepository projectRepository;
  
  @Autowired
  ProjectService projectService;

  @RequestMapping("/error")
  public void throwError(){
    projectService.createNewProject(new Project());
  }
  
  @RequestMapping("/error/db")
  public void throwDBError(){
    projectRepository.save(new Project());
  }
}
