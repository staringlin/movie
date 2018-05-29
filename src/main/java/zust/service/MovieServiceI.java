package zust.service;

import java.util.List;

import zust.dto.Json;
import zust.dto.MovieDto;


public interface MovieServiceI {
	public List<MovieDto> getFeaturedMoview();
	public String getRating(int id);
	public String getIntroduction(String movieName);
	String getDoubanId(String movieName);
	public List<MovieDto> search(String movieName);
	public List<MovieDto> getTypedMovie(String type,String currentPage);
	public Json addPreference(int userID, int movieID, int preference);
	public int getTypedCount(String type);
	public List<MovieDto> getReviewedMovie(int userID);
	List<MovieDto> getMovies(String currentPage, int rows);
	int getCount(int rows);
	public Json modifyMovie(MovieDto m);
	public Json delete(int id);
	public void addMovie(String newFileName, MovieDto dto);
	
}
