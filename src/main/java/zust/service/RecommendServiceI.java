package zust.service;

import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;



public interface RecommendServiceI {

	public List<RecommendedItem> userBasedRecommend(long userId ,int size);
}
