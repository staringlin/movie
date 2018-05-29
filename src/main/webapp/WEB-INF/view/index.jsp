<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Movie_store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<link href="/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- start plugins -->
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/1.11.1/jquery.min.js"></script>
<link href='/css/font.css' rel='stylesheet' type='text/css'>
<script src="/js/responsiveslides.min.js"></script>
<script>
	$(function() {
		$("#slider").responsiveSlides({
			auto : true,
			nav : true,
			speed : 500,
			namespace : "callbacks",
			pager : true,
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="container_wrap">
			<div class="header_top">
				<div class="col-sm-3 logo">
					<a href="/userController/index"><img src="/images/logo.png" alt="" /></a>
				</div>
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
						<c:when test="${username == null}">
							<!--如果 -->
							<ul class="header_right_box">
								<li><a href="/login.html">登录</a> | <a href="/register.html">注册</a></li>
							</ul>
						</c:when>
						<c:otherwise>
							<!--否则 -->
							<ul class="header_right_box">
								<li><img src="/images/p1.png" alt="" weight="36"
									height="36" /></li>
								<li><p>
										<a href="/userController/logout">${username} | 退出</a>
									</p></li>
								<li class="last"><i class="edit"> </i></li>
								<div class="clearfix"></div>
							</ul>
						</c:otherwise>
					</c:choose>

				</div>
				<div class="clearfix"></div>
			</div>
			<div class="slider">
				<div class="callbacks_container">
					<ul class="rslides" id="slider">
						<li><img src="/images/banner.jpg" class="img-responsive"
							alt="" />
							<div class="button">
								<a href="#" class="hvr-shutter-out-horizontal">查看详情</a>
							</div></li>
						<li><img src="/images/banner1.jpg" class="img-responsive"
							alt="" />
							<div class="button">
								<a href="#" class="hvr-shutter-out-horizontal">查看详情</a>
							</div></li>
						<li><img src="/images/banner2.jpg" class="img-responsive"
							alt="" />
							<div class="button">
								<a href="#" class="hvr-shutter-out-horizontal">查看详情</a>
							</div></li>
					</ul>
				</div>
				<div class="banner_desc">
					<div class="col-md-9">
						<ul class="list_1">
							<li>
							</li>	
						</ul>
					</div>
					<div class="col-md-3 grid_1">
						<ul class="list_1 list_2">
							<li>
							</li>
							<li>
								
							</li>
							<li>
							</li>														
						</ul>
					</div>
				</div>
			</div>
			
			<div class="content">
				<div class="box_1">
					<h1 class="m_2">特辑电影</h1>

					<div class="search">
						<form action="/movieController/search">
							<input type="text" name="movieName" value="搜索电影..."
								onfocus="this.value='';"
								onblur="if (this.value == '') {this.value ='';}"> <input
								type="submit" value="">
						</form>
					</div>
					<div class="clearfix"></div>					
				</div>
<a class="btn btn-warning  pull-right"  href="/recommendController/getRecommendation?id=${id}" >为我推荐</a>
				<div class="box_2">
					<div class="row">
						<c:forEach var="movie" items="${featuredMovies}"
							varStatus="status">
							<c:if test="${status.index%4==0}">
					</div>
					<div class="row">
						</c:if>
						<div class="col-md-3">
							<p class="top">
								<span> <i class="icon1"> </i> <span>${movie.comments}</span>
								</span> <i class="icon3"> </i>
							</p>
							<img src="${movie.poster}" referrerpolicy="never" /> <a
								class="bottom"
								href="/userController/getMovie?id=${movie.id}&poster=${movie.poster}&comments=${movie.comments}&name=${movie.name}&type=${movie.type}&published_year=${movie.published_year}">${movie.name}</a>
						</div>

						</c:forEach>
					</div>

				</div>





				<div>
					<div class="clearfix"></div>
				</div>
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
							<img src="/images/f_logo.png" alt="" />
						</div>
						<br>
						<p>It is a long established fact that a reader will be
							distracted by the readable content of a page.</p>
						<p class="text">There are many variations of passages of Lorem
							Ipsum available, but the majority have suffered.</p>
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
								<p>Copyright &copy; 2018.lxx.</p>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</footer>
	</div>
</body>
</html>