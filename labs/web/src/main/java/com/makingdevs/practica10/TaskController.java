package com.makingdevs.practica10;

import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.makingdevs.model.Project;
import com.makingdevs.model.Task;
import com.makingdevs.model.TaskStatus;
import com.makingdevs.model.UserStory;
import com.makingdevs.repositories.ProjectRepository;
import com.makingdevs.repositories.UserStoryRepository;

@Controller
@RequestMapping("/project/{codeName}/userStories/{userStoryId}")
@SessionAttributes("tasks") // Wow! What is this?
public class TaskController {

  private Log log = LogFactory.getLog(TaskController.class);

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  UserStoryRepository userStoryRepository;

  @ModelAttribute
  public Project currentProject(@PathVariable("codeName") String codeName) {
    return projectRepository.findByCodeName(codeName);
  }

  @ModelAttribute
  public UserStory currentUserStory(@PathVariable("userStoryId") Long userStoryId) {
    return userStoryRepository.findOne(userStoryId);
  }

  @ModelAttribute
  public void taskStatus(Map<String, Object> map) {
    map.put("taskStatusList", TaskStatus.values());
  }

  @ModelAttribute
  public void tasksForThisUserStory(Model model) {
    // Hey, validate if the userStory has tasks ...
    // Hey, validate the current user story ...
    if (!model.containsAttribute("tasks")) {
      model.addAttribute("tasks", new Vector<Task>());
    }
  }

  @RequestMapping(value = "/task", method = RequestMethod.GET)
  public String newTask(ModelMap model) {
    Task task = new Task();
    model.addAttribute("task", task);
    return "task/new";
  }

  @RequestMapping(value = "/task", method = RequestMethod.POST)
  public String newTask(@ModelAttribute("tasks") Vector<Task> tasks, Task task, BindingResult result) {
    tasks.add(task);
    return "redirect:task";
  }

  @RequestMapping("/saveTasks")
  public ModelAndView createTasksForThisUserStory(@ModelAttribute("project") Project project, SessionStatus status) {
    // Hey ma!!! Let me save the list...
    status.setComplete(); // Hey look ma! I finish the current session with the objects
    return new ModelAndView("redirect:/project/" +project.getCodeName()+"/userStories");
  }
}
// Hey you know what tod do...