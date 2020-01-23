package com.revature.comicappv2.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.comicappv2.repositories.ComicDao;
import com.revature.comicappv2.repositories.ComicDaoPostgres;


@WebServlet(name="Testing", urlPatterns = {"/dao"})
public class DaoTestingServlet extends HttpServlet{
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    //super.doGet(req, resp);
    
    System.out.println("hey");
    
    ComicDao cd = new ComicDaoPostgres();
    
    System.out.println(cd.getAll());
  }

}
