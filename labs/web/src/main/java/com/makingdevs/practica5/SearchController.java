package com.makingdevs.practica5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.makingdevs.model.Task;
import com.makingdevs.model.UserStory;
import com.makingdevs.repositories.TaskRepository;
import com.makingdevs.repositories.UserStoryRepository;

@Controller
@RequestMapping(value = "/search/**")
public class SearchController {

  private Log log = LogFactory.getLog(SearchController.class);

  @Autowired
  UserStoryRepository userStoryRepository;

  @Autowired
  TaskRepository taskRepository;

  @RequestMapping(method = RequestMethod.GET)
  public String searchInProjects(HttpServletResponse response, @CookieValue(value = "queryCounter", defaultValue = "0") Integer queryCounter) {
    queryCounter++;
    log.debug("This is the " + queryCounter + " time!");
    Cookie cookie = new Cookie("queryCounter", queryCounter.toString());
    response.addCookie(cookie);
    return "search";
  }

  @RequestMapping(method = RequestMethod.POST)
  public Map<String, Object> searchResults(@RequestParam("minValue") Integer minValue,
      @RequestParam("maxValue") Integer maxValue, @RequestParam("taskDescription") String taskDescription) {
    Map<String, Object> model = new HashMap<String, Object>();
    List<UserStory> userStories = userStoryRepository.findAllByEffortBetween(minValue, maxValue);
    List<Task> tasks = taskRepository.findAllByDescriptionLike("%" + taskDescription + "%");
    model.put("minValue", minValue);
    model.put("maxValue", maxValue);
    model.put("taskDescription", taskDescription);
    model.put("userStories", userStories);
    model.put("tasks", tasks);
    return model;
  }
}
