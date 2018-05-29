<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Movie</title>
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
<style type="text/css">
  #flexiselDemo3{
     height: 400px;
  }
  #flexiselDemo3 img{
  	height: 240px; 
  }
</style>
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
	   	   <h2 class="m_3">为你推荐的电影</h1>
					<div class="box_2">
						<div class="row">
							<c:forEach var="movie" items="${MovieRecommendatinos}"
								varStatus="status">
								<c:if test="${status.index%4==0}">
						</div>
						<div class="row">
							</c:if>
							<div class="col-md-3">
								<img src="${movie.poster}" referrerpolicy="never" /> <a
									class="bottom"
									href="/userController/getMovie?id=${movie.id}&poster=${movie.poster}&comments=${movie.comments}&name=${movie.name}&type=${movie.type}&published_year=${movie.published_year}">${movie.name}</a>
							</div>

							</c:forEach>
						</div>

					</div>
				   <h1 class="recent">最近看过的电影</h3>
                   <ul id="flexiselDemo3">
							<c:forEach var="movie" items="${reviewedMovies}">
							<li><img style="width: 188px;margin: 0 auto;" src="${movie.poster}" class="img-responsive" referrerpolicy="never"/><div class="grid-flex"><a href="/userController/getMovie?id=${movie.id}&poster=${movie.poster}&comments=${movie.comments}&name=${movie.name}&type=${movie.type}&published_year=${movie.published_year}">${movie.name}</a></div></li>
							</c:forEach>
						
				    </ul>
				    <script type="text/javascript">
					 $(window).load(function() {
						$("#flexiselDemo3").flexisel({
							visibleItems: "${fn:length(reviewedMovies)}",
							animationSpeed: 1000,
							autoPlay: true,
							autoPlaySpeed: 3000,    		
							pauseOnHover: true,
							enableResponsiveBreakpoints: true,
					    	responsiveBreakpoints: { 
					    		portrait: { 
					    			changePoint:480,
					    			visibleItems: 1
					    		}, 
					    		landscape: { 
					    			changePoint:640,
					    			visibleItems: 2
					    		},
					    		tablet: { 
					    			changePoint:768,
					    			visibleItems: 3
					    		}
					    	}
					    });
					    
					});
				   </script>
				   <script type="text/javascript" src="/js/jquery.flexisel.js"></script>

		  </div>
</div>
</div>
<div class="container"> 
 <footer id="footer">
 	<div id="footer-3d">
		<div class="gp-container">
			<span class="first-widget-bend"></span>
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



</html>