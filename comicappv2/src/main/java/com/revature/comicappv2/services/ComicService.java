package com.revature.comicappv2.services;

import java.util.List;
import com.revature.comicappv2.models.Comic;
import com.revature.comicappv2.repositories.ComicDao;

public class ComicService {

  /**
   * A basic service layer that just sits between the controller and repository This class will be
   * useful in the future for business logic and glue.
   * 
   */



  private ComicDao comicDao;

  public ComicService(ComicDao comicDao) {
    this.comicDao = comicDao;
  }

  /**
   * Get a Comic by its id
   * 
   * @param id
   * @return
   */
  public Comic get(int id) {
    return comicDao.get(id);
  }

  /**
   * Gets all Comics
   * 
   * @return
   */
  public List<Comic> getAll() {
    return comicDao.getAll();
  }

  /**
   * Gets all comics between two prices.
   * 
   * @param lower
   * @param upper
   * @return
   */
  public List<Comic> getByPriceRange(double lower, double upper) {
    return comicDao.getByPriceRange(lower, upper);
  }

  /**
   * Save a new comic.
   * 
   * @param comic A comic not yet persisted.
   */
  public void save(Comic comic) {
    comicDao.save(comic);
  }

  /**
   * Update an existing comic. Works by id.
   * 
   * @param comic
   */
  public void update(Comic comic) {
    comicDao.update(comic);
  }



}
