
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <!-- 状态栏的值可能为black, default, black-translucent -->
    <meta name="apple-mobile-web-app-status-bar-style" content="default">
    <!-- uc强制竖屏 -->
    <meta name="screen-orientation" content="portrait">
    <!-- UC强制全屏 -->
    <!--<meta name="full-screen" content="yes"> -->
    <!-- UC应用模式 -->
    <!-- <meta name="browsermode" content="application"> -->
    <!-- QQ强制竖屏 -->
    <meta name="x5-orientation" content="portrait">
    <!-- QQ强制全屏 -->
    <meta name="x5-fullscreen" content="true">
    <!-- QQ应用模式 -->
    <meta name="x5-page-mode" content="app">
    <!-- 是针对一些老的不识别viewport的浏览器，列如黑莓 -->
    <meta name="HandheldFriendly" content="true">
    <!-- 微软的老式浏览器 -->
    <meta name="MobileOptimized" content="320">
    <meta http-equiv="pragma" content="no-cache"  />
    <meta name="format-detection" content="telephone=no, email=no"/>
<title>墨子星健康管家</title>
<style>
html,body{
    margin:0;
    padding:0;
    min-height: 100%;
    overflow-x: hidden;
    -webkit-tap-highlight-color: rgba(0,0,0,0);
}
* {box-sizing: border-box;}
body,
dl, dt, dd, ul, ol, li,
h1, h2, h3, h4, h5, h6,
pre, code, form, fieldset, legend, input, textarea,
p, blockquote, th, td, hr, button,
article, aside, details, figcaption, figure, footer, header, menu, nav, section {
  margin: 0;
  padding: 0;
  border: 0;
}
a {text-decoration: none;}
button {user-select: none;}
img {vertical-align: middle;}
img:not([src]),img[src=""] {opacity: 0;}
ul, ol {list-style: none;}
table {border-collapse: collapse; border-spacing: 0;}
input, select, button, textarea {
    font-size: 100%; 
    font: inherit;
    -webkit-tap-highlight-color: rgba(255,0,0,0);
    -webkit-appearance: none;
}
#box{ 
    position: relative;
    height: calc(100vh);
    width: 100%;  
    background: url('https://img.cdn.zhaoshang800.com/img/2018/06/12/broker/3b675033-8180-4e6b-b7d1-c4e2f24a1ab8.jpg') no-repeat;
    background-size:cover ;
}
#loading{
    text-align: center;
    position: absolute;
    top: calc(100vh - 50%);
    left: 50%;
    transform: translateX(-50%);
    -webkit-transform: translateX(-50%);
    line-height: 1.12px;
}
#loading a{
    color: #E60012;
    border: 1px solid #cccccc;
    width: 5.267rem;
    height: 1.12rem;
    border-radius: 1.33rem;
    font-size: 0.4rem;
    background: #FFFFFF;
    display: flex;
    align-items: center;
    justify-content: center;
}
#loading #load_android{
margin-top: 1rem;
}
#loading #load_android img{
margin-left: 0.2rem;
margin-right: 0.2rem;
width: 0.48rem;
height: 0.512rem;
}
#loading #load_ios img{
margin-right: 0.2rem;
    width: 0.48rem;
height: 0.56rem;
}
#copyright{
text-align: center;
    position: absolute;
    bottom: 1rem;
    width: 100%;
    color: #FFFFFF;
    font-size:0.346rem;
}
#copyright .copy{
font-size: 0.32rem;
}
#pic{
    text-align: center;
    position: absolute;
    width: 100%;
}
#pic img{
    width: 2.333rem;
    height: 2.333rem;
    margin-top: 4rem;
    padding: 0.133rem;
}
#pic p{
    width: 100%;
    font-size: 0.64rem;
    color: #FFFFFF;
    text-align: center;
}
#shade{
    position: relative;
    /*水平居中*/
    text-align: center;
    
}
#shade p{
    position: absolute;
    color: white;
    font-size: 0.4rem;
    text-align: left;
    top:0.2rem;
    width: 100%;
    left: 0.4rem;
    padding-right: 1.5rem;
}
#shade p span{
border-bottom: 1px solid #FFFFFF;
}
#img{
    position: absolute;
    display: block;
    right: 0.1rem;
    top:0;
    width: 0.94rem;
    height: 1rem;
}

</style>
</head>
<script type="text/javascript" src="https://i.cdn.zhaoshang800.com/public/js/flexible.js"></script>





<body style="width: 100%;height: 100%;">
<div id="box">
	<div id="shade" class="shade">
           <!--  <p style="color: white;">如果在微信中无法直接下载，可以点击右上角选择在<span>浏览器中打开</span>即可下载。</p> -->
           <!--  <img id='img' src="https://img.cdn.zhaoshang800.com/img/2018/06/12/broker/f8589779-5a3b-47c0-85dc-d5985e9ca83f.png" alt="pic"> -->
        </div>
        <div id="pic">
            <img src="https://appicon.pgyer.com/image/view/app_icons/b5f4d3ed28dcb26b89cd4a1550efffdb/120" alt="墨子星">
            <p>墨子星健康管家</p>
        </div> 
        <div id="loading">
        <!-- <a id='load_ios' href="https://itunes.apple.com/cn/app/id1384882080?mt=8">
        <img src="https://img.cdn.zhaoshang800.com/img/2018/06/12/broker/23348e98-acd0-49fb-987f-a0d693455864.png" alt="iPhone版下载" />
        iPhone版下载
        </a>  -->
        
            <a id='load_android' href="javascript:void(0)" onclick="down();">
            <img src="https://img.cdn.zhaoshang800.com/img/2018/06/12/broker/3f0db71b-61df-4b7f-9da8-9b1ea321e01f.png" alt="Android版下载" 
            />
           	 点击安装&nbsp;&nbsp;&nbsp;
            </a>
        </div>
        <div id="copyright">
        <p>墨子星智能科技集团有限公司</p>
        <p class="copy">Mozistar©2018</p>
        </div>
        
        
</div>
</body>
<script>
var url  = window.location.host;
function down(){
	window.location.href="http://"+url+"/Mozi/app/down"; 
}
//手机端判断各个平台浏览器及操作系统平台
function checkPlatform(){
  if(/android/i.test(navigator.userAgent)){
      //alert("This is Android'browser.")//这是Android平台下浏览器
      //window.location="http://"+url+"/Mozi/app/down"
  }
  if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){
      //alert("This is iOS'browser.");//这是iOS平台下浏览器
       window.location="https://itunes.apple.com/cn/app/id1384882080?mt=8"
  }
  if(/Linux/i.test(navigator.userAgent)){
//	      alert("This is Linux'browser.");//这是Linux平台下浏览器
  }
  if(/Linux/i.test(navigator.platform)){
//	      alert("This is Linux operating system.");//这是Linux操作系统平台
  }
  if(/MicroMessenger/i.test(navigator.userAgent)){
	  
		// window.location="http://"+url+"/Mozi/app/down"
		
	  alert("微信中无法直接下载，点击右上角选择浏览器打开,即可下载");//这是微信平台下浏览器
	   
	  var shade = document.getElementById("shade");
	  shade.innerHTML = '<p style="color: white;">如果在微信中无法直接下载，可以点击右上角选择在<span>浏览器中打开</span>即可下载。</p><img id="img" src="https://img.cdn.zhaoshang800.com/img/2018/06/12/broker/f8589779-5a3b-47c0-85dc-d5985e9ca83f.png" alt="pic">';
  }
}
checkPlatform()

</script>
</html>