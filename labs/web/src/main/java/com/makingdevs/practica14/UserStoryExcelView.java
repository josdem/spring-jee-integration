package com.makingdevs.practica14;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import com.makingdevs.model.UserStory;

@Component
public class UserStoryExcelView extends AbstractJExcelView {

  @Override
  protected void buildExcelDocument(Map<String, Object> model, WritableWorkbook workbook, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    WritableSheet sheet = workbook.createSheet("Tasks", 0);
    List<UserStory> userStories= (List<UserStory>) model.get("userStories");
    int i = 0;
    
    for (UserStory us : userStories) {
      sheet.addCell(new Label(0, i, String.valueOf(us.getId())));
      sheet.addCell(new Label(1,i,us.getDescription()));
      sheet.addCell(new Label(2,i,us.getEffort().toString()));
      sheet.addCell(new Label(3,i,us.getPriority().toString()));
      i++;
    }

  }

}
