package com.makingdevs.practica3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
	public String userStoriesForProject(
			@PathVariable("codeName") String codeName, Model model) {
		List<UserStory> userStories = userStoryService
				.findUserStoriesByProject(codeName);
		if (userStories.size() == 0)
			model.addAttribute("project",
					projectRepository.findByCodeName(codeName));
		else
			model.addAttribute("project", userStories.get(0).getProject());
		model.addAttribute("userStories", userStories);
		return "userStory/project";
	}

	@RequestMapping("/project/{codeName}/userStory/new")
	public String createUserStory(@PathVariable("codeName") String codeName,
			ModelMap model) {
		UserStory us = new UserStory();
		us.setProject(projectRepository.findByCodeName(codeName));
		model.addAttribute("userStory", us);
		return "userStory/new";
	}

	@RequestMapping("/project/{codeName}/userStory/save")
	public String saveUserStory(@PathVariable("codeName") String codeName,
			UserStory userStory) {
		//userStory.setProject(projectRepository.findByCodeName(codeName));
		//userStory.setDateCreated(new Date());
		//userStory.setLastUpdated(new Date());
		//userStoryRepository.save(userStory);
		userStoryService.createUserStory(userStory);
		return "redirect:/project/" + codeName + "/userStories";
	}
}

// You must add @ComponentScan(basePackages = { "com.makingdevs.practica3" })
// or <context:component-scan base-package="com.makingdevs.practica3"/>
// depending on your configuration