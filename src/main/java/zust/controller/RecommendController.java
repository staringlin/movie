package zust.controller;

import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import zust.dto.MovieDto;
import zust.service.MovieServiceI;
import zust.service.RecommendServiceI;

@Controller
@RequestMapping("/recommendController")
public class RecommendController {

	@Autowired
	MovieServiceI movieService;
	@Autowired
	RecommendServiceI recommendService;
	
	
	@RequestMapping("/getRecommendation")
	@ResponseBody
	/*	得到推荐电影
	 * @Param id 用户id 若是游客默认为0
	 */
	public ModelAndView getRecommendation(@RequestParam(value="id",required=true,defaultValue="0")int id){
		ModelAndView mv = new ModelAndView();
		List<MovieDto> data = null;
		List<MovieDto> reviewedMovies = null;
		if(id != 0){
			data = recommendService.getRecommendation(id);
			reviewedMovies = movieService.getReviewedMovie(id);
		}else{
			data = movieService.getFeaturedMoview();//游客显示自定义推荐电影
		}
		
		mv.setViewName("recommend");
		mv.addObject("MovieRecommendatinos", data);
		mv.addObject("reviewedMovies", reviewedMovies);
		return mv;
	}
	
	/*
	 * 为某个用户进行推荐
	 */
	@RequestMapping("/recommend")
	@ResponseBody
	public String recommend(int id){
		List<RecommendedItem> items = recommendService.userBasedRecommend(id, 10);
		recommendService.addItem(items,id);
		return "successful";
	}
	
	/*
	 * 基于用户CF算法评估
	 */
	@RequestMapping("/evaluateUB")
	@ResponseBody
	public String evaluateUB(int n){
		try {
			while(n < 129){
				recommendService.evaluateUB(n);
				n *=2 ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "successful";
	}
	
	
	/*
	 * 为基于项目CF算法评估
	 */
	@RequestMapping("/evaluateIB")
	@ResponseBody
	public String evaluateIB(int n){
		try {
			while(n < 129){
				recommendService.evaluateIB(n);
				n *=2 ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "successful";
	}
	
	/*
	 * 基于用户CF加权重算法评估
	 */
	@RequestMapping("/improveUser")
	@ResponseBody
	public String improveUser(int n){
		try {
			while(n < 129){
				recommendService.improveUser(n);
				n *=2 ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "successful";
	}
	
	/*
	 * 基于聚类用户CF加权重算法评估
	 */
	@RequestMapping("/test")
	@ResponseBody
	public String test(int id) throws Exception{
		recommendService.test(id);
		return "successful";
	}
	
}
