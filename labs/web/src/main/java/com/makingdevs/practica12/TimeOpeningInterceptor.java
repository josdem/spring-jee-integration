package com.makingdevs.practica12;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TimeOpeningInterceptor implements HandlerInterceptor {

  private Log log = LogFactory.getLog(TimeOpeningInterceptor.class);
  private int openingTime = 0;
  private int closingTime = 60;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.debug("preHandle()");
    log.debug(request);
    log.debug(response);
    log.debug(handler);
    Calendar cal = Calendar.getInstance();
    int hour = cal.get(Calendar.SECOND);
    if (openingTime <= hour && hour < closingTime) {
      return true;
    } else {
      response.sendRedirect("http://makingdevs.com");
      return false;
    }
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    log.debug("postHandle()");
    log.debug(request);
    log.debug(response);
    log.debug(handler);
    log.debug(modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    log.debug("afterCompletion()");
    log.debug(request);
    log.debug(response);
    log.debug(handler);
    log.debug(ex);
  }

}
