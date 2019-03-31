<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<base href="../">
    <meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>充值中心</title>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <link rel="stylesheet" href="h5/layermobile/need/layer.css">
    <script type="text/javascript" src="h5/layermobile/layer.js" ></script>
    <link rel="stylesheet" href="h5/css/new_file.css" />
	<script type="text/javascript" src="h5/js/jquery-1.8.2.min.js" ></script>
	<script type="text/javascript" src="h5/js/new_file.js" ></script>
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="application-name" content="Material Design Lite">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <meta name="theme-color" content="#024f73">
</head>

<link rel="stylesheet" href="css/bootstrap.css">

<style>	
--------------------- 
</style>
<script type="text/javascript">
$(function() { 
	layer.open({
				content: '支付成功',
				btn: '关闭',
				yes: function(){
					window.open("/Mozi/h5/index",'_self',''); 
					}
			});
    //任何需要执行的js特效
})
		
</script>
<body>
		
</body>

</html>
