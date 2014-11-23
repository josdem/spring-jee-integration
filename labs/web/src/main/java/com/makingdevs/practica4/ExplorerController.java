package com.makingdevs.practica4;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.makingdevs.model.Task;

@Controller
@RequestMapping("/explorer")
public class ExplorerController {

  private Log log = LogFactory.getLog(ExplorerController.class);

  @RequestMapping("/requestAndResponse")
  public void explorarRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
    log.debug("\nRequest:\t" + ToStringBuilder.reflectionToString(request));
    log.debug("\nResponse:\t" + ToStringBuilder.reflectionToString(response));
  }

  @RequestMapping("/session")
  public String explorarSession(HttpSession session) {
    log.debug("\nSession:\t" + ToStringBuilder.reflectionToString(session));
    return "helloWorld";
  }

  @RequestMapping("/logout")
  public String logout(HttpSession session) {
    log.debug("\nSession:\t" + ToStringBuilder.reflectionToString(session));
    session.invalidate();
    return "helloWorld";
  }

  @RequestMapping("localeAndStream")
  public Map<String, Object> explorarLocaleAndStream(Locale locale, Reader reader, Writer writer) throws IOException {
    log.debug("\nLocale:\t" + ToStringBuilder.reflectionToString(locale));
    log.debug("\nInputStream:\t" + ToStringBuilder.reflectionToString(reader));
    log.debug("\nOutputStream:\t" + ToStringBuilder.reflectionToString(writer));
    return new HashMap<String, Object>();
  }

  @RequestMapping(value = "/commandErrors", method = RequestMethod.GET)
  public Model explorarCommandErrorsSessionStatus(ModelMap modelMap) {
    Model model = new ExtendedModelMap();
    model.addAttribute(new Task());
    log.debug("\nModel:\t" + ToStringBuilder.reflectionToString(model));
    log.debug("\nModelMap:\t" + ToStringBuilder.reflectionToString(modelMap));
    return model;
  }

  @RequestMapping(value = "/commandErrors", method = RequestMethod.POST)
  public String explorarCommandErrorsSessionStatus(Task task, Errors errors) {
    log.debug("\nObjeto:\t" + ToStringBuilder.reflectionToString(task));
    log.debug("\nErrors:\t" + ToStringBuilder.reflectionToString(errors));
    return "helloWorld";
  }
}
