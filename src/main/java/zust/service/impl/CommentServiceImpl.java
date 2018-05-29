package zust.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zust.dao.CommentDaoI;
import zust.dao.UserDaoI;
import zust.dto.CommentDto;
import zust.dto.Json;
import zust.dto.MovieDto;
import zust.entity.MovieComment;
import zust.entity.User;
import zust.entity.movie;
import zust.service.CommentServiceI;

@Service
public class CommentServiceImpl implements CommentServiceI {

	
	@Autowired
	CommentDaoI commentDao;
	@Autowired
	UserDaoI userDao;
	@Override
	public List<MovieComment> getComment(MovieDto dto) {
			String hql = "from MovieComment c where c.movieID = :id order by createtime desc";
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("id", dto.getId());
			List<MovieComment> comment = commentDao.find(hql, params);
			return comment;
	}
	@Override
	public void add(CommentDto dto) {
		
			MovieComment o  = new MovieComment();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("id", dto.getOwnerID());
			User u = userDao.get("from User u where u.id = :id", params);
			o.setCommentType(dto.getCommentType());
			o.setCreatetime(System.currentTimeMillis()/1000);
			o.setUser(u);
			o.setMovieID(dto.getMovieID());
			o.setContent(dto.getContent());
			commentDao.save(o);
	}
	@Override
	public List<MovieComment> getComments(String currentPage,int rows) {
		String hql = "from MovieComment order by createtime desc";
		List<MovieComment> comments = commentDao.find(hql,Integer.parseInt(currentPage),rows);
		return comments;
	}
	@Override
	public int getCount(int rows) {
		Long count = commentDao.count("select count(*) from MovieComment");
		int pageCount = count.intValue()%rows == 0 ? count.intValue()/rows:count.intValue()/rows+1;
		return pageCount;
	}
	@Override
	public Json delete(int id) {
		Json j = new Json();
		String hql = " from MovieComment m where m.id = :id ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		MovieComment m = commentDao.get(hql, params);
		if(null != m){
			commentDao.delete(m);
			j.setSuccess(true);
		}else{
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
	}

}
