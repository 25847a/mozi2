<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="../">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title></title>
<meta name="viewport" content="width=device-width, user-scalable=no">
<meta content="yes" name="apple-mobile-web-app-capable">
<link rel="stylesheet" href="h5/css/new_file.css" />
<script type="text/javascript" src="h5/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="h5/js/new_file.js"></script>

</head>

<link rel="stylesheet" href="css/bootstrap.css">
<style>

	.start{
		position:relative;
	    top:50px;
    }

	button{
		outline: none; 
		left: 120px;
		top: 550px;
		width: 120px;
		height: 40px;
		line-height: 20px;
		border-radius: 4px;
		background-color: rgba(38, 136, 196, 1);
		color: rgba(16, 16, 16, 1);
		font-size: 14px;
		color:#E8E8E8;
		text-align: center;
		box-shadow: 0px 2px 3px 0px rgba(170, 170, 170, 1);
		font-family: Roboto;
		border-color: #2688C4;
	}
	.phone{
		position:relative;
		right:-20px;
	}
	.txt{
		color:#848484;
	}
	.txt-red{
		color:#E51C23;
	}
	
</style>
<script>
	
</script>

<body>

	<div class="">
	<br>
		<div class="phone">
			<h4>电话卡号：${phone }</h4>
			<h4>ICCIC： ${ICCIC}</h4>
			<h4>实名校验码：${PUK }</h4>
		</div>
		<br>
		<br>
		<br>
		<div align="center">
			<div class="txt" >
			<h5 align="center">请记住上面的号码卡信息,点击"开始激活"</h5>
			<h5 align="center">进入中国移动激活页面</h5>
			</div>
			<br>
			<div class="txt-red">
			<h5 align="center">注意:  激活流程第二步填写的号码为</h5>
			<h5 align="center">个人正常使用的的电话号码</h5>
			</div>
			<br>
			<div class="txt">
			<h5 align="center">激活成功后会收到</h5>
			<h5 align="center">"号码已可以正常使用"的短信提示</h5>
			</div>
		</div>
		
		
		<div align="center" class="start">
			<button onclick="start()">开始激活</button>
		</div>
		<br>
	</div>
</body>
<script type="text/javascript">
	function start(){
		 window.location.assign("http://wap.gd.10086.cn/nwap/wlw/wlwrealName/index.jsps")
		//window.location.href("http://wap.gd.10086.cn/nwap/wlw/wlwrealName/index.jsps");
		
	}


</script>
</html>
