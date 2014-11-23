package com.makingdevs.practica7;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.makingdevs.model.User;

public class UserCommand {
  private Long id;
  @Email
  @NotBlank
  private String username;
  @NotNull
  @AssertTrue
  private boolean enabled;
  
  public User getUser(){
    User user = new User();
    user.setId(id);
    user.setEnabled(enabled);
    user.setUsername(username);
    return user;
  }
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public boolean isEnabled() {
    return enabled;
  }
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  
}
