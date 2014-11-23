package com.makingdevs.practica9;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.makingdevs.model.Project;
import com.makingdevs.practica8.SprintCommand;
import com.makingdevs.repositories.ProjectRepository;
import com.makingdevs.services.SprintService;

@Controller
@RequestMapping("/sprint/")
public class SprintModelAttributeController {

  private Log log = LogFactory.getLog(SprintModelAttributeController.class);

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  SprintService sprintService;

  @ModelAttribute
  public List<Project> allProjects() {
    log.debug("Obtaining projects");
    List<Project> projectList = new ArrayList<Project>();
    Iterator<Project> iterator = projectRepository.findAll().iterator();
    while(iterator.hasNext())
      projectList.add(iterator.next());
    return projectList;
  }

  @RequestMapping(value = { "/new", "/create" }, method = RequestMethod.GET)
  public String crearSprint(ModelMap model) {
    model.addAttribute("sprintCommand", new SprintCommand());
    // Hey look ma! No more references to project list...
    return "sprint/new";
  }

  @RequestMapping(value = { "/save", "/persist" }, method = RequestMethod.POST)
  public String persistirSprint(@Valid SprintCommand sprintCommand, Errors errors, ModelMap model) {
    // Hey look ma! No more references to project list...
    if (errors.hasErrors()) {
      model.addAttribute("sprintCommand", sprintCommand);
      return "sprint/new";
    } else {
      sprintService.createSprintForOneproject(sprintCommand.getSprint());
      return "redirect:/";
    }
  }

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
  }

}
// You must add @ComponentScan(basePackages = { "com.makingdevs.practica9" })
// or <context:component-scan base-package="com.makingdevs.practica9"/>
// and remove the package scan com.makingdevs.practica8