<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Single</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Movie_store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- start plugins -->
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.min.js"></script>
<link href='/css/font.css' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="container">
	<div class="container_wrap">
		<div class="header_top">
		    <div class="col-sm-3 logo"><a href="/userController/index"><img src="/images/logo.png" alt=""/></a></div>
		    <div class="col-sm-6 nav">
					<ul>
						<li><span class="simptip-position-bottom simptip-movable"
							data-tooltip="动画片"><a
								href="/movieController/getTypedMovie?type=Animation"> </a></span></li>
						<li><span class="simptip-position-bottom simptip-movable"
							data-tooltip="动作片"><a
								href="/movieController/getTypedMovie?type=Action"> </a> </span></li>
						<li><span class="simptip-position-bottom simptip-movable"
							data-tooltip="戏剧片"><a
								href="/movieController/getTypedMovie?type=Drama"> </a></span></li>
						<li><span class="simptip-position-bottom simptip-movable"
							data-tooltip="浪漫片"><a
								href="/movieController/getTypedMovie?type=Romance"> </a></span></li>
						<li><span class="simptip-position-bottom simptip-movable"
							data-tooltip="喜剧片"><a
								href="/movieController/getTypedMovie?type=Comedy"> </a></span></li>
						<li><span class="simptip-position-bottom simptip-movable"
							data-tooltip="恐怖片"><a
								href="/movieController/getTypedMovie?type=Thriller"> </a></span></li>
					</ul>
			</div>
			
			<div class="col-sm-3 header_right">
			
				<c:choose>  
				    <c:when test="${username == null}">    <!--如果 -->
					    <ul class="header_right_box"> 
					    	<li><a href="/login.html">登录</a>  |  <a href="/register.html">注册</a></li>
					    </ul>
				    </c:when>  
				    <c:otherwise>  <!--否则 --> 
							   <ul class="header_right_box">
							     <li><img src="/images/p1.png" alt="" weight="36" height="36"/></li>
								 <li><p><a href="/userController/logout">${username} | 退出</a></p></li>
								 <li class="last"><i class="edit"> </i></li>
								 <div class="clearfix"> </div>
							   </ul>
				    </c:otherwise>  
 				</c:choose>			

			</div>
			
			<div class="clearfix"> </div>
	      </div>
	   <div class="content">
      	   <div class="movie_top">
      	         <div class="col-md-9 movie_box">
                        <div class="grid images_3_of_2">
                        	<div class="movie_image">
                                <span class="movie_rating">${rating}</span>
                                <img src="${movie.poster}" class="img-responsive" referrerpolicy ="never" width="100%"/>
                            </div>
                            <div class="movie_rate">
                            	<div class="rating_desc"><p>Your Vote :</p></div>
                            	<form action="" class="sky-form">
							     <fieldset>					
								   <section>
								     <div class="rating">
										<input type="radio" name="stars-rating" id="stars-rating-5" value="5">
										<label for="stars-rating-5"><i class="icon-star"></i></label>
										<input type="radio" name="stars-rating" id="stars-rating-4" value="4">
										<label for="stars-rating-4"><i class="icon-star"></i></label>
										<input type="radio" name="stars-rating" id="stars-rating-3" value="3">
										<label for="stars-rating-3"><i class="icon-star"></i></label>
										<input type="radio" name="stars-rating" id="stars-rating-2" value="2">
										<label for="stars-rating-2"><i class="icon-star"></i></label>
										<input type="radio" name="stars-rating" id="stars-rating-1" value="1">
										<label for="stars-rating-1"><i class="icon-star"></i></label>
									 </div>
								  </section>
							    </fieldset>
						  	   </form>
						  	   <div class="clearfix"> </div>
                            </div>
                        </div>
                        <div class="desc1 span_3_of_2">
                        	<p class="movie_option"><strong>电影名:</strong>${movie.name} </p> 
                        	<p class="movie_option"><strong>类　型: </strong><a href="#">${movie.type}</a>, <a href="#">Fantazy</a></p>
                        	<p class="movie_option"><strong>上映日期: </strong>${movie.published_year}</p>
                            
                            <div class="down_btn"><a class="btn1" href="#" id="rating"><span> </span>评分</a></div>
                         </div>
                        <div class="clearfix"> </div>
                        <p class="m_4" id="introduction">There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet.</p>
		               
		               <!-- 评论模块 -->
				<c:choose>  
				    <c:when test="${id != null}">    <!--如果 -->
		                <form method="post" action="/commentController/review">
							<div class="to">
		                     	<input type="hidden" class="text" name="movieID" value="${movie.id}">
							 	<input type="hidden" class="text" name="ownerID" value="${id}">
							 	<input type="hidden" class="text" name="commentType" value="0">
							 	<input type="hidden" class="text" name="published_year" value="${movie.published_year}">
							 	<input type="hidden" class="text" name="poster" value="${movie.poster}">
							 	<input type="hidden" class="text" name="type" value="${movie.type}">
							 	<input type="hidden" class="text" name="movieName" value="${movie.name}">
							</div>
							<div class="text">
			                   <textarea name="content" value="Message:" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Message';}">Message:</textarea>
			                </div>
			                <div class="form-submit1">
					           <input name="submit" type="submit" id="submit" value="发表评论"><br>
					        </div>
							<div class="clearfix"></div>
                 		</form>
				    </c:when>  

 				</c:choose>			               
		               

                 		
		                <div class="single">
		                <h1>共${fn:length(comments)}评论</h1>
		                <ul class="single_list">
		                
				              <c:forEach var="comment" items="${comments}" varStatus="status"> 
									<jsp:useBean id="dateValue" class="java.util.Date"/>
									<c:set var="extra" value="000" />
									<jsp:setProperty name="dateValue" property="time" value="${comment.createtime}${extra}"/>
									
							        <li>
							            <div class="preview"><a href="#"><img src="${comment.user.avatar}" class="img-responsive" alt=""></a></div>
							            <div class="data">
							                <div class="title">${comment.user.username} /  <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd HH:mm"/>  /  </div>
							                <p>${comment.content}</p>
							            </div>
							            <div class="clearfix"></div>
							        </li>
		                     </c:forEach>			                
			  			</ul>			  			
                      </div>
                      
                      </div>
                      <div class="col-md-3">
                      	<div class="movie_img"><div class="grid_2">
							<img src="/images/pic6.jpg" class="img-responsive" alt="">
							<div class="caption1">
									<ul class="list_5 list_7">
							    		<li><i class="icon5"> </i><p>3,548</p></li>
							    	</ul>
							    	<i class="icon4 icon6 icon7"> </i>
							    	<p class="m_3">Guardians of the Galaxy</p>
							</div>
						    </div>
						   </div>
                      	  <div class="grid_2 col_1">
							<img src="/images/pic2.jpg" class="img-responsive" alt="">
							<div class="caption1">
								<ul class="list_3 list_7">
						    		<li><i class="icon5"> </i><p>3,548</p></li>
						    	</ul>
						    	<i class="icon4 icon7"> </i>
						    	<p class="m_3">Guardians of the Galaxy</p>
							</div>
						   </div>
						    <div class="grid_2 col_1">
							<img src="/images/pic9.jpg" class="img-responsive" alt="">
							<div class="caption1">
								<ul class="list_3 list_7">
						    		<li><i class="icon5"> </i><p>3,548</p></li>
						    	</ul>
						    	<i class="icon4 icon7"> </i>
						    	<p class="m_3">Guardians of the Galaxy</p>
							</div>
						   </div>
		               </div> 
                      <div class="clearfix"> </div>
                  </div>
           </div>
    </div>
</div>




<div class="container"> 
 <footer id="footer">
 	<div id="footer-3d">
		<div class="gp-container">
			<span class="first-widget-bend"> </span>
		</div>		
	</div>
    <div id="footer-widgets" class="gp-footer-larger-first-col">
		<div class="gp-container">
            <div class="footer-widget footer-1">
            	<div class="wpb_wrapper">
					<img src="/images/f_logo.png" alt=""/>
				</div> 
	          <br>
	          <p>It is a long established fact that a reader will be distracted by the readable content of a page.</p>
			  <p class="text">There are many variations of passages of Lorem Ipsum available, but the majority have suffered.</p>
			 </div>
			 <div class="footer_box">
			  <div class="col_1_of_3 span_1_of_3">
					<h3>Categories</h3>
					<ul class="first">
						<li><a href="#">Dance</a></li>
						<li><a href="#">History</a></li>
						<li><a href="#">Specials</a></li>
					</ul>
		     </div>
		     <div class="col_1_of_3 span_1_of_3">
					<h3>Information</h3>
					<ul class="first">
						<li><a href="#">New products</a></li>
						<li><a href="#">top sellers</a></li>
						<li><a href="#">Specials</a></li>
					</ul>
		     </div>
		     <div class="col_1_of_3 span_1_of_3">
					<h3>Follow Us</h3>
					<ul class="first">
						<li><a href="#">Facebook</a></li>
						<li><a href="#">Twitter</a></li>
						<li><a href="#">Youtube</a></li>
					</ul>
					<div class="copy">
				      <p>Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="#">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
			        </div>
		     </div>
		    <div class="clearfix"> </div>
	        </div>
	        <div class="clearfix"> </div>
		</div>
	</div>
  </footer>
</div>		
</body>

<script type="text/javascript">
$(function() {
	var movieName = "${movie.name}"
// 任何需要执行的js特效
	$.ajax({
		   type: "POST",
		   url: "/grep/getIntroduction",
		   dataType : "json",
		   data: {
			   movieName:movieName
		   },
		   success:function(data){
			   if(data.success){
				   $("#introduction").html(data.msg);
			   }else{
				   alert("简介抓取失败");
			   } 
		   },
		   
		   error:   function(err){
			   alerth(err)
		}
		   
		}); 
	
});


$('#rating').click(function(){
	var userID = "${id}";
	var movieID = "${movie.id}";
	if(userID == ''){
		alert("请登录后给分");
		return false;
	}
	var preference = $("input[name='stars-rating']:checked").val();
	$.ajax({
		   type: "POST",
		   url: "/movieController/doRate",
		   dataType : "json",
		   data: {
			   userID:userID,
			   movieID:movieID,
			   preference:preference,
		   },
		   success:function(data){
			   if(data.success){
				   	alert("给分成功"+data.msg);
			   }else{
				   alert("打分失败");
			   } 
		   },
		   
		   error:   function(err){
			   alerth(err)
		}
		   
		}); 
	
	
});
</script>


</html>