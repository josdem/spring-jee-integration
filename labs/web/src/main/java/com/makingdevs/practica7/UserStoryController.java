package com.makingdevs.practica7;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.makingdevs.model.UserStory;
import com.makingdevs.repositories.ProjectRepository;
import com.makingdevs.services.UserStoryService;

@Controller
public class UserStoryController {

  @Autowired
  UserStoryService userStoryService;

  @Autowired
  ProjectRepository projectRepository;

  @RequestMapping("/project/{codeName}/userStories")
  public String userStoriesForProject(@PathVariable("codeName") String codeName, Model model) {
    List<UserStory> userStories = userStoryService.findUserStoriesByProject(codeName);
    if (userStories.size() == 0)
      model.addAttribute("project", projectRepository.findByCodeName(codeName));
    else
      model.addAttribute("project", userStories.get(0).getProject());
    model.addAttribute("userStories", userStories);
    return "userStory/project";
  }

  @RequestMapping("/project/{codeName}/userStory/new")
  public String createUserStory(@PathVariable("codeName") String codeName, ModelMap model) {
    UserStoryCommand userStoryCommand = new UserStoryCommand();
    userStoryCommand.setProject(projectRepository.findByCodeName(codeName));
    model.addAttribute("userStoryCommand", userStoryCommand);
    return "userStory/new";
  }

  @RequestMapping("/project/{codeName}/userStory/save")
  public String saveUserStory(@PathVariable("codeName") String codeName, @Valid UserStoryCommand userStoryCommand,
      BindingResult result, ModelMap model) {
    if (result.hasErrors()) {
      userStoryCommand.setProject(projectRepository.findByCodeName(codeName));
      model.put("userStory", userStoryCommand);
      return "userStory/new";
    } else {
      userStoryService.createUserStory(userStoryCommand.getUserStory());
      return "redirect:/project/" + codeName + "/userStories";
    }
  }
}