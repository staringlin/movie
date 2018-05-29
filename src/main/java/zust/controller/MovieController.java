package zust.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import zust.dao.MoviePreferenceDaoI;
import zust.dto.Json;
import zust.dto.MovieDto;
import zust.dto.UserDto;
import zust.service.MovieServiceI;

@Controller
@RequestMapping("/movieController")
public class MovieController {
	
	@Autowired
	MovieServiceI movieService;	
	@Autowired
	HttpSession session;
	
	/*
	 * 进行电影搜索
	 */
	@RequestMapping("/search")
	public ModelAndView search(String movieName){
		ModelAndView mv = new ModelAndView();
		List<MovieDto> data = movieService.search(movieName);
		mv.setViewName("index");
		mv.addObject("featuredMovies", data);
		return mv;
	}
	
	/*
	 * 得到某种类型电影
	 */
	@RequestMapping("/getTypedMovie")
	public ModelAndView getTypedMovie(String type,@RequestParam(value="currentPage",required=true,defaultValue="1")String currentPage){
		ModelAndView mv = new ModelAndView();
		List<MovieDto> data = movieService.getTypedMovie(type,currentPage);//显示哪页数据
		int pageCount = 0;
		if(session.getAttribute(type) == null){
			pageCount = movieService.getTypedCount(type);
			session.setAttribute(type, pageCount);
		}else{
			pageCount = (int) session.getAttribute(type);
		}
		mv.setViewName("movie");
		mv.addObject("TypedMovies", data);
		mv.addObject("currentPage",currentPage);
		mv.addObject("type",type);
		mv.addObject("pageCount",pageCount);
		return mv;
	}
	/*
	 * 给电影评分
	 */
	@ResponseBody
	@RequestMapping(value={"/doRate"},method=RequestMethod.POST,headers="Accept=*/*",produces = "application/json")	
	public Json register(int userID,int movieID,int preference){
		System.out.println(userID);
		System.out.println(movieID);
		System.out.println(preference);
		Json j = movieService.addPreference(userID,movieID,preference);
		return j;
	}
	
	
	@RequestMapping("/getTypedMovie2")
	public ModelAndView getTypedMovie2(String type,@RequestParam(value="currentPage",required=true,defaultValue="0")String currentPage){
		ModelAndView mv = new ModelAndView();
		System.out.println("当前页数："+currentPage);
		List<MovieDto> data = movieService.getTypedMovie(type,currentPage);//显示哪页数据
		mv.setViewName("movie2");
		mv.addObject("TypedMovies", data);
		mv.addObject("currentPage",currentPage);
		mv.addObject("type",type);
		mv.addObject("pageCount",6);
		return mv;
	}
	
}
