package zust.service;

import java.util.List;

import zust.dto.SlideDto;

public interface SlideServiceI {

	public List<SlideDto> getSlide();
	public void addSlide(String name ,String path);
	public void modifySlide(String name, int id);
	public String deleteSlide(int id);
}
