package com.makingdevs.practica8;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.makingdevs.repositories.ProjectRepository;
import com.makingdevs.services.SprintService;

@Controller
@RequestMapping("/sprint/")
public class SprintController {

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  SprintService sprintService;

  @RequestMapping(value = { "/new", "/create" }, method = RequestMethod.GET)
  public String crearSprint(ModelMap model) {
    model.addAttribute("sprintCommand", new SprintCommand());
    model.addAttribute(projectRepository.findAll());
    return "sprint/new";
  }

  @RequestMapping(value = { "/save", "/persist" }, method = RequestMethod.POST)
  public String persistirSprint(@Valid SprintCommand sprintCommand, Errors errors, ModelMap model) {
    model.addAttribute(projectRepository.findAll()); // Wuack!!!!, this again...
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