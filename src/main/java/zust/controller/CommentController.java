package zust.controller;

import java.beans.Beans;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import zust.dto.CommentDto;
import zust.dto.Json;
import zust.dto.MovieDto;
import zust.entity.MovieComment;
import zust.service.CommentServiceI;
import zust.service.MovieServiceI;

@Controller
@RequestMapping("/commentController")
public class CommentController {

	
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	MovieServiceI movieService;
	@Autowired
	CommentServiceI commentService;
	
	@ResponseBody
	@RequestMapping(value={"/getComment"},method=RequestMethod.POST,headers="Accept=*/*",produces = "application/json")		
	public Json getComment(MovieDto dto){
		commentService.getComment(dto);
		Json j = new Json();
		j.setSuccess(true);
		return j;
	}
	
	
	@RequestMapping("/review")
	@ResponseBody
	public ModelAndView review(CommentDto dto){
		commentService.add(dto);
		MovieDto movie = new MovieDto();
		BeanUtils.copyProperties(dto,movie);
		movie.setId(dto.getMovieID());
		movie.setName(dto.getMovieName());
		String rating = movieService.getRating(movie.getId());
		List<MovieComment> comments = commentService.getComment(movie);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("single");
		mv.addObject("movie", movie);
		mv.addObject("rating", rating);	
		mv.addObject("comments",comments);
		
		return mv;
	}
	
}
