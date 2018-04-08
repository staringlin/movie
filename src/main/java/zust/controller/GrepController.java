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
import org.springframework.web.bind.annotation.ResponseBody;

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
	

	@RequestMapping("/getPoster")
	@ResponseBody
	public String getPoster() throws IOException{
		return posterService.get("Toy Story");
		
	}
	
	
	@RequestMapping("/savePoster")
	@ResponseBody
	public String savePoster() throws IOException{
		posterService.savePoster();
		return "hello";
		
	}

}
