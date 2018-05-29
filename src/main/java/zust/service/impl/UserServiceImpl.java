package zust.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zust.dao.UserDaoI;
import zust.dto.Json;
import zust.dto.MovieDto;
import zust.dto.UserDto;
import zust.entity.User;
import zust.entity.movie;
import zust.service.UserServiceI;
import zust.util.MD5Util;
@Service
public class UserServiceImpl<DtoUser> implements UserServiceI{

	@Autowired
	UserDaoI userDao;
	public Json register(UserDto u) {
		// TODO Auto-generated method stub
		Json j = new Json();
		

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", u.getUsername());
		if (userDao.count("select count(*) from User c where c.username = :username", params) > 0) {
			j.setSuccess(false);
			j.setMsg("用户名已经存在!");
			return j;
		}else{
		
			String password = MD5Util.md5(u.getPassword());
			User one = new User();
			BeanUtils.copyProperties(u, one);
			one.setPassword(password);
			userDao.save(one);
			j.setMsg("注册成功");
			j.setSuccess(true);
			
			return j;
		}
	}
	public UserDto login(String username,String password) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		User t = userDao.get("from User t where t.username = :username and t.password = :password", params);
		if (t != null) {			
			UserDto u = new UserDto();
			u.setUsername(username);
			u.setId(t.getId());
			return u;
		}
		return null;
	}

	
	@Override
	public void delete(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		User t = userDao.get("from User t where t.id = :id ", params);
		userDao.delete(t);
	}
	@Override
	public List<UserDto> getUsers(String currentPage) {
		String hql = "from User u ";
		List<User> data = userDao.find(hql,Integer.parseInt(currentPage), 4);
		List<UserDto> users = new ArrayList<UserDto>();
		for(User one : data){
			UserDto temp = new UserDto();
			temp.setEmail(one.getEmail());
			temp.setId(one.getId());
			temp.setUsername(one.getUsername());
			temp.setAvatar(one.getAvatar());
			users.add(temp);			
		}
		return users;
	}
	@Override
	public int getCount() {
		Long count = userDao.count("select count(*) from User");
		int pageCount = count.intValue()%4 == 0 ? count.intValue()/4:count.intValue()/4+1;
		return pageCount;
	}
	@Override
	public Json modifyUser(UserDto u) {
		Json j = new Json();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", u.getId());
		User t = userDao.get("from User t where t.id = :id ", params);
		if(t != null){
			System.out.println(u.getId());
			System.out.println(u.getEmail());
			System.out.println(u.getUsername());
		t.setEmail(u.getEmail());
		t.setUsername(u.getUsername());
		j.setSuccess(true);
		userDao.update(t);
		}else{
			j.setSuccess(false);
			j.setMsg("修改失败");
		}

		return j;
	}
	@Override
	public List<UserDto> search(String username) {
		String hql = "from User u where u.username like :username or u.email like :username";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("username", "%"+username+"%");
		List<User> data = userDao.find(hql, params);
		List<UserDto> users = new ArrayList<UserDto>();
		for(User one : data){
			UserDto temp = new UserDto();
			temp.setEmail(one.getEmail());
			temp.setId(one.getId());
			temp.setUsername(one.getUsername());
			temp.setAvatar(one.getAvatar());
			users.add(temp);
			
		}
		return users;
	}

}
