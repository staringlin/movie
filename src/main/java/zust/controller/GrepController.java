package zust.controller;

import java.io.IOException;
import java.net.URLEncoder;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import zust.dto.Json;
import zust.dto.UserDto;
import zust.service.MovieServiceI;
import zust.service.PosterServiceI;
import zust.util.HttpUtils;


/**
 * @author star
 *
 */

@Controller
@RequestMapping("/grep")
public class GrepController {
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	PosterServiceI posterService;
	@Autowired
	MovieServiceI movieService;
	
	/*
	 * 得到电影图片
	 */
	@RequestMapping("/getPoster")
	@ResponseBody
	public String getPoster() throws IOException{
		return posterService.get("Toy Story");
		
	}
	
	/*
	 * 保存电影图片链接到数据库
	 */	
	@RequestMapping("/savePoster")
	@ResponseBody
	public String savePoster() throws IOException{
		posterService.savePoster();
		return "hello";
		
	}

	
	/*
	 * 从豆瓣得到电影简介
	 */	
	@ResponseBody
	@RequestMapping(value={"/getIntroduction"},method=RequestMethod.POST,headers="Accept=*/*",produces = "application/json")	
	public Json register(String movieName){
		Json j = new Json();
		String intorduction = movieService.getIntroduction(movieName);
		if(!"false".equals(intorduction)){
			j.setMsg(intorduction);
			j.setSuccess(true);
		}
		return j;
	}
	

}
