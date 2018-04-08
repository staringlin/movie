package zust.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import zust.dao.PosterDaoI;
import zust.entity.movie;
import zust.service.PosterServiceI;
import zust.util.HttpUtils;

@Service
public class PosterServiceImpl implements PosterServiceI {
	
	public static int pageNumber = 40;
	@Autowired
	private PosterDaoI posterDao;

	@Override
	public String get(String movieName) {
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
	            String images = subject.getString("images");
	            
	            JSONObject image = JSON.parseObject(images);
	            String poster = image.getString("small");
	            return poster;
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
	public void savePoster() {
		String hql = "from movie";
		List<movie> posters = posterDao.find(hql, pageNumber, 40);
		for(movie one : posters){
			if("false".equals(one.getPoster())){
				String poster = get(one.getName());
				one.setPoster(poster);
				posterDao.update(one);
			}else{
				System.out.println("pagenumber is "+pageNumber+one.getId());
			}
		}
		pageNumber++;
					
	}
	

	public List<String> test(){
		String hql = "from movie";
		List<movie> posters = posterDao.find(hql, 0, 500);
		List<String> link = new ArrayList<String>(){};
		for(movie one : posters){
			link.add(one.getPoster());			
		}
		return link;
	}

}
