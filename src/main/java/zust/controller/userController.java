package zust.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import zust.dto.Json;
import zust.dto.UserDto;
import zust.service.PosterServiceI;
import zust.service.RecommendServiceI;
import zust.service.UserServiceI;
import zust.util.MD5Util;

@Controller
@RequestMapping("/userController")
public class userController {
	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	PosterServiceI posterService;
	@Autowired
	RecommendServiceI recommendService;
	@Autowired
	UserServiceI userService;
	@Autowired
	HttpSession session;
	
	@RequestMapping("/hello")
	public ModelAndView hello(){
		List<String> link = posterService.test();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("hello");
		mv.addObject("link", link);
		
		return mv;
	}
	
	@RequestMapping("/recommend")
	@ResponseBody
	public String recommend(long userId,int size){
		recommendService.userBasedRecommend(userId, size);
		return "successful";
	}
	
	@ResponseBody
	@RequestMapping(value={"/doRegister"},method=RequestMethod.POST,headers="Accept=*/*",produces = "application/json")	
	public Json register(UserDto u){
		Json j = new Json();
		j = userService.register(u);
		System.out.println(j.isSuccess());
		return j;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(String username,String password){
		UserDto u = userService.login(username, MD5Util.md5(password));
		ModelAndView mv = new ModelAndView();
		if(u != null){
		mv.setViewName("index");
		session.setAttribute("id", u.getId());
		session.setAttribute("username", username);
		return mv;
		}else{
			mv.setViewName("index");
			return mv;
		}
		
		
	}
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/logout")
	public String logout(){
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/login.html";
	}

}
