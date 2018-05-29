package zust.service.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zust.dao.SlideDaoI;
import zust.dto.SlideDto;
import zust.entity.Slide;
import zust.service.SlideServiceI;

@Service
public class SlideServiceImpl implements SlideServiceI{
	
	@Autowired	
	SlideDaoI slideDao;

	@Override
	public List<SlideDto> getSlide() {
		List<Slide> slides = slideDao.find("from Slide s");
		List<SlideDto> dto = new ArrayList<SlideDto>();
		for(Slide s : slides){
			SlideDto target = new SlideDto();
			target.setId(s.getId());
			target.setName(s.getName());
			target.setPath(s.getPath());
			dto.add(target);
		}
		return dto;
	}

	@Override
	public void addSlide(String name, String path) {
		Slide s = new Slide();
		s.setName(name);
		s.setPath(path);
		slideDao.save(s);
	}

	@Override
	public void modifySlide(String name, int id) {
		Map<String ,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		Slide s = slideDao.get("from Slide s where s.id = :id",params);
		s.setName(name);
		slideDao.update(s);
		
	}
	
	@Override
	public String deleteSlide(int id) {
		Map<String ,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		Slide s = slideDao.get("from Slide s where s.id = :id",params);
		String path = s.getPath();
		slideDao.delete(s);
		return path;
		
	}

	

}
