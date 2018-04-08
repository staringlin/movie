package zust.service.impl;

import java.util.List;

import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import zust.service.RecommendServiceI;

@Service
public class RecommendService implements RecommendServiceI {

	@Override
	public List<RecommendedItem> userBasedRecommend(long userId, int size) {
		   try {
		        MysqlDataSource dataSource = new MysqlDataSource();
		        dataSource.setServerName("115.159.184.231");
		        dataSource.setUser("test");
		        dataSource.setPassword("test123456");
		        dataSource.setDatabaseName("movie");
		        ConnectionPoolDataSource connectionPool=new ConnectionPoolDataSource(dataSource);
		        JDBCDataModel dm =  new MySQLJDBCDataModel(connectionPool,"movie_preferences", "userID", "movieID","preference",null);;
		        UserSimilarity similarity = new PearsonCorrelationSimilarity(dm);
		        UserNeighborhood neighbor = new NearestNUserNeighborhood(3,similarity, dm);
		        Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(dm, neighbor, similarity));
		        List<RecommendedItem> list = recommender.recommend(userId,size);// recommend
		                                                                 // one item
		                                                                 // to user
		                                                                 // 1
		        for (RecommendedItem ri : list) {
		            System.out.println(ri);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		return null;
	}

}
