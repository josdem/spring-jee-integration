package com.makingdevs.practica13;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {

  public ModelAndView resolveException(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex) {
    Map<String,Object> model = new HashMap<String,Object>();
    model.put("ex", ex);
    model.put("message", ex.getMessage());
    return new ModelAndView("handlerException",model);
  }

}
