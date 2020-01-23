package com.revature.comicappv2.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.comicappv2.models.Comic;
import com.revature.comicappv2.repositories.ComicDaoPostgres;
import com.revature.comicappv2.services.ComicService;

@WebServlet(name = "FrontController", urlPatterns = {"/api/*"})
public class FrontController extends HttpServlet {
  
  private ComicService comicService; 
  private ObjectMapper om;  
  
  @Override
  public void init() throws ServletException {
    // TODO Auto-generated method stub
    this.comicService = new ComicService(new ComicDaoPostgres());
    this.om = new ObjectMapper();
    super.init();
  }


  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    // super.doGet(req, resp);

    System.out.println("URI:" + req.getRequestURI());
    String[] tokens = req.getRequestURI().split("/");


    if (tokens[3].equals("comics")) {
      //For now, let's implement our getAll
      boolean idWasSpecified = tokens.length > 4;
      if(idWasSpecified) {
        Integer id = Integer.valueOf(tokens[4]);
        Comic comic = comicService.get(id);
        
        if(comic == null) {
          resp.setStatus(404);
        }
        resp.getWriter().write(om.writeValueAsString(comic));    
        System.out.println(om.writeValueAsString(comic));
        
      }else {
        List<Comic> comics = null;
        if(req.getParameter("lower") == null && req.getParameter("upper") == null) {
          comics = comicService.getAll();
        }else {
          try {
            comics = comicService.getByPriceRange(Double.valueOf(req.getParameter("lower")), Double.valueOf(req.getParameter("upper")));
          }catch (NullPointerException e){
            resp.sendError(400, "must specify both lower and upper if one is specified");
          }
        }
        
        
        String myJsonObject = om.writeValueAsString(comics);
        
        
         
        resp.getWriter().write(myJsonObject);
        System.out.println(myJsonObject);
      }     

    }

  }
  
   
  
 
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    System.out.println("URI" + req.getRequestURI());
    String[] tokens = req.getRequestURI().split("/");
    
    switch(tokens[3]) {
      case "comics":
        Comic receivedComic = om.readValue(req.getReader(), Comic.class);
        System.out.println(receivedComic);
        comicService.save(receivedComic);
        
        break;
        
      default:
        resp.setStatus(404);
    }
  }
  
  

}
