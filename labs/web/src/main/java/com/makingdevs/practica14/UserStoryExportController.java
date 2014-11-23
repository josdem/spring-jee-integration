package com.makingdevs.practica14;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.makingdevs.model.UserStory;
import com.makingdevs.services.UserStoryService;

@Controller
public class UserStoryExportController {
  
  @Autowired
  UserStoryService userStoryService;

  @RequestMapping("/project/{codeName}/userStory/exportToExcel")
  public String exportToExcel(@PathVariable("codeName") String codeName, Model model) {
    List<UserStory> userStories = userStoryService.findUserStoriesByProject(codeName);
    model.addAttribute("userStories", userStories);
    return "userStoryExcelView";
  }
  
  @RequestMapping("/project/{codeName}/userStory/exportToPdf")
  public String exportToPdf(@PathVariable("codeName") String codeName, Model model) {
    List<UserStory> userStories = userStoryService.findUserStoriesByProject(codeName);
    model.addAttribute("userStories", userStories);
    return "userStoryPdfView";
  }
}
