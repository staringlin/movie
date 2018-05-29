<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户列表</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
 <!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/admin/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/admin/css/style.css" rel='stylesheet' type='text/css' />
<!-- fa CSS -->
<link href="${pageContext.request.contextPath}/admin/css/font-awesome.min.css" rel="stylesheet"> 
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/admin/js/jquery-2.2.3.min.js"></script> 
<!-- Nav CSS -->
<link href="${pageContext.request.contextPath}/admin/css/custom.css" rel="stylesheet">
<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/admin/js/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/custom.js"></script>
</head>
<body>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="top1 navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0;">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">电影后台管理</a>
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
            <!-- /.navbar-static-side -->
        </nav>
        <div id="page-wrapper">
            <div class="panel panel-default "> 
            <div class="panel-heading"> 
                <h3 > 
                    网站配置&nbsp;&nbsp;
                    <small>用户列表</small> 
                </h3> 
            </div>
                        <h3 style="color:red">${message}</h3> 
            <div class="panel-body"> 
                <div class="row ">
                <span class="col-md-6 col-md-push-6">
	                <form action="${pageContext.request.contextPath}/adminController/searchUser" method="post">
				       <div class="input-group ">
				           	<input type="text" class="form-control" name="key" > 
				            <div class="input-group-btn">
				            <button class="btn btn-default ">搜索</button>
				            </div>
				       </div>
	                </form>
                </span>
                </div>
            </div>

            <table class="table table-striped table-bordered table-hover"> 
                    <caption>用户列表</caption> 
                    <thead> 
                        <tr> 
                            <th width="20%">编号</th> 
                            <th width="20%">头像</th>
                            <th width="20%">昵称</th> 
                            <th width="20%">邮箱</th>
                            <th width="20%">操作</th>
                        </tr> 
                    </thead> 
                    <tbody>
                    <c:forEach var="user" items="${users}"> 
                        <tr> 
                            <td>${user.id}</td> 
                            <td><img src="${user.avatar}" width="100"></td> 
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>
	                            <a href="${pageContext.request.contextPath}/adminController/modifyUser?id=${user.id}&username=${user.username}&avatar=${user.avatar}&email=${user.email}" class="btn btn-info">修改</a>&nbsp;
	                            <a href="${pageContext.request.contextPath}/adminController/deleteUser?id=${user.id}" class="btn btn-info">删除</a>
                            </td>  
                        </tr> 
                     </c:forEach>
                     <tr id="message">
                     <td></td>
                     <td></td>
                        <td colspan="3">						
                        <nav aria-label="Page navigation" class="pull-right ">
						  <ul class="pagination pagination-lg" id="pageBtn">
	
						  </ul>
						  <span>共${pageCount}页</span>
					  	</nav>	
					  	</td>                                                     
				  					                                    
                     </tr>
                     <tr id="disapear"></tr>
                    </tbody> 	
                </table>                                                                  
        </div>
    </div>
    <!-- /#wrapper -->
    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/admin/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(function(){
	    
	    var dqPage = "${currentPage}";//得到当前页数
	    dqPage = parseInt(dqPage);//得到的文本转成int
	    var pageCount = "${pageCount}";//得到总页数
	    pageCount = parseInt(pageCount);
	    var i = 1;
	    i = parseInt(i);
	    
	    
	
	    var href = "/adminController/getUsers?currentPage=";
	    var item="<li><a href='"+href+(dqPage-1)+"' aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>";
	    if (pageCount <= 5 ) {//总页数小于五页，则加载所有页
	        
	        for (i; i <= pageCount; i++) {
	            if (i == dqPage) {
	                item += "<li class='active'><a>"+i+"</a></li>"; 
	            }else{
	                item += "<li><a href='"+href+i+"' >"+i+"</a></li>"; 
	            }
	        };
	        item+="<li><a href='"+href+(dqPage+1)+"' aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
	        $('#pageBtn').append(item);
	        return;
	    }else if (pageCount > 5) {//总页数大于五页，则加载五页
	        if (dqPage < 5) {//当前页小于5，加载1-5页
	            for (i; i <= 5; i++) {
	                if (i == dqPage) {
	                	item += "<li class='active'><a >"+i+"</a></li>";  
	                }else{
	                	item += "<li><a href='"+href+i+"' >"+i+"</a></li>";  
	                }
	            };
	            if (dqPage <= pageCount-2) {//最后一页追加“...”代表省略的页
	            	item += "<li><a>...</a></li>";
	            }
	            item+="<li><a href='"+href+(dqPage+1)+"' aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
	            $('#pageBtn').append(item);
	            return;
	        }else if (dqPage >= 5) {//当前页大于5页
	            for (i; i <= 2; i++) {//1,2页码始终显示
	            	item += "<li><a href='"+href+i+"' >"+i+"</a></li>"; 
	            }
	            item += "<li><a>...</a></li>";//2页码后面用...代替部分未显示的页码
	            if (dqPage+1 == pageCount) {//当前页+1等于总页码
	                for(i = dqPage-1; i <= pageCount; i++){//“...”后面跟三个页码当前页居中显示
	                    if (i == dqPage) {
	                    	item += "<li class='active'><a>"+i+"</a></li>";
	                    }else{
	                    	item += "<li><a href='"+href+i+"' >"+i+"</a></li>"; 
	                    }
	                }
	            }else if (dqPage == pageCount) {//当前页数等于总页数则是最后一页页码显示在最后
	                for(i = dqPage-2; i <= pageCount; i++){//...后面跟三个页码当前页居中显示
	                    if (i == dqPage) {
	                    	item += "<li class='active'><a>"+i+"</a></li>"; 
	                    }else{
	                    	item += "<li><a href='"+href+i+"' >"+i+"</a></li>";
	                    }
	                }
	            }else{//当前页小于总页数，则最后一页后面跟...
	                for(i = dqPage-1; i <= dqPage+1; i++){//dqPage+1页后面...
	                    if (i == dqPage) {
	                    	item += "<li class='active'><a>"+i+"</a></li>"; 
	                    }else{
	                    	item += "<li><a href='"+href+i+"' >"+i+"</a></li>";
	                    }
	                }
	                item += "<li><a>...</a></li>";
	            }
	            item+="<li><a href='"+href+(dqPage+1)+"' aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>";
	            $('#pageBtn').append(item);
	            return;
	        }
	    }
	    
	    
	});
	</script>
</body>
</html>