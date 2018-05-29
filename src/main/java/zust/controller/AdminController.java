package zust.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import zust.dto.AdminDto;
import zust.dto.Json;
import zust.dto.MovieDto;
import zust.dto.SlideDto;
import zust.dto.UserDto;
import zust.entity.MovieComment;
import zust.service.AdminServiceI;
import zust.service.CommentServiceI;
import zust.service.MovieServiceI;
import zust.service.SlideServiceI;
import zust.service.UserServiceI;
import zust.util.MD5Util;

@Controller
@RequestMapping("/adminController")
public class AdminController {
	
	@Autowired
	AdminServiceI adminService;
	@Autowired
	SlideServiceI slideService;
	@Autowired
	UserServiceI userService;
	@Autowired
	MovieServiceI movieService;
	@Autowired
	CommentServiceI commentService;
	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;

	@RequestMapping("/login")
	@ResponseBody
	public Json login(String username,String password){
		Json j = new Json();
		AdminDto u = adminService.login(username, MD5Util.md5(password));
		ModelAndView mv = new ModelAndView();
		if(u != null){
		j.setSuccess(true);
		session.setAttribute("adminId", u.getId());
		session.setAttribute("adminName", username);
		return j;
		}else{
			j.setSuccess(false);
			j.setMsg("账号或密码错误");
			return j;
		}				
	}
	
	/*
	 * 后台首页
	 */
	@RequestMapping("/index")
	public String index(){
		return "admin/index";
	}
	
	@RequestMapping("/getAdmin")
	public String getAdmin(){
		
		return "admin/adminMessage";
	}
	
	
	/*
	 * 修改管理员信息
	 */
	@RequestMapping("/modifyAdmin")
	public ModelAndView modifyAdmin(int id,String username,String password){
		ModelAndView mv = new ModelAndView();
		String message = adminService.modifyAdmin(id, username, password);
		mv.setViewName("admin/adminMessage");
		mv.addObject("message", message);
		return mv;
	}
	
	@RequestMapping("/getSlide")
	public ModelAndView getSlide(){
		ModelAndView mv = new ModelAndView();
		List<SlideDto> slides = slideService.getSlide();
		mv.setViewName("admin/slide");
		mv.addObject("slides", slides);
		return mv;
	}
	
	/*
	 * 返回添加轮播图页面
	 */
	@RequestMapping("/addImg")
	public String addImg(){
		return "admin/addImg";
	}
	/*
	 * 返回修改轮播图页面
	 */
	@RequestMapping("/modifyImg")
	public ModelAndView modifyImg(String name,String path,int id){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/modifyImg");
		mv.addObject("name", name);
		mv.addObject("path", path);
		mv.addObject("imgId", id);
		return mv;
	}
	
	/*
	 * 添加轮播图
	 */
	@RequestMapping("/addSlide")
	public ModelAndView addSlide(MultipartFile myfile ,String name) throws IllegalStateException, IOException{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/addImg");
		String originalFileName = myfile.getOriginalFilename();
		if(myfile != null && originalFileName !=null && originalFileName.length() > 0){
			String picPath = session.getServletContext().getRealPath("/slide/");
			String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
			File newFile = new File(picPath+newFileName);
			myfile.transferTo(newFile);
			mv.addObject("message","添加成功");
			slideService.addSlide(name, "/slide/"+newFileName);
		}
		return mv;
	}
	
	/*
	 * 修改轮播图
	 */
	@RequestMapping("/modifySlide")
	public ModelAndView modifySlide(String name,int id){
		slideService.modifySlide(name,id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/modifyImg");
		mv.addObject("message", "修改成功");
		return mv;
	}
	
	/*
	 * 删除轮播图
	 */
	@RequestMapping("/deleteSlide")
	public ModelAndView deleteSlide(int id){
		String path = slideService.deleteSlide(id);
		File file = new File(session.getServletContext().getRealPath(path));
		if(file.exists()){
			boolean b = file.delete();
			System.out.println("删除了"+session.getServletContext().getRealPath(path)+b);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminController/getSlide");
		return mv;
	}
	
	/*
	 * 得到用户列表
	 */
	@RequestMapping("/getUsers")
	public ModelAndView getUsers(@RequestParam(value="currentPage",required=true,defaultValue="1")String currentPage,@RequestParam(value="message",required=false)String message){
		ModelAndView mv = new ModelAndView();
		List<UserDto> data = userService.getUsers(currentPage);//显示哪页数据
		int pageCount = userService.getCount();
		mv.setViewName("admin/userList");
		mv.addObject("users", data);
		mv.addObject("currentPage",currentPage);
		mv.addObject("pageCount",pageCount);
		if(message !=null && !"".equals(message)){
			mv.addObject("message", message);
		}
		return mv;
	}
	/*
	 * 得到修改用户界面
	 */
	@RequestMapping(value={"/modifyUser"},method=RequestMethod.GET)	
	public ModelAndView getModifyUser(UserDto u){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/modifyUser");
		mv.addObject("user", u);
		return mv;
	}
	
	
	/*
	 * 修改用户
	 */ 
	@RequestMapping(value={"/modifyUser"},method=RequestMethod.POST)	
	public ModelAndView modifyUser(UserDto u){
		ModelAndView mv = new ModelAndView();
		Json j = new Json();
		j = userService.modifyUser(u);
		mv.setViewName("redirect:/adminController/getUsers");
		mv.addObject("message", "用户信息修改成功");
		return mv;
	}
	
	
	/*
	 * 删除用户
	 */
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(int id){
		userService.delete(id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/adminController/getUsers");
		mv.addObject("message", "用户删除成功");
		return mv;
	}
	
	/*
	 * 搜索用户
	 */
	@RequestMapping("/searchUser")
	public ModelAndView searchUser(String key){
		List<UserDto> users = userService.search(key);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/userList");
		mv.addObject("users", users);
		mv.addObject("currentPage",1);
		mv.addObject("pageCount",1);
		return mv;
	}
	
	/*
	 * 得到电影列表
	 */
	@RequestMapping("/getMovies")
	public ModelAndView getMovies(@RequestParam(value="currentPage",required=true,defaultValue="1")String currentPage,@RequestParam(value="message",required=false)String message){
		ModelAndView mv = new ModelAndView();
		List<MovieDto> data = movieService.getMovies(currentPage,8);//显示哪页数据,一页显示多少
		int pageCount = movieService.getCount(8);//一页显示8条
		mv.setViewName("admin/movieList");
		mv.addObject("movies", data);
		mv.addObject("currentPage",currentPage);
		mv.addObject("pageCount",pageCount);
		if(message !=null && !"".equals(message)){
			mv.addObject("message", message);
		}
		return mv;
	}
	
	/*
	 * 得到修改电影页面
	 */ 
	@RequestMapping(value={"/modifyMovie"},method=RequestMethod.GET)	
	public ModelAndView modifyMovie(MovieDto m){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/modifyMovie");
		mv.addObject("movie", m);
		return mv;
	}
	
	/*
	 * 修改电影
	 */ 
	@RequestMapping(value={"/modifyMovie"},method=RequestMethod.POST)
	@ResponseBody
	public Json doModifyMovie(MovieDto m){
		ModelAndView mv = new ModelAndView();
		Json j = new Json();
		j = movieService.modifyMovie(m);
		return j;
	}
	
	/*
	 * 删除电影
	 */
	@RequestMapping("/deleteMovie")
	public ModelAndView deleteMovie(int id,int currentPage){
		ModelAndView mv = new ModelAndView();
		Json j = new Json();
		j = movieService.delete(id);
		if(j.isSuccess()){
			mv.setViewName("redirect:/adminController/getMovies?currentPage="+currentPage);
			mv.addObject("message", "电影删除成功");
		}else{
			mv.setViewName("redirect:/adminController/getMovies?currentPage="+currentPage);
			mv.addObject("message", "电影删除失败");
		}
		return mv;
	}
	
	
	/*
	 * 得到添加电影页面
	 */
	@RequestMapping(value={"/addMovie"})
	public ModelAndView addMovie(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/addMovie");
		return mv;
	}
	
	/*
	 * 添加电影
	 */
	@RequestMapping("/doAddMovie")
	public ModelAndView doAddMovie(MultipartFile myfile ,MovieDto dto) throws IllegalStateException, IOException{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/addMovie");
		String originalFileName = myfile.getOriginalFilename();
		if(myfile != null && originalFileName !=null && originalFileName.length() > 0){
			String picPath = session.getServletContext().getRealPath("/poster/");
			String newFileName = UUID.randomUUID()+originalFileName.substring(originalFileName.lastIndexOf("."));
			File newFile = new File(picPath+newFileName);
			myfile.transferTo(newFile);
			mv.addObject("message","添加成功");
			movieService.addMovie("/poster/"+newFileName,dto);
		}
		return mv;
	}
	
	
	/*
	 * 进行电影搜索
	 */
	@RequestMapping("/searchMovie")
	public ModelAndView searchMovie(String movieName){
		ModelAndView mv = new ModelAndView();
		List<MovieDto> data = movieService.search(movieName);
		mv.setViewName("admin/movieList");
		mv.addObject("movies", data);
		mv.addObject("currentPage",1);
		mv.addObject("pageCount",1);
		return mv;
	}
	
	
	/*
	 * 得到评论
	 */	
	@RequestMapping(value={"/getComments"})		
	public ModelAndView getComments(@RequestParam(value="currentPage",required=true,defaultValue="1")String currentPage,@RequestParam(value="message",required=false)String message){
		ModelAndView mv = new ModelAndView();
		List<MovieComment> comments = commentService.getComments(currentPage,8);
		int pageCount = commentService.getCount(8);
		mv.setViewName("admin/commentList");
		mv.addObject("comments", comments);
		mv.addObject("currentPage",currentPage);
		mv.addObject("pageCount",pageCount);
		if(message !=null && !"".equals(message)){
			mv.addObject("message", message);
		}
		return mv;
	}
	
	
	
	
	/*
	 * 删除评论
	 */
	@RequestMapping("/deleteComment")
	public ModelAndView deleteComment(int id,int currentPage){
		ModelAndView mv = new ModelAndView();
		Json j = new Json();
		j = commentService.delete(id);
		if(j.isSuccess()){
			mv.setViewName("redirect:/adminController/getComments?currentPage="+currentPage);
			mv.addObject("message", "评论删除成功");
		}else{
			mv.setViewName("redirect:/adminController/getComments?currentPage="+currentPage);
			mv.addObject("message", "评论删除失败");
		}
		return mv;
	}
	
}
