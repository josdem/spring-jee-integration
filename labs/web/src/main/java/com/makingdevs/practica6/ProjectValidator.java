package com.makingdevs.practica6;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.makingdevs.model.Project;

public class ProjectValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Project.class.equals(clazz);
  }

  @Override
  public void validate(Object object, Errors errors) {
    Project project = (Project) object;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty","The name is required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codeName", "codename.empty","The code name is required");
    if(project.getCodeName().length() >= 20)
      errors.rejectValue("codeName", "codename.toolong", "The code name is too long");
  }

}
