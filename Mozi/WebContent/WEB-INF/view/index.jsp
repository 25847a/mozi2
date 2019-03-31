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
<!-- <script src="js/jquery-1.10.1.min.js" type="text/javascript"></script>
 --><!-- BEGIN HEAD -->
<head>
<base href="../">

<meta charset="utf-8" />

<title>墨子管理系统</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<meta content="" name="description" />

<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />
	

<link href="media/css/style-metro.css" rel="stylesheet" type="text/css" />

<link href="media/css/style.css" rel="stylesheet" type="text/css" />

<link href="media/css/style-responsive.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/default.css" rel="stylesheet" type="text/css"
	id="style_color" />

<link href="media/css/uniform.default.css" rel="stylesheet"
	type="text/css" />

<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN PAGE LEVEL STYLES -->

<link href="media/css/jquery.gritter.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/daterangepicker.css" rel="stylesheet"
	type="text/css" />

<link href="media/css/fullcalendar.css" rel="stylesheet" type="text/css" />

<link href="media/css/jqvmap.css" rel="stylesheet" type="text/css"
	media="screen" />

<link href="media/css/jquery.easy-pie-chart.css" rel="stylesheet"
	type="text/css" media="screen" />

<!-- END PAGE LEVEL STYLES -->

<link rel="shortcut icon" href="media/image/favicon.ico" />
<style type="text/css">

 
 table td{height: 30px; text-align:center;}
  
 table th{height: 30px; text-align:center;}
</style>
</head>
<script src="js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="js/date.js" type="text/javascript"></script>
<script src="js/daterangepicker.js" type="text/javascript"></script>
<script src="js/jquery.gritter.js" type="text/javascript"></script>
<script src="js/fullcalendar.min.js" type="text/javascript"></script>
<script src="js/jquery.easy-pie-chart.js" type="text/javascript"></script>
<script src="js/jquery.sparkline.min.js" type="text/javascript"></script>
<script src="js/app.js" type="text/javascript"></script>
<script src="js/index.js" type="text/javascript"></script>
<script src="js/calendar.js" type="text/javascript"></script>
<script src="js/charts.js"></script>
<!-- <script src="js/Chart.Doughnut.js"></script> -->
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<script type="js/laydate/laydate.js" src="js/layer/layer.js"></script>
<!-- END HEAD -->
<style type="text/css">

</style>
<!-- BEGIN BODY -->

<body class="page-header-fixed">

	<!-- BEGIN HEADER -->

	<div class="header navbar navbar-inverse navbar-fixed-top">

		<!-- BEGIN TOP NAVIGATION BAR -->

		<div class="navbar-inner">

			<div class="container-fluid">

				<!-- BEGIN LOGO -->

				<a class="brand"> <img width="20" height="20"
					src="image/我家微超LOGO.png" alt="logo" />

				</a>

				<!-- END LOGO -->

				<!-- BEGIN RESPONSIVE MENU TOGGLER -->

				<a href="javascript:;" class="btn-navbar collapsed"
					data-toggle="collapse" data-target=".nav-collapse"> <img
					src="media/image/menu-toggler.png" alt="" />

				</a>

				<!-- END RESPONSIVE MENU TOGGLER -->

				<!-- BEGIN TOP NAVIGATION MENU -->

				<ul class="nav pull-right">



					<!-- BEGIN USER LOGIN DROPDOWN -->

					<li class="dropdown user"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <img alt=""
							src="image/default-photo1.png" /> <span class="username">${loginUser.username}</span>

							<i class="icon-angle-down"></i>

					</a>

						<ul class="dropdown-menu">



							<li><a href="login/dropout"><i class="icon-key"></i> 退出系统</a></li>

						</ul></li>

					<!-- END USER LOGIN DROPDOWN -->

				</ul>

				<!-- END TOP NAVIGATION MENU -->

			</div>

		</div>

		<!-- END TOP NAVIGATION BAR -->

	</div>

	<!-- BEGIN CONTAINER -->

	<div class="page-container">

		<!-- BEGIN SIDEBAR -->

		<div class="page-sidebar nav-collapse collapse">

			<!-- BEGIN SIDEBAR MENU -->

			<ul class="page-sidebar-menu">

				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->

					<div class="sidebar-toggler hidden-phone"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->

				</li>

				<li style="height: 20px;"></li>

				<li class="start active "><a href="javascript:void(0)" onclick="Home()"> <i
						class="icon-home"></i> <span class="title">墨子简介</span> <span
						class="selected"></span>

				</a></li>
				<c:forEach var="rs" items="${mv.ro}">
					<c:if test="${rs.al.size()  > 0 }">
						<li class="" name="${rs.r.id}"><a href="javascript:;">

								<i class="${rs.r.img}"></i> <span class="title">${rs.r.roleName}</span>

								<span class="arrow "></span>

						</a>

							<ul class="sub-menu">
								<c:forEach var="Menu" items="${rs.al}">
									<li><a href="javascript:void(0);" url="${Menu.url}">
											${Menu.authName}</a></li>

								</c:forEach>


							</ul></li>
					</c:if>
				</c:forEach>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>

	<!-- END SIDEBAR -->

	<!-- BEGIN PAGE -->

	<div class="page-content">

		<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

		<div id="portlet-config" class="modal hide">

			<div class="modal-header">

				<button data-dismiss="modal" class="close" type="button"></button>

				<h3>Widget Settings</h3>

			</div>

			<div class="modal-body">Widget settings form goes here</div>

		</div>

		<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

		<!-- BEGIN PAGE CONTAINER-->

		<div class="container-fluid" style="margin-top:50px; ">
			<div class="page-content">
		
            
             </div>
		</div>

		<!-- END PAGE -->

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER -->

	<div class="footer">

		<div class="footer-inner">

			墨子开发有限公司<a href="http://www.qmcx.com.cn" title="" target="_blank"></a>
			<a href="http://www.qmcx.com.cn" target="_blank" title=""></a>

		</div>

		<div class="footer-tools">

			<span class="go-top"> <i class="icon-angle-up"></i>

			</span>

		</div>

	</div>

	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="media/js/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="media/js/jquery-ui-1.10.1.custom.min.js"
		type="text/javascript"></script>

	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="media/js/excanvas.min.js"></script>

	<script src="media/js/respond.min.js"></script>  

	<![endif]-->

	<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.uniform.min.js" type="text/javascript"></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="media/js/jquery.vmap.js" type="text/javascript"></script>

	<script src="media/js/jquery.vmap.russia.js" type="text/javascript"></script>

	<script src="media/js/jquery.vmap.world.js" type="text/javascript"></script>

	<script src="media/js/jquery.vmap.europe.js" type="text/javascript"></script>

	<script src="media/js/jquery.vmap.germany.js" type="text/javascript"></script>

	<script src="media/js/jquery.vmap.usa.js" type="text/javascript"></script>

	<script src="media/js/jquery.vmap.sampledata.js" type="text/javascript"></script>

	<script src="media/js/jquery.flot.js" type="text/javascript"></script>

	<script src="media/js/jquery.flot.resize.js" type="text/javascript"></script>

	<script src="media/js/jquery.pulsate.min.js" type="text/javascript"></script>

	<script src="media/js/date.js" type="text/javascript"></script>

	<script src="media/js/daterangepicker.js" type="text/javascript"></script>

	<script src="media/js/jquery.gritter.js" type="text/javascript"></script>

	<script src="media/js/fullcalendar.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.easy-pie-chart.js" type="text/javascript"></script>

	<script src="media/js/jquery.sparkline.min.js" type="text/javascript"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="media/js/app.js" type="text/javascript"></script>

	<script src="media/js/index.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/laydate/laydate.js"></script>

	<script type="text/javascript" src="js/base.js"></script>
	<!-- END PAGE LEVEL SCRIPTS -->

	<script>
		jQuery(document).ready(function() {

			App.init(); // initlayout and core plugins

			Index.init();

			Index.initJQVMAP(); // init index page's custom scripts

			Index.initCalendar(); // init index page's custom scripts

			Index.initCharts(); // init index page's custom scripts

			Index.initChat();

			Index.initMiniCharts();

			Index.initDashboardDaterange();


		});
	</script>

	<!-- END JAVASCRIPTS -->
	<script type="text/javascript">
		//第二层数据的类 ul sub-menu        li是第二层数据的显示。
		$(function() {
			$(".page-sidebar-menu li").bind("click", function(event) {
				if($(this).index(".page-sidebar-menu li") == 0){
					return;
				}
			   var href = $(this).children('a').attr("url");
               if(href){
              		 $(".page-sidebar-menu > li").removeClass("active ");
               	     $(this).parent().parent().addClass("active");
               	     $(this).parent().parent().children('a').append("<span class='selected'></span>");
              		 $(".page-sidebar-menu ul li").removeClass("active");
              		 $(this).addClass("active");
               }else{ 
               		if($(this).children('ul').length == 0){
               			$(".page-sidebar-menu > li").removeClass("active ");
               			 $(".page-sidebar-menu ul li").removeClass("active");
               			$(this).addClass("active");
               		}
               		
               		return ;
               
               }
				//获取到整个页面的数据进行显示操作
				var sfun = function(html) {
					//判断带来的数据里面是否出现DOCTYPE  
					if (html.indexOf("DOCTYPE") > 0) {
						location.href = "login/loginIn";
					}
					//追加到某个地方进行显示
					$(".page-content").eq(0).html(html);
				}
				//定义获取方法将整个要返回的页面提添加到当前的页面；
				requestMthod(href, {}, sfun);
			});

		});
	
	function Home(){
	$(".page-content").eq(0).html("<iframe id='menuFrame' name='menuFrame' src='index/process' style='overflow:visible;'  frameBorder=0 scrolling=no width='100%' height='800px;''></iframe>");
	}	
		
 
	</script>
</body>

<!-- END BODY -->

</html>