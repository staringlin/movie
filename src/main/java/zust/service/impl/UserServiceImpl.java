package zust.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zust.dao.UserDaoI;
import zust.dto.Json;
import zust.dto.UserDto;
import zust.entity.User;
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
		// TODO Auto-generated method stub
	}

}
