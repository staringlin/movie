package zust.task;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import zust.service.PosterServiceI;

@Component
@Lazy(value=false)
public class MyTask {
	@Autowired PosterServiceI posterService;
	
//	@Scheduled(fixedRate = 9000) 
//	public void test(){
//		posterService.savePoster();
//		Date now = new Date();
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//		System.out.println("正在每隔9秒爬取链接。。。。"+sf.format(now));
//	}	

}
