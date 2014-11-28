package com.makingdevs.practica6;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.makingdevs.jms.MessageProducer;
import com.makingdevs.model.Project;
import com.makingdevs.repositories.ProjectRepository;

@Controller
public class ProjectValidationController {

  @Autowired
  ProjectRepository projectRepository;

  //@Autowired
  //ProjectService projectService;
  @Autowired
  MessageProducer messageProducer;

  @RequestMapping("/project")
  public String allProjects(Model model) {
    model.addAttribute("message", "Welcome to projects manager!");
    model.addAttribute("projects", projectRepository.findAll());
    return "project/list";
  }

  @RequestMapping(value = "/project/new", method = RequestMethod.GET)
  public Project createNewProject() {
    return new Project();
  }

  @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
  //public ModelAndView saveProject(@Validated Project project, BindingResult binding) {
  public ModelAndView saveProject(@Valid Project project, BindingResult binding) {
    if (binding.hasErrors()) {
      ModelAndView mv = new ModelAndView("project/new");
      mv.getModel().put("project", project);
      return mv;
    } else {
      //projectService.createNewProject(project);
      messageProducer.heavyOperationForDelegationAndProcessing(project);
      return new ModelAndView("redirect:/project");
    }
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(new ProjectValidator());
  }

}
// You must add @ComponentScan(basePackages = { "com.makingdevs.practica6" })
// or <context:component-scan base-package="com.makingdevs.practica6"/>
// and remove the package scan com.makingdevs.practica2
