package zust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zust.dao.MovieDaoI;
import zust.service.MovieServiceI;

@Service
public class MovieServiceImpl implements MovieServiceI {

	@Autowired
	MovieDaoI movieDao;
	
	@Override
	public void getFeaturedMoview() {
		
	}
	

}
