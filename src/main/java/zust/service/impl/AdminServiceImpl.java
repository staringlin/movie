package zust.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zust.dao.AdminDaoI;
import zust.dto.AdminDto;
import zust.dto.UserDto;
import zust.entity.Admin;
import zust.service.AdminServiceI;
import zust.util.MD5Util;
@Service
public class AdminServiceImpl implements AdminServiceI{

	@Autowired
	AdminDaoI adminDao;
	@Override
	public AdminDto login(String username, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		zust.entity.Admin t = adminDao.get("from Admin t where t.username = :username and t.password = :password", params);
		if (t != null) {			
			AdminDto u = new AdminDto();
			u.setUsername(username);
			u.setId(t.getId());
			return u;
		}
		return null;
	}
	@Override
	public String modifyAdmin(int id ,String username, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Admin ad = adminDao.get("from Admin t where t.id = :id", params);
		if(ad != null){
			ad.setUsername(username);
			ad.setPassword(MD5Util.md5(password));
			adminDao.update(ad);
			return "修改成功";
		}
		return "修改失败";
	}


	

}
