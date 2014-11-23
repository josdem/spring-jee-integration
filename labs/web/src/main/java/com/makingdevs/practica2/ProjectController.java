package com.makingdevs.practica2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.makingdevs.model.Project;
import com.makingdevs.repositories.ProjectRepository;
import com.makingdevs.services.ProjectService;

@Controller
public class ProjectController {
  
  @Autowired
  ProjectRepository projectRepository;
  
  @Autowired
  ProjectService projectService;

  @RequestMapping("/project")
  public String allProjects(Model model) {
    model.addAttribute("message", "Welcome to projects manager!");
    model.addAttribute("projects",projectRepository.findAll());
    return "project/list";
  }
  
  @RequestMapping(value="/project/new",method=RequestMethod.GET)
  public Project createNewProject() {
    return new Project();
  }
  
  @RequestMapping(value="/saveProject",method=RequestMethod.POST)
  public ModelAndView saveProject(Project project) {
    projectService.createNewProject(project);
    return new ModelAndView("redirect:/project");
  }
  
}
// You must add @ComponentScan(basePackages = { "com.makingdevs.practica2" })
// or <context:component-scan base-package="com.makingdevs.practica2"/>
// depending on your configuration