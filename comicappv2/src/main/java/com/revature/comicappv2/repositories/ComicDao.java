package com.revature.comicappv2.repositories;

import java.util.List;
import com.revature.comicappv2.models.Comic;

public interface ComicDao {
  
  Comic get(int id);
  
  List<Comic> getAll();
  
  List<Comic> getByPriceRange(double lower, double upper);
  
  void save(Comic comic);
  
  void update(Comic comic);

}
