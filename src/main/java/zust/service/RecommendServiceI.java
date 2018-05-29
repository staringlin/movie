package zust.service;

import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import zust.dto.MovieDto;



public interface RecommendServiceI {

	public List<RecommendedItem> userBasedRecommend(long userId ,int size);
	public List<MovieDto> getRecommendation(int id);
	public void addItem(List<RecommendedItem> items,int userID);
	public void test(int id) throws Exception;
	public void evaluateUB(int n) throws Exception;
	public void improveUser(int n) throws Exception;
	public void evaluateIB(int n) throws Exception;
}
