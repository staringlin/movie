package zust.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.example.grouplens.GroupLensDataModel;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.LoadEvaluator;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.ClusterSimilarity;
import org.apache.mahout.cf.taste.impl.recommender.FarthestNeighborClusterSimilarity;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.TreeClusteringRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import model.MyDataModel;
import zust.dao.MovieDaoI;
import zust.dao.MovieRecommendDaoI;
import zust.dto.MovieDto;
import zust.entity.MovieRecommend;
import zust.entity.movie;
import zust.service.RecommendServiceI;

@Service
public class RecommendService implements RecommendServiceI {
	
	
	@Autowired
	MovieRecommendDaoI movieRecommendDao;
	@Autowired
	MovieDaoI movieDao;

	@Override
	public List<RecommendedItem> userBasedRecommend(long userId, int size) {
		List<RecommendedItem> list = null;
		   try {

			    DataModel model = MyDataModel.myDataModel();
		        UserSimilarity similarity = new PearsonCorrelationSimilarity(model, Weighting.WEIGHTED);
		        UserNeighborhood neighbor = new NearestNUserNeighborhood(3,similarity, model);
		        Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(model, neighbor, similarity));
		        list = recommender.recommend(userId,size);// recommend
		        for(RecommendedItem i : list){
		        	System.out.println(i);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		return list;
	}

	
	@Override
	public List<MovieDto> getRecommendation(int userId) {
		String hql = "from MovieRecommend where userId = :userId";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		List<MovieRecommend> recommendations = movieRecommendDao.find(hql, params);
		List<MovieDto> movies = new ArrayList<MovieDto>();
		
		if(recommendations != null & recommendations.size()>0){
			
			List<Integer> ids = new ArrayList<Integer>();
			for(MovieRecommend one : recommendations){
				ids.add(one.getMovieID());
			}
			hql = "from movie m where m.id in (:ids)";
			List<movie> data = movieDao.find(hql, ids, 0, 8);
			for(movie one : data){
				MovieDto temp = new MovieDto();
				temp.setId(one.getId());
				temp.setName(one.getName());
				temp.setPoster(one.getPoster());
				temp.setType(one.getType());
				temp.setPublished_year(one.getPublished_year());
				movies.add(temp);
				
			}
		}
		return movies;
	}


	/*
	 *  将推荐的电影保存到数据库
	 * @see zust.service.RecommendServiceI#addItem(java.util.List, int)
	 */
	@Override
	public void addItem(List<RecommendedItem> items,int userID) {
		if(items != null && items.size()>0){
			for (RecommendedItem item : items) {
				MovieRecommend one = new MovieRecommend();
				one.setUserId(userID);
				one.setMovieID((int) item.getItemID());
				movieRecommendDao.save(one);
			}
		}
	}
	
	
	public void evaluateUB(int n) throws Exception  {
		RandomUtils.useTestSeed();
	    DataModel model = new GroupLensDataModel(new File("C:/Users/star/Desktop/CollaborativeFilteringMovieRecommender-master/WebRoot/movielens/ratings.dat"));
	    //计算推荐程序所给出的估计值与实际值的平均绝对偏差
	    RecommenderEvaluator evaluator =
	    	      new AverageAbsoluteDifferenceRecommenderEvaluator();
	    	    RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
	    	      @Override
	    	      public Recommender buildRecommender(DataModel model) throws TasteException {
	    	        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
	    	        UserNeighborhood neighborhood =
	    	          new NearestNUserNeighborhood(n, similarity, model);
	    	        return new GenericUserBasedRecommender(model, neighborhood, similarity);
	    	      }
	    	    };
	    	    double score = evaluator.evaluate(recommenderBuilder, null, model, 0.95, 0.05);
	    	    System.out.println("当n="+n+": 平均绝对差值为"+score);
	}

	public void improveUser(int n) throws Exception {
		RandomUtils.useTestSeed();
	    DataModel model = new GroupLensDataModel(new File("C:/Users/star/Desktop/CollaborativeFilteringMovieRecommender-master/WebRoot/movielens/ratings.dat"));
	    //计算推荐程序所给出的估计值与实际值的平均绝对偏差
	    RecommenderEvaluator evaluator =
	    	      new AverageAbsoluteDifferenceRecommenderEvaluator();
	    	    RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
	    	      @Override
	    	      public Recommender buildRecommender(DataModel model) throws TasteException {
	    	        UserSimilarity similarity = new PearsonCorrelationSimilarity(model, Weighting.WEIGHTED);
	    	        UserNeighborhood neighborhood =
	    	          new NearestNUserNeighborhood(n, similarity, model);
	    	        return new GenericUserBasedRecommender(model, neighborhood, similarity);
	    	      }
	    	    };
	    	    double score = evaluator.evaluate(recommenderBuilder, null, model, 0.95, 0.05);
	    	    System.out.println("当n="+n+": 平均绝对差值为"+score);
	}
	
	public void evaluateIB(int n) throws Exception{
		RandomUtils.useTestSeed();
	    DataModel model = new GroupLensDataModel(new File("C:/Users/star/Desktop/CollaborativeFilteringMovieRecommender-master/WebRoot/movielens/ratings.dat"));
	    //计算推荐程序所给出的估计值与实际值的平均绝对偏差
	    RecommenderEvaluator evaluator =
	    	      new AverageAbsoluteDifferenceRecommenderEvaluator();
	    	    RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
	    	      @Override
	    	      public Recommender buildRecommender(DataModel model) throws TasteException {
	    	        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
	    	        return new GenericItemBasedRecommender(model, similarity);
	    	      }
	    	    };
	    	    double score = evaluator.evaluate(recommenderBuilder, null, model, 0.95, 0.05);
	    	    System.out.println("当n="+n+": 平均绝对差值为"+score);
	}


	@Override
	public void test(int id) throws Exception {
	    DataModel model = new GroupLensDataModel(new File("C:/Users/star/Desktop/CollaborativeFilteringMovieRecommender-master/WebRoot/movielens/ratings.dat"));

		UserSimilarity similarity = new LogLikelihoodSimilarity(model);
		ClusterSimilarity clusterSimilarity = new FarthestNeighborClusterSimilarity(similarity);
		Recommender recommender = new TreeClusteringRecommender(model, clusterSimilarity, 20);

	    	    
		List<RecommendedItem> list = recommender.recommend(id, 10);// recommend
		for (RecommendedItem i : list) {
			System.out.println(i);
		}
	}
	
}
