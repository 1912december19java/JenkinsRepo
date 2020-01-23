package com.revature.comicappv2.repositories;

// import com.revature.Main;
import com.revature.comicappv2.models.Comic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// import org.apache.log4j.Logger;



public class ComicDaoPostgres implements ComicDao {

  private static Connection conn;
  
  // private static Logger log = Logger.getLogger(Main.class);
  

  static {
    try {
      Class.forName("org.postgresql.Driver");
    }catch(ClassNotFoundException e1) {
      e1.printStackTrace();
    }
    
    try {
      conn = DriverManager.getConnection(
           System.getenv("connstring"), System.getenv("username"), System.getenv("password"));
         // "jdbc:postgresql://database-1.cs4xqzqqrfzt.us-east-1.rds.amazonaws.com:5432/postgres",
         // "postgres", "5235923a");
      // log.info("Connected to Database");
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    System.out.println("You are Connected");
  }


  @Override
  public Comic get(int id) {
    // TODO Auto-generated method stub
    Comic out = null;
    try {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM comics WHERE id = ?");
      stmt.setInt(1, id);
      stmt.execute();

      ResultSet rs = stmt.getResultSet();
      rs.next();
      out = new Comic(
          rs.getInt("id"),
          rs.getString("title"),
          rs.getInt("page_count"),
          rs.getDouble("price"),
          rs.getInt("rating"));
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return out;
  }

  @Override
  public void save(Comic comic) {
    // TODO Auto-generated method stub
    PreparedStatement stmt = null;
    
    try {
      stmt = conn.prepareStatement("INSERT INTO comics(title, page_count, price, rating) VALUES(?,?,?,?)");
      stmt.setString(1,  comic.getTitle());
      stmt.setInt(2,  comic.getPageCount());
      stmt.setDouble(3, comic.getPrice());
      stmt.setDouble(4, comic.getRating());
      
      stmt.execute();
      
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

  }

  @Override
  public void update(Comic comic) {
    
    PreparedStatement stmt = null;
    
    // TODO Auto-generated method stub
    
    try {
      stmt = conn.prepareStatement("UPDATE comics SET title = ?, page_count = ?, price = ?, rating = ? WHERE id = ?");
      stmt.setString(1,  comic.getTitle());
      stmt.setInt(2,  comic.getPageCount());
      stmt.setDouble(3, comic.getPrice());
      stmt.setDouble(4, comic.getRating());
      stmt.setInt(5, comic.getId());
      
      stmt.execute();
      
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }


  }

  @Override
  public List<Comic> getAll() {
    // TODO Auto-generated method stub
    List<Comic> allComics = new ArrayList<Comic>();
    
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
      stmt = conn.prepareStatement("SELECT * FROM comics");
      if(stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while(rs.next()) {
        allComics.add(new Comic(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getInt("page_count"),
            rs.getDouble("price"),
            rs.getInt("rating")));
      }
    } catch (SQLException e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return allComics;
  }

  @Override
  public List<Comic> getByPriceRange(double lower, double upper) {
    // TODO Auto-generated method stub
    return null;
  }

}
