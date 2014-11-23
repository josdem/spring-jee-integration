package com.makingdevs.practica14;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.makingdevs.model.UserStory;

@Component
public class UserStoryPdfView extends AbstractPdfView {

  @Override
  protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    List<UserStory> userStories= (List<UserStory>) model.get("userStories");

    int i = 0;
    for (UserStory us : userStories) {
      document.addAuthor("makingdevs");
      document.addTitle("User Stories");
      String buffer = String.valueOf(us.getId());
      buffer += " - " + us.getDescription();
      document.add(new Paragraph(buffer));
      i++;
    }
    
  }

}
