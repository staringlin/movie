package zust.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import zust.dao.MovieDaoI;
import zust.dao.MoviePreferenceDaoI;
import zust.dto.Json;
import zust.dto.MovieDto;
import zust.dto.UserDto;
import zust.entity.MoviePreference;
import zust.entity.User;
import zust.entity.movie;
import zust.service.MovieServiceI;
import zust.util.HttpUtils;

@Service
public class MovieServiceImpl implements MovieServiceI {

	@Autowired
	MovieDaoI movieDao;
	@Autowired
	MoviePreferenceDaoI moviePreferenceDao;
	
	/*
	 * (获得特辑电影
	 * @see zust.service.MovieServiceI#getFeaturedMoview()
	 */
	@Override
	public List<MovieDto> getFeaturedMoview() {
		String hql = "from movie order by id DESC";
		List<movie> data = movieDao.find(hql, 0, 8);
		List<MovieDto> movies = new ArrayList<MovieDto>();
		for(movie one : data){
			MovieDto temp = new MovieDto();
			temp.setComments(one.getPreferences().size());
			temp.setId(one.getId());
			temp.setName(one.getName());
			temp.setPoster(one.getPoster());
			temp.setType(one.getType());
			temp.setPublished_year(one.getPublished_year());
			movies.add(temp);
			
		}
		return movies;
		
	}

	@Override
	public String getRating(int id) {
		String hql = "from movie m where m.id = :id";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		movie one = movieDao.get(hql, params);
		double maxScore = 0;
		if(null == one.getPreferences()  || one.getPreferences().size() == 0){
			return "0.0";
		}
		
		for(MoviePreference pre : one.getPreferences()){
			maxScore += pre.getPreference();
		}
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(maxScore/one.getPreferences().size());
	}

	@Override
	public String getIntroduction(String movieName) {
		String DoubanId = getDoubanId(movieName);
		String url = "http://api.douban.com///v2/movie/subject/";
		HttpResponse response;
		try {
			response = HttpUtils.getRawHtml(url+URLEncoder.encode(DoubanId,"utf-8"));
	        //获取响应状态码
	        int StatusCode = response.getStatusLine().getStatusCode();
	        //如果状态响应码为200，则获取html实体内容或者json文件
	        if(StatusCode == 200){
	            String entity = EntityUtils.toString (response.getEntity(),"utf-8");                
	            EntityUtils.consume(response.getEntity());
	            
	            //Json解析
	            JSONObject jsonObject = JSON.parseObject(entity);	           
	            String summary= jsonObject.getString("summary");
	            return summary;
	            
	        }else {
	            //否则，消耗掉实体
	            EntityUtils.consume(response.getEntity());
	        }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		return "false";
	}
	

	@Override
	public String getDoubanId(String movieName) {
		String url = "http://api.douban.com//v2/movie/search?q=";
		HttpResponse response;
		try {
			response = HttpUtils.getRawHtml(url+URLEncoder.encode(movieName,"utf-8"));
	        //获取响应状态码
	        int StatusCode = response.getStatusLine().getStatusCode();
	        //如果状态响应码为200，则获取html实体内容或者json文件
	        if(StatusCode == 200){
	            String entity = EntityUtils.toString (response.getEntity(),"utf-8");                
	            EntityUtils.consume(response.getEntity());
	            
	            //Json解析
	            JSONObject jsonObject = JSON.parseObject(entity);
	            String subjects= jsonObject.getString("subjects");
	            
	            JSONArray jsonArray = JSONArray.parseArray(subjects);
	            if(jsonArray.size() > 0 ){
	            JSONObject subject = JSON.parseObject(jsonArray.get(0).toString());
	            String id = subject.getString("id");
	            return id;
	            }else{
	            	return "not found";
	            }

	            
	        }else {
	            //否则，消耗掉实体
	            EntityUtils.consume(response.getEntity());
	        }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		return "false";
	}

	@Override
	public List<MovieDto> search(String movieName) {
		String hql = "from movie m where m.name like :name";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", "%"+movieName+"%");
		List<movie> data = movieDao.find(hql, params);
		List<MovieDto> movies = new ArrayList<MovieDto>();
		for(movie one : data){
			MovieDto temp = new MovieDto();
			temp.setComments(one.getPreferences().size());
			temp.setId(one.getId());
			temp.setName(one.getName());
			temp.setPoster(one.getPoster());
			temp.setType(one.getType());
			temp.setPublished_year(one.getPublished_year());
			movies.add(temp);
			
		}
		return movies;
	}

	@Override
	public List<MovieDto> getTypedMovie(String type,String currentPage) {
		String hql = "from movie m where m.type like :type";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", "%"+type+"%");
		List<movie> data = movieDao.find(hql, params, Integer.parseInt(currentPage), 12);
		List<MovieDto> movies = new ArrayList<MovieDto>();
		for(movie one : data){
			MovieDto temp = new MovieDto();
			temp.setComments(one.getPreferences().size());
			temp.setId(one.getId());
			temp.setName(one.getName());
			temp.setPoster(one.getPoster());
			temp.setType(one.getType());
			temp.setPublished_year(one.getPublished_year());
			movies.add(temp);
			
		}
		return movies;
	}


	@Override
	public Json addPreference(int userID, int movieID, int preference) {
		Json j = new Json();
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("movieID", movieID);
		params.put("userID", userID);
		String hql = "from moviePreference m where m.userID = :userID and m.movieID = :movieID";
		String sql = "select * from movie_preferences m where m.userID = :userID and m.movieID = :movieID";
		List<Object[]> m = moviePreferenceDao.findBySql(sql, params);
		if(m != null && m.size() == 1){
			sql = "update movie_preferences m  set m.preference = :preference where m.userID = :userID and m.movieID = :movieID";
			params.put("preference", preference);
			moviePreferenceDao.executeSql(sql,params);
			j.setMsg("评分更新");
		}else{
			sql = "insert into movie_preferences(userID,movieID,preference,timestamp) values(:userID,:movieID,:preference,:timestamp)";
			params.put("preference", preference);
			params.put("timestamp", System.currentTimeMillis()/1000);
			moviePreferenceDao.executeSql(sql,params);
			j.setMsg("第一次给分");
		}

		j.setSuccess(true);
		return j;
	}

	/*
	 * 12为一页
	 * @see zust.service.MovieServiceI#getTypedCount(java.lang.String)
	 */
	@Override
	public int getTypedCount(String type) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("type", "%"+type+"%");
		int count = movieDao.count("select count(*) from movie m where m.type like :type", params).intValue();
		return count/12;
	}

	@Override
	public List<MovieDto> getReviewedMovie(int userID) {
		String hql = "from MoviePreference m where m.userID = :userID order by timestamp desc";
		Map<String , Object> params = new HashMap<String,Object>();
		params.put("userID", userID);
		List<MoviePreference> data = moviePreferenceDao.find(hql,params,0,4);
		List<MovieDto> movies = new ArrayList<MovieDto>();
		for(MoviePreference pre : data){
			movie one = pre.getMovie();
			MovieDto temp = new MovieDto();
			temp.setId(one.getId());
			temp.setName(one.getName());
			temp.setPoster(one.getPoster());
			temp.setType(one.getType());
			temp.setPublished_year(one.getPublished_year());
			movies.add(temp);
			
		}
		return movies;
	}

	@Override
	public List<MovieDto> getMovies(String currentPage,int rows) {
		String hql = "from movie ";
		List<movie> data = movieDao.find(hql,Integer.parseInt(currentPage), rows);
		List<MovieDto> movies = new ArrayList<MovieDto>();
		for(movie one : data){
			MovieDto temp = new MovieDto();
			temp.setId(one.getId());
			temp.setName(one.getName());
			temp.setPoster(one.getPoster());
			temp.setType(one.getType());
			temp.setPublished_year(one.getPublished_year());
			movies.add(temp);
			
		}
		return movies;
	}

	@Override
	public int getCount(int rows) {
		Long count = movieDao.count("select count(*) from movie");
		int pageCount = count.intValue()%rows == 0 ? count.intValue()/rows:count.intValue()/rows+1;
		return pageCount;
	}

	
	@Override
	public Json modifyMovie(MovieDto m) {

			Json j = new Json();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", m.getId());
			movie t = movieDao.get("from movie t where t.id = :id ", params);
			if(t != null){
				t.setName(m.getName());
				t.setType(m.getType());
				t.setPublished_year(m.getPublished_year());
				j.setSuccess(true);
				movieDao.update(t);
			}else{
				j.setSuccess(false);
				j.setMsg("修改失败");
			}
			return j;
	}

	@Override
	public Json delete(int id) {
		Json j = new Json();
		String hql = " from movie m where m.id = :id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		movie m = movieDao.get(hql, params);
		if(null != m){
			movieDao.delete(m);
			j.setSuccess(true);
		}else{
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
	}

	@Override
	public void addMovie(String newFileName, MovieDto dto) {
		movie one = new movie();
		one.setName(dto.getName());
		one.setPoster(newFileName);
		one.setPublished_year(dto.getPublished_year());
		one.setType(dto.getType());
		movieDao.save(one);
		
	}
	
}
