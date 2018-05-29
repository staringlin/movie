<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>
<body>
<div class="container">
	<div class="container_wrap">
		<div class="header_top">
		    <div class="col-sm-3 logo"><a href="/userController/index"><img src="/images/logo.png" alt=""/></a></div>
		    <div class="col-sm-6 nav">
			  <ul>
				 <li> <span class="simptip-position-bottom simptip-movable" data-tooltip="Animation"><a href="/movieController/getTypedMovie?type=Animation"> </a></span></li>
				 <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Action"><a href="/movieController/getTypedMovie?type=Action"> </a> </span></li>
				 <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Drama"><a href="/movieController/getTypedMovie?type=Drama"> </a></span></li>
				 <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Romance"><a href="/movieController/getTypedMovie?type=Romance"> </a></span></li>
				 <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Comedy"><a href="/movieController/getTypedMovie?type=Comedy"> </a></span></li>
				 <li><span class="simptip-position-bottom simptip-movable" data-tooltip="Thriller"><a href="/movieController/getTypedMovie?type=Thriller"> </a></span></li>
			 </ul>
			</div>
			<div class="col-sm-3 header_right">
			
				<c:choose>  
				    <c:when test="${username == null}">    <!--如果 -->
					    <ul class="header_right_box"> 
					    	<li><a href="/login.html">Login</a>  |  <a href="/register.html">register</a></li>
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
	   	   <h2 class="m_3">Now in the Movie</h1>
				<div class="box_2">
					 <div class="row">
				              <c:forEach var="movie" items="${TypedMovies}" varStatus="status"> 
						              	<c:if test="${status.index%4==0}">
								             </div>
								             <div class="row">
								        </c:if>
				                        <div class="col-md-3"> 
											<p class="top">
												<span>
													<i class="icon1"> </i>
													<span>${movie.comments}</span>
												</span>
												<i class="icon3"> </i>
											</p>
											<img src="${movie.poster}" referrerpolicy ="never"/>
											<a class="bottom" href="/userController/getMovie?id=${movie.id}&poster=${movie.poster}&comments=${movie.comments}&name=${movie.name}&type=${movie.type}&published_year=${movie.published_year}">${movie.name}</a>                            
				                        </div>		                    
		
		                     </c:forEach>	
					</div>
			<span id="dqPage" hidden="hidden" class="disabled1 current">${currentPage}</span>
			<%-- js控制的页码显示在这个div中--%>
			<div id="pageBtn" style="width: auto;display:inline-block !important;height: auto;">
			</div>	
		
			  </div> 
              <h1 class="recent">Recently Viewed</h3>
                   <ul id="flexiselDemo3">
						<li><img src="/images/1.jpg" class="img-responsive"/><div class="grid-flex"><a href="#">Syenergy 2mm</a><p>22.10.2014 | 14:40</p></div></li>
						<li><img src="/images/2.jpg" class="img-responsive"/><div class="grid-flex"><a href="#">Surf Yoke</a><p>22.01.2015 | 14:40</p></div></li>
						<li><img src="/images/3.jpg" class="img-responsive"/><div class="grid-flex"><a href="#">Salty Daiz</a><p>22.10.2013 | 14:40</p></div></li>
						<li><img src="/images/4.jpg" class="img-responsive"/><div class="grid-flex"><a href="#">Cheeky Zane</a><p>22.10.2014 | 14:40</p></div></li>
						<li><img src="/images/5.jpg" class="img-responsive"/><div class="grid-flex"><a href="#">Synergy</a><p>22.10.2013 | 14:40</p></div></li>
				    </ul>
				    <script type="text/javascript">
					 $(window).load(function() {
						$("#flexiselDemo3").flexisel({
							visibleItems: 4,
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
				   <ul id="flexiselDemo1">
						<li><img src="/images/8.jpg" class="img-responsive"/><div class="grid-flex"><a href="#">Syenergy 2mm</a><p>22.10.2014 | 14:40</p></div></li>
						<li><img src="/images/7.jpg" class="img-responsive"/><div class="grid-flex"><a href="#">Surf Yoke</a><p>22.01.2015 | 14:40</p></div></li>
						<li><img src="/images/6.jpg" class="img-responsive"/><div class="grid-flex"><a href="#">Salty Daiz</a><p>22.10.2013 | 14:40</p></div></li>
						<li><img src="/images/1.jpg" class="img-responsive"/><div class="grid-flex"><a href="#">Cheeky Zane</a><p>22.10.2014 | 14:40</p></div></li>
						<li><img src="/images/2.jpg" class="img-responsive"/><div class="grid-flex"><a href="#">Synergy</a><p>22.10.2013 | 14:40</p></div></li>
				     </ul>
				    <script type="text/javascript">
					 $(window).load(function() {
						$("#flexiselDemo1").flexisel({
							visibleItems: 4,
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