<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>后台登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
 <!-- Bootstrap Core CSS -->
<link href="${pageContext.request.contextPath}/admin/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="${pageContext.request.contextPath}/admin/css/style.css" rel='stylesheet' type='text/css' />
<link href="${pageContext.request.contextPath}/admin/css/font-awesome.min.css" rel="stylesheet"> 
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/admin/js/jquery-2.2.3.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/admin/js/bootstrap.min.js"></script>
</head>
<body id="login">
  <h2 class="form-heading">后台登录</h2>
  <div class="app-cam">
	  <form>
		<input type="text" class="text" id="account" placeholder="账号">
		<input type="password" id="password" placeholder="密码">
		<div class="submit"><input type="button" id="submit" value="Login" style="background-color:#3498db"></div>
		<!-- <div class="login-social-link"></div> -->
	</form>
  </div>
   <div class="copy_layout login">
      <p>Copyright &copy; 2018..</p>
   </div>
</body>
<script>
	$('#submit').click(function(){
		var account = $('#account').val();
		var pwd = $('#password').val();
		if(account == ''){
			alert('请输入账号');
			return false;
		}
		if(pwd == ''){
			alert('请输入密码');
			return false;
		}
		$.post('${pageContext.request.contextPath}/adminController/login', {'username': account, 'password': pwd}, function(data){
			if(data.success){
				window.location = '${pageContext.request.contextPath}/adminController/index';
			} else {
				alert('登录失败，' + data.msg);
				return false;
			}
			
		}, 'json');
	})
</script>
</html>
