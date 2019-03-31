<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=basePath%>/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<!-- BEGIN HEAD -->

<head>
<base href="../">

<meta charset="utf-8" />


	<title>墨子星管理后台</title>
 <!-- BEGIN HEAD -->
  <script type="text/javascript" src="<%=basePath%>/js/base.js"></script>
<script src="<%=basePath%>/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>/js/jquery.backstretch.min.js" type="text/javascript"></script>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />

	<!-- BEGIN GLOBAL MANDATORY STYLES -->
    
	<link href="<%=basePath%>/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>/media/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="<%=basePath%>/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link href="<%=basePath%>/media/css/login.css" rel="stylesheet" type="text/css"/>

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="<%=basePath%>/media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login" >

	<!-- BEGIN LOGO -->

	<div class="logo">

		<img src="<%=basePath%>/image/我家微超LOGO.png"  style="height: 60px; width: 60px;" /> 

	</div>

	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->

	<div class="content">

		<!-- BEGIN LOGIN FORM -->
		<form class="form-vertical login-form" id="login_form">

			<h3 class="form-title" >墨子星管理后台</h3>

			<div class="alert alert-error hide">

				<button class="close" data-dismiss="alert"></button>

				<span>Enter any username and password.</span>

			</div>

			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">Username</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-user"></i>

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Username" id ="userName" name="username"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">Password</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i>

						<input class="m-wrap placeholder-no-fix" type="password" placeholder="Password" id="password" name="password"/>

					</div>

				</div>

			</div>

			<div class="form-actions">

				<label class="checkbox">

				<input type="checkbox" name="remember"  id="check"  value="1"/> 记住密码

				</label>

				<button type="submit" class="btn green pull-right">

				登录<i class="m-icon-swapright m-icon-white"></i>

				</button>            

			</div>

			<div class="forget-password">

				<h4>忘记密码了吗 ?</h4>

				<p>

					请联系管理员
				</p>

			</div>

			  <div class="create-account">
 
			 </div>

		</form>

		<!-- END LOGIN FORM -->        

		<!-- BEGIN FORGOT PASSWORD FORM -->

		<form class="form-vertical forget-form" action="index.html">

			<h3 class="">Forget Password ?</h3>

			<p>Enter your e-mail address below to reset your password.</p>

			<div class="control-group">

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-envelope"></i>

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email" />

					</div>

				</div>

			</div>

			<div class="form-actions">

				<button type="button" id="back-btn" class="btn">

				<i class="m-icon-swapleft"></i> Back

				</button>

				<button type="submit" class="btn green pull-right">

				Submit <i class="m-icon-swapright m-icon-white"></i>

				</button>            

			</div>

		</form>

		<!-- END FORGOT PASSWORD FORM -->

		<!-- BEGIN REGISTRATION FORM -->

		<form class="form-vertical register-form" action="index.html">

			<h3 class="">Sign Up</h3>

			<p>Enter your account details below:</p>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">Username</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-user"></i>

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Username" id="userName"  name="username"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">Password</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-lock"></i>

						<input class="m-wrap placeholder-no-fix" type="password" id="register_password"  id="password"   placeholder="Password" name="password"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<label class="control-label visible-ie8 visible-ie9">Re-type Your Password</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-ok"></i>

						<input class="m-wrap placeholder-no-fix" type="password" placeholder="Re-type Your Password" name="rpassword"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->

				<label class="control-label visible-ie8 visible-ie9">Email</label>

				<div class="controls">

					<div class="input-icon left">

						<i class="icon-envelope"></i>

						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email"/>

					</div>

				</div>

			</div>

			<div class="control-group">

				<div class="controls">

					<label class="checkbox">

					<input type="checkbox" name="tnc"/> I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a>

					</label>  

					<div id="register_tnc_error"></div>

				</div>

			</div>

			<div class="form-actions">

				<button id="register-back-btn" type="button" class="btn">

				<i class="m-icon-swapleft"></i>  Back

				</button>

				<button type="submit" id="register-submit-btn" class="btn green pull-right">

				Sign Up <i class="m-icon-swapright m-icon-white"></i>

				</button>            

			</div>

		</form>

		<!-- END REGISTRATION FORM -->

	</div>

	<!-- END LOGIN -->

	<!-- BEGIN COPYRIGHT -->

	<div class="copyright">

		

	</div>

	<!-- END COPYRIGHT -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="<%=basePath%>/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="<%=basePath%>/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="<%=basePath%>/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      

	<script src="<%=basePath%>/media/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="media/js/excanvas.min.js"></script>

	<script src="media/js/respond.min.js"></script>  

	<![endif]-->   

	<script src="<%=basePath%>/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="<%=basePath%>/media/js/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="<%=basePath%>/media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.uniform.min.js" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="<%=basePath%>/media/js/jquery.validate.min.js" type="text/javascript"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="<%=basePath%>/media/js/app.js" type="text/javascript"></script>

	<script src="<%=basePath%>/media/js/login.js" type="text/javascript"></script>      

	<!-- END PAGE LEVEL SCRIPTS --> 

	<script type="text/javascript" >
	$().ready(function() {
		$("#login_form").validate({
			rules: {
				username: "required",
				password: {
					required: true,
					minlength: 5
				},
			},
			messages: {
				username: "<p style='color: red;'>请输入姓名</p>",
				password: {
					required: "<p style='color: red;'>请输入密码</p>",
					minlength: jQuery.format("密码不能小于{0}个字 符")
				},
			},
			
			submitHandler : function(){
				$("#myModal").modal('show');
				var userName = $("#userName").val();
				var password = $("#password").val();
				var sfun = function(data){ 
					if( data.code=="200"){ 
						//登录成
						location.href = '<%=basePath%>index/main';
					}else{
						message("消息",data.message,null,function(){});
					}
					
				}
				requestMthod("<%=basePath%>login/login",{"userName":userName,"password":password},sfun);
				return;
			}
			
		});
	});
	
	$(function (){
    var COOKIE_NAME ='userName';
     var COOKIE_PASSWORD ='password';
    if($.cookie(COOKIE_NAME)){
     $('#userName').val($.cookie(COOKIE_NAME));
    
    }
    if($.cookie(COOKIE_PASSWORD)){
     $('#password').val($.cookie(COOKIE_PASSWORD));
    
    }
    $("#check").click(function(){
    if(this.checked){
       $.cookie(COOKIE_NAME,$("#userName").val() ,{path:'/',expires:10} );
       $.cookie(COOKIE_PASSWORD,$("#password").val() ,{path:'/',expires:10} );
    }else{
    
          $.cookie(COOKIE_NAME,null,{path:'/'});
          $.cookie(COOKIE_PASSWORD,null,{path:'/'});
    }
    });
} );

jQuery(document).ready(function() { 
  
      $.backstretch([
       "<%=basePath%>/image/bg/1.jpg",
       "<%=basePath%>/image/bg/2.jpg",
       "<%=basePath%>/image/bg/3.jpg",
       "<%=basePath%>/image/bg/4.jpg"
       ], {
         fade: 1000,
         duration: 5000
   }
   );
   });
   
   
  
	</script>

	<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->

</html>