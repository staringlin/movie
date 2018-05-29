<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/admin/css/bootstrap.min.css"
	rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/admin/css/style.css"
	rel='stylesheet' type='text/css' />
<!-- fa CSS -->
<link
	href="${pageContext.request.contextPath}/admin/css/font-awesome.min.css"
	rel="stylesheet">
<!-- jQuery -->
<script
	src="${pageContext.request.contextPath}/admin/js/jquery-2.2.3.min.js"></script>
<!-- Nav CSS -->
<link href="${pageContext.request.contextPath}/admin/css/custom.css"
	rel="stylesheet">
<!-- Metis Menu Plugin JavaScript -->
<script
	src="${pageContext.request.contextPath}/admin/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/custom.js"></script>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="top1 navbar navbar-default navbar-static-top"
			role="navigation" style="margin-bottom: 0;">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/admin/pages/index.jsp">电影后台管理</a>
		</div>
		<!-- /.navbar-header -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="javascript:;"><i class="fa fa-laptop fa-fw nav_icon"></i>系统管理 <span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${pageContext.request.contextPath}/adminController/getSlide"><i class="fa fa-file-image-o fa-fw nav_icon"></i>轮播图</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/adminController/getAdmin"><i class="fa fa-cog fa-fw nav_icon"></i>管理员密码修改</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                        <li>
                            <a href="javascript:;"><i class="fa fa-user fa-fw nav_icon"></i>用户管理 <span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${pageContext.request.contextPath}/adminController/getUsers"><i class="fa fa-list fa-fw nav_icon"></i>用户列表</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                        <li>
                            <a href="javascript:;"><i class="fa fa-home fa-fw nav_icon"></i>电影管理 <span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${pageContext.request.contextPath}/adminController/getMovies"><i class="fa fa-list fa-fw nav_icon"></i>电影列表</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                        <li>
                            <a href="javascript:;"><i class="fa fa-list-alt fa-fw nav_icon"></i>评论管理 <span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${pageContext.request.contextPath}/adminController/getComments"><i class="fa fa-list fa-fw nav_icon"></i>评论列表</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                    </ul>
                </div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>
        <div id="page-wrapper">
            <div class="panel panel-default "> 
            <div class="panel-heading"> 
                <h3 > 
                    网站配置&nbsp;&nbsp;
                    <small>轮播图列表</small> 
                </h3> 
            </div> 
            <div class="panel-body"> 
                <div class="row">
                <span class="col-md-6"><a class="btn btn-info" href="${pageContext.request.contextPath}/adminController/addImg">添加轮播图</a></span>
                </div>
            </div>
            <table class="table table-striped table-bordered table-hover"> 
                    <caption>轮播图列表</caption> 
                    <thead> 
                        <tr> 
                            <th width="20%">编号</th> 
                            <th width="40%">图片</th> 
                            <th width="20%">电影名</th>
                            <th width="20%">操作</th>
                        </tr> 
                    </thead> 
                    <tbody>
                    <c:forEach var="imgs" items="${slides}" varStatus="status"> 
                        <tr> 
                            <td>${status.index+1}</td> 
                            <td><img src="${imgs.path}" width="200"/></td> 
                            <td>${imgs.name}</td> 
                            <td>
	                            <a href="${pageContext.request.contextPath}/adminController/modifyImg?path=${imgs.path}&id=${imgs.id}&name=${imgs.name}" class="btn btn-info">修改</a>&nbsp;
	                            <a href="${pageContext.request.contextPath}/adminController/deleteSlide?id=${imgs.id}" class="btn btn-info">删除</a>
                            </td>  
                        </tr> 
                     </c:forEach>

                     <tr id="disapear"></tr>
                    </tbody>             
                </table>             
        </div>
    </div>
    <!-- /#wrapper -->
			<!-- /#wrapper -->
			<!-- Bootstrap Core JavaScript -->
			<script
				src="${pageContext.request.contextPath}/admin/js/bootstrap.min.js"></script>
</body>
</html>
