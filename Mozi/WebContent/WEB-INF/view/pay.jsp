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
	.layui-m-layer0 .layui-m-layerchild {
    width: 70% !important;
	}

	.strong{
		padding-bottom:5px;
	}
	.zfb{
	
	    padding-left:40px;
        padding-right:20px;
	}
	
	.wx {
	
	    padding-left:40px;
        padding-right:20px;
	}
	.r2{
	
		height:20px;
		color:blue;
	}
	
	.ljcz{
	
		width:80%;
		height:40px;
		font-size:25px;
		color : #fff;
		background-color: #5CACEE;
		 border-radius: 5px;
		
	}
	.hr1{border: solid 1.2px #A8A8A8;}
	.hr2{border: solid 1px #EBEBEB;}
	input {
		border:0px;
		height:50px;font-size:30px;
		outline:none;
		width:80%;
	}
    .header {
        position: absolute;
        top: 0px;
        left: 0px;
        right: 0px;
        height: 60px;
        background-color: #fff;
        color: #000;
    }

    .content {
        margin-top: 100px;
    }

    .greenbg {
        width: 100%;
        background-color: #0f0;
    }

    .greenbr {
        text-align: center;
        border: solid 1px ;
        border-radius: 5px;
        border-color : #5CACEE;
        color: #5CACEE;
        padding-left:0px;
        padding-right:0px;
    }
	.addvideo{
		 padding-top:5px;
		 padding-down:10px;
	}
    .submit {
        height: 45px;
    }

    .fit {
        width: 100%;
    }

    .row {
        margin-right: 0px;
   		 margin-left: 0px;
        margin-top: 10px;
    }

    .panal {
        width: 80%;
        margin: 0 auto;
        margin-bottom: 30px;
    }

    .box {
        margin-bottom: 20x;
    }
    
	a:link {color: blue; text-decoration:none;} 
	a:active:{color: red;text-decoration:none; } 
	a:visited {color:purple;text-decoration:none;} 
	a:hover {color: red; text-decoration:none;} 
--------------------- 
</style>
<script  type="text/javascript">


		//让本页面的手机自带返回按钮失效
		$(function() {
		　　if (window.history && window.history.pushState) {
		　　$(window).on('popstate', function () {
		　　window.history.pushState('forward', null, '#');
		　　window.history.forward(1);
		　　});
		　　}
			//在IE中必须得有这两行
		　　window.history.pushState('forward', null, '#'); 
		　　window.history.forward(1);
	　　});

		
		//监听浏览器返回按钮
	/* $(function(){ 
		pushHistory(); 
		window.addEventListener("popstate", function(e) { 
			window.location.href = "/Mozi/h5/index";
		}, false); 
		function pushHistory() { 
		var state = { 
		title: "title", 
		url: "#"
		}; 
			window.history.pushState(state, "title", "#"); 
		} 
	});
 */
    function getByClass(parent, cls) {
        if (parent.getElementsByClassName) {
            return parent.getElementsByClassName(cls);
        } else {
            var res = [];
            var reg = new RegExp(' ' + cls + ' ', 'i')
            var ele = parent.getElementsByTagName('*');
            for (var i = 0; i < ele.length; i++) {
                if (reg.test(' ' + ele[i].className + ' ')) {
                    res.push(ele[i]);
                }
            }
            return res;
        }
    }
    
    

    function get(index,obj) {
    	
    	var h4 = $(obj).children()[0];
    	var text  = $(h4).text();
    	var phone = $("input[name='phone']").val();
    	$("input[name='totalAmount']").val(text);
    	if(phone==''||phone==null){
    		layer.open({content: '请输入手机号码',btn: 'OK'});
    		return;
    	}
    	if(phone.length!=11){
    		layer.open({content: '号码不正确',btn: 'OK'});
    		return;
    	}
    	
    	var index = layer.open({type: 2});
    	$.post("h5/checkPhoneNum",{"phone":phone},
    			function(result){
    				layer.close(index);
    				if(result==false){
    					layer.open({content: '号码不匹配,请重新输入',btn: 'OK'});
    				}else{
    					$(".f-overlay").show();
    					$(".addvideo").fadeIn();
    					var str = phone +' 充值话费 '+text+' 元';
    					$("#label").text(str);
    				}
		  });
    	cz('zfb');
    }   
    
    
    function cal(){
		$(".f-overlay").hide();
		$(".addvideo").hide();
	}
    
    function cz(obj){
    
    	$(".zfb").append('<img align="right"  class="falseico" src="h5/images/false.png" >');
    	$(".wx").append('<img align="right"  class="falseico" src="h5/images/false.png" >');
    	$(".trueico").remove();
    	$(".falseico").remove();
    	if(obj=='zfb'){
    		
    		$(".zfb").append('<img align="right"  class="trueico" src="h5/images/true.png" >');
    		$(".wx").append('<img align="right"  class="falseico" src="h5/images/false.png" >');
    		
    	}else{
    		
    		$(".zfb").append('<img align="right"  class="falseico" src="h5/images/false.png" >');
    		$(".wx").append('<img align="right"  class="trueico" src="h5/images/true.png" >');
    	}
    	$("input[name='pay']").val(obj);
    }
    
    
</script>

<body>
		<br>
       <!-- <h4><a href="javascript:history.go(-1);"><strong>＜  </strong></a>充值中心</h4> -->
       <!--<h4><!-- <a href="javascript:window.opener=null;window.close();"><strong>＜  </strong></a>充值中心</h4>-->
     	<!-- <hr class='hr1'/>    target="_blank"-->
     <form action="/Mozi/h5/yesPay" method="post"  >
		<input type="hidden" name="totalAmount" >
		<input type="hidden" name="pay" >
     	<div class="panal">
            <div><h4>充值号码</h4></div>
            <br>
            <div align="center"><input type="number"  id="uid" name="phone" placeholder="输入手机号码" 
            	value="${phone}" ></div>
        </div>
        <div class="panal">
       	 <h4>充值话费</h4>
            <div class="row">
                <div class="col-xs-5 greenbr" onclick="get(0,this)">
                   <!--  <strong>10元宝</strong> -->
                     <h4>20</h4>
                     <h6>售价：20.00元</h6>
                </div>
                <div class="col-xs-offset-2 col-xs-5 greenbr" onclick="get(1,this)">
                <!--  <strong>10元宝</strong> -->
                     <h4>30</h4>
                     <h6>售价：30.00元</h6>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-5 greenbr" onclick="get(2,this)">
                    <!-- <strong>50元宝</strong> -->
                     <h4>50</h4>
                     <h6>售价：50.00元</h6>
                </div>
                <div class="col-xs-offset-2 col-xs-5 greenbr" onclick="get(3,this)">
                    <!-- <strong>100元宝</strong> -->
                     <h4>100</h4>
                     <h6>售价：100.00元</h6>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-5 greenbr" onclick="get(4,this)">
                   <!--  <strong>300元宝</strong> -->
                  	 <h4>200</h4>
                     <h6>售价：200.00元</h6>
                </div>
                <div class="col-xs-offset-2 col-xs-5 greenbr" onclick="get(5,this)">
                    <!-- <strong>500元宝</strong> -->
                     <h4>300</h4>
                     <h6>售价：300.00元</h6>
                </div>
            </div>
        </div>
        <!--遮罩层-->
            <div class="f-overlay"></div>
            <div class="addvideo" style="display: none;" align="center">
            <div class="row r2">
            	<div class="col-xs-4" align="left" onclick="cal()"><h4><strong>╳</strong></h4></div>
          		<div class="col-xs-4" align="center" ><h4><strong>确认充值</strong></h4></div>
          	</div>	
          	<hr class='hr2'/>
        		  <h4 id='label'></h4> 
          	<hr class = 'hr2'/>
           	  <div class="row">
	            <div  align="left"><h4 class="zfb" onclick="cz('zfb')" ><img class='strong' src="h5/images/zfb.jpg" ><strong>&nbsp;&nbsp;支付宝钱包支付</strong>
	            <img  align="right" class="falseico" src="h5/images/false.png" >
	            </h4>
	            </div> 
	            <br>
	            <div align="left"><h4  class="wx" onclick="cz('wx')" ><img class='strong' src="h5/images/wx.png" ><strong>&nbsp;&nbsp;微信支付</strong>
					 <img  align="right" class="falseico" src="h5/images/false.png" >	         	 
	            </h4>
	            </div>
	            <br>
	            <div > 
	            	<input class="ljcz" type="submit"  value="立即充值">
	            </div>
	            <br>
            </div>
            </div>
            </form>
</body>

</html>
