<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<style>
.imei{width:550px;height:400px;}
.imei tr td {border:1px solid #ABABAB;width:30px; height:10px;}
.button tr td {border:0px solid #ABABAB;}
#myModal{width:650px; height:550px;}
.modal-dialog{width:650px; height:550px;}
.modal-content{width:650px; height:550px;}



body{font-size:12px;}   
.lanrentuku1{
border: 0px solid #CCC;
width:1120px;
height:40px;
margin:auto;
display: table;   
*position: relative;   
overflow: hidden;  
}   
               
.lanrentuku2 {
vertical-align: middle;
display: table-cell;
*position: absolute;
*top: 50%;
} 
         
.lanrentuku3 {
*position: relative;
*top: -50%;
padding-right:10px;
}  
/*  .ul {
list-style-type:none;
} */
.ul li:nth-of-type(odd){
            background-color: #EAEAEA;
        }
.ul {
           text-align:center;
        }
.ul li{
			text-align:center;
			width:150px;
            /* border: solid #E0E0E0;
            /* border-bottom-width: 0px; 
            border-left-width: 0px;
            border-right-width: 0px;
            border-top-width: 0px; */
        }

.modal-footer {
    padding: 14px 15px 15px;
    margin-bottom: 0;
    /* text-align: justify;  */
    background-color: #f5f5f5;
    border-top: 1px solid #ddd;
    -webkit-border-radius: 0 0 6px 6px;
    -moz-border-radius: 0 0 6px 6px;
    border-radius: 0 0 6px 6px;
    -webkit-box-shadow: inset 0 1px 0 #fff;
    -moz-box-shadow: inset 0 1px 0 #fff;
    box-shadow: inset 0 1px 0 #fff;
}
.divleft{ float:left;}
.divright{ float:right;}

.nav-tabs{border-bottom:0px;}



</style>
</head>
<!-- 可选的Bootstrap主题文件（一般不用引入） -->

<div class="container-fluid" id="loglist">
	<div class="row-fluid">
		<div class="span12" style="margin-top: 10px">
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="javascript:return;">主页</a><i
					class="icon-angle-right"></i></li>
				<li><a href="javascript:return;">代理商管理</a></i></li>

			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-edit"></i>代理商管理
					</div>
				</div>
				<div class="portlet-body flip-scroll">
					<div class="row-fluid">
						<div class="span8">
							<div class="btn-group"></div>
								<div class="btn-group">
								<a href="javascript:void(0)">
									<button class="btn green" onclick="addagent();">&nbsp;添加</button>
								</a>
							</div>
						</div>
						<div class="span4">
							<form action="" id="selectForm">
								<div class="dataTables_filter" style="float: right;">
									<input class="m-wrap medium" id="keyword" name="keyword"
										placeholder="姓名" value="${keyword }" type="text"> <a
										class="btn green" id="search" onclick="searchList();">查询</a>
								</div>
							</form>
						</div>
					</div>
					<table
						class="table-bordered table-striped table-condensed flip-content"
						id="decicecolset_table">
						<thead>
							<tr>
								<th>编号</th>
								<th>姓名</th>
								<th>年龄</th>
								<th>性别</th>
								<th>手机号码</th>
								<th>地址</th>
								<th>设备数</th>
								<th>注册时间</th>
								<th>设备</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lis" items="${pagemodel.list}">
								<tr>
									<td>${lis.account}</td>
									<td>${lis.name}</td>
									<td>${lis.age}</td>
									<td>${lis.gender}</td>
									<td>${lis.phone}</td>
									<td>${lis.address}</td>
									<td>${lis.eqNumber}</td>
									<td><fmt:formatDate value="${lis.createtime}" pattern="yyyy-MM-dd HH:mm"/></td>
									<td style='width:150px;'>
									<%-- <a onclick="entry(${lis.id})">录入</a> --%>
									<ul class="nav nav-tabs" >
									<li><a class="btn green" onclick="showequiment(${lis.id})">查看</a></li>
									<li class="dropdown">
										
										<a class="btn green dropdown dropdown-toggle" data-toggle="dropdown" href="#">
											录入
										</a>
										<ul class="dropdown-menu">
											<li><a onclick="entry(${lis.id},'LN073OV1')">LN073OV1</a></li>
											<%-- <li><a onclick="entry(${lis.id},'LN073CV4')">LN073CV4</a></li> --%>
										</ul>
									</li>
									</ul>
									
									</td>
								    <td><a onclick="commodityimgedit(${lis.id})"><i
											class="icon-edit"></i></a>&nbsp;
											<%-- <a onclick="deleteimg(${lis.id})"><i
											class="icon-remove"></i></a> --%></td>
								</tr> 
							</c:forEach>
						</tbody>
					</table>
					<div class="row-fluid" style="margin-top: 5px;">
						<div class="span6">
							<div id="decice_page_info" class="dataTables_info"
								style="line-height: 30px">当前第 ${pagemodel.pageNo} 页 / 共
								${pagemodel.pageCount}页 共 ${pagemodel.count} 条</div>
						</div>
						<div class="span6">
							<div class="dataTables_paginate paging_bootstrap pagination"
								style="margin: 0px; float: right;">
								<ul id="decice_pagebar">${pagemodel.pageHtm}
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

		var checkbox = false;

		function checkNumber(theObj) {
	  		var reg = /^[0-9]*$/;
	  		if (reg.test(theObj)) {
	   			 return true;
	  	}
	  		return false;
		}
		
		
		
		function onClickHander(){ 
				checkbox = $("#checkbox").prop("checked");
				addimeis();
		}
		
		//自动添加
		function addimeis(id){
			var imei = $("input[name='imei']").val();
			var boolea = checkNumber(imei);
			if(!boolea){
				$("input[name='imei']").val(imei.substr(0,imei.length-1));
				layer.msg("请输入15个数字的imei号", {
					icon : 4
				});
			}
			if(checkbox){
				if(imei.length>15){
					$(".ul").append("<li>&nbsp;&nbsp;&nbsp;"+imei.substr(0,15)+"&nbsp;&nbsp;&nbsp;<a onclick='removeli(this)' ><i class='icon-remove' ></i></a></li>");
					 $("input[name='imei']").val(imei.substring(15,imei.length));
					}else if(imei.length==15){
						$(".ul").append("<li>&nbsp;&nbsp;&nbsp;"+imei.substr(0,15)+"&nbsp;&nbsp;&nbsp;<a onclick='removeli(this)' ><i class='icon-remove' ></i></a></li>");
						 $("input[name='imei']").val("");
					}
			}
			$('.over')[0].scrollTop =$('.over')[0].scrollHeight;
		}
		
		//手动添加
		function addimei(){
			var imei = $("input[name='imei']").val();
			if(imei.length>15){
				$(".ul").append("<li>&nbsp;&nbsp;&nbsp;"+imei.substr(0,15)+"&nbsp;&nbsp;&nbsp;<a onclick='removeli(this)' ><i class='icon-remove' ></i></a></li>");
				 $("input[name='imei']").val(imei.substring(15,imei.length));
				}else if(imei.length==15){
					$(".ul").append("<li>&nbsp;&nbsp;&nbsp;"+imei.substr(0,15)+"&nbsp;&nbsp;&nbsp;<a onclick='removeli(this)' ><i class='icon-remove' ></i></a></li>");
					 $("input[name='imei']").val("");
				}else{
					layer.msg("请输入15个数字的imei号", {
						icon : 4
					});
				}
			$('.over')[0].scrollTop =$('.over')[0].scrollHeight;
		}
		
		//撤掉li
		function removeli(obj){
			$(obj).parent().remove();
		}
		
		
		//list的录入连接
		function entry(id,model) {
			
			checkbox = false;
			var agent = function(data){
				var html ="<table class='imei'>";
				
				  html+=  "<tr>";
				  html+=  "<td rowspan='5' ><div class='over' style='height:260px;width:230px;overflow-y:auto'><ol class='ul'></ol></div></td>";
				  html+=  "<td>姓名<input type='hidden' name='agentid' value="+data.data.id+" /></td>";
				  html+=  " <td>"+data.data.name+"</td>";
				  html+=  " </tr>";
				  html+=  "<tr><td>年龄</td><td>"+data.data.age+"</td></tr>";
				  html+=  "<tr><td>性别</td><td>"+data.data.gender+"</td></tr>";
				  html+=  "<tr><td>手机号码</td><td>"+data.data.phone+"</td></tr>";
				  html+=  "<tr><td>地址</td><td>"+data.data.address+"</td></tr>";
				  
				  //底部的录入操作
				  html+= "<tr><td colspan='3' ><style='height:200px;' >";
				  html+= "<table class='button' >";
				  html+= "<tr><td>";
				  html+= " <div class='lanrentuku1' style='height:100px;width:550px;'>  ";
				  html+= " <div class='lanrentuku2'>  ";
						  html+= "    <div class='lanrentuku3'>";
							  html+= "    <input style='height:60px;width:400px;font-size:40px' name='imei' type='text' oninput='addimeis("+data.data.id+")'  placeholder='设备号imei' />自动添加<input type='checkbox' onclick='onClickHander(this)' id='checkbox' />";
								  html+= "    </div>";
										  html+= "  </div>  ";
											  html+= " </div> ";

				  html+=  "</td></tr>";			
				  html+= "<tr><td><div style='height:30px;width:550px;' ><input  type='button' onclick='addimei()' value='添加' /></td></tr>";	
				  
				  html+= "</table>";
				  
				  html+="</div></tr>";
				  html+=  "</table>";
				  
				  message("录入设备机型"+model, html, function(){}, null);
				  
				  $("#closeBtn").remove();
				   var divstr ='<div class="divleft"><button type="button" id="closeBtn" class="btn btn-default" data-dismiss="modal">取消</button></div>';
					divstr += '<div class="divright"><button type="button" onclick="allentry(\''+model+'\')" class="btn btn-default" data-dismiss="modal">全部录入</button></div>';
					$(".modal-footer").append(divstr); 
			}
				var userid = {"id":id};
			sendAjaxMeth("management/getmanagementid", userid, agent, null);
			
			
	    }
		
		//全部录入
		function allentry(model){
			var arr = new Array();
			$(".ul").children().each(function(){
			 	arr.push($(this).text().trim());
			  });
			var msg = function(data) {
				layer.msg(data.message, {
					icon : 4
				});
				Refresh("management/agentlist");
			};
			var agentid = $("input[name='agentid']").val();
			var data = {
						"arr":arr,
						"agentid":agentid,
						"model":model,
						};
			sendAjaxMeth("management/allentry", data, msg, null);
			}
			
			
		//编辑	
		function commodityimgedit(id) {
			
			var updatecommodityimg = function(){
				
		    	var name = $("#name1").val();
		    	var age = $("#age1").val();
		    	var gender = $("#gender1").val();
		    	var phone = $("#phone1").val();
		    	var address = $("#address1").val();
		    	
			var data = {
				"name":name,
				"age":age,
				"id": id,
				"gender":gender,
				"phone":phone,
				"address":address };
			var msg = function(data) {
				layer.msg(data.message, {
					icon : 4
				});
				Refresh("management/agentlist");
			};
			sendAjaxMeth("management/updateManagement", data, msg, null);
         }
				
				var userid = {"id":id};
				var agent = function(data){
					var html ="<table class='table table-bordered' >";
					  html+=  "<tr><th>姓名</th><th><input type='email' class='form-control' id='name1' placeholder='姓名' name='name'  value='"+data.data.name+"'></th></tr>";
					  html+=  "<tr><th>年龄</th><th><input type='email' class='form-control' id='age1' placeholder='年龄' name='age'  value='"+data.data.age+"'></th></tr>";
					  html+=  "<tr><th>性别</th><th><input type='email' class='form-control' id='gender1' placeholder='性别' name='gender'  value='"+data.data.gender+"'></th></tr>";
					  html+=  "<tr><th>手机号码</th><th><input type='email' class='form-control' id='phone1' placeholder='手机号码' name='phone'  value='"+data.data.phone+"'></th></tr>";
					  html+=  "<tr><th>地址</th><th><input type='email' class='form-control' id='address1' placeholder='地址' name='address'  value='"+data.data.address+"'></th></tr>";
					  html+="</table>";
  					message("修改代理商", html, function(){}, updatecommodityimg);
					
				}
				sendAjaxMeth("management/getmanagementid", userid, agent, null);
	    }


		//删除
		function deleteimg(id) {
		var deleteimgs = function(){
		var data = {
				"id" : id
			};
			var _init = function(data) { 
				if (data.code == 200) {
				layer.msg("删除成功！！", {
					icon : 4
				});
				} else {
				layer.msg("删除失败！！", {
					icon : 4
				});
				}
			Refresh("management/agentlist");
			}
			sendAjaxMeth("management/deleteManagement", data, _init, null);
		}
           message("提示", "是否删除管理人员？", function(){}, deleteimgs);
		}

		function searchList() {
		toPage(0, "management/agentlist");
	}

	
		function addagent() {
			var addimg =function(){
		        var fd = new FormData();
		        /*var avatar = document.getElementById('avatar').files[0]; 
		    	 fd.append("avatar", avatar); */
		    	var account = $("#account").val();
		    	fd.append("account", account);
		    	var name = $("#name").val();
		    	fd.append("name", name);
		    	var age = $("#age").val();
		    	fd.append("age", age);
		    	var password = $("#password").val();
		    	fd.append("password", password);
		    	var gender = $("#gender").val();
		    	fd.append("gender", gender);
		    	var position = $("#position").val();
		    	fd.append("position", position);
		    	var phone = $("#phone").val();
		    	fd.append("phone", phone);
		    	var address = $("#address").val();
		    	fd.append("address", address);
		    	
		    
		    	
		    	var data = {
		    		"account":account,
    				"name":name,
    				"age":age,
    				"password":password,
    				"gender":gender,
    				"position":position,
    				"phone":phone,
    				"address":address,
    				"role":"代理商",
    				};
		    	
		    	
		    	var msg = function(data) {
					layer.msg(data.message, {
						icon : 4
					});
					Refresh("management/agentlist");
				};
				
		    	sendAjaxMeth("management/addagent", data, msg, null);
			}
						var html ="<table class='table table-bordered' >";
								  html+=  "<tr><th>编号</th><th><input type='text' class='form-control' id='account' placeholder='账号' name='account' oninput='accountfun(this)' ></th></tr>";
								  html+=  "<tr><th>姓名</th><th><input type='text' class='form-control' id='name' placeholder='姓名' name='name' ></th></tr>";
								  html+=  "<tr><th>年龄</th><th><input type='text' class='form-control' id='age' placeholder='年龄' name='age' ></th></tr>";
								  html+=  "<tr><th>密码</th><th><input type='text' class='form-control' id='password' placeholder='密码' name='password' ></th></tr>";
								  html+=  '<tr><th>性别</th><th><select  class="form-control" id = "gender" name="gender" value="男" ><option  value = "男" >男</option><option  value = "女" >女</option></select ></th></tr>';
								  html+=  "<tr><th>手机号码</th><th><input type='text' class='form-control' id='phone' placeholder='手机号码' name='phone' ></th></tr>";
								  html+=  "<tr><th>地址</th><th><input type='email' class='form-control' id='address' placeholder='地址' name='address' ></th></tr>";
								 /*  html+=  "<tr><th>头像</th><th><input type='file'  multiple accept='image/*' class='form-control' id='avatar'  name='avatar' ></th></tr>"; */
								  html+="</table>";
               message("添加代理商", html, function(){}, addimg);
		}
		
		
		function accountfun(obj){
			var account = $(obj).val();
			var boolea = checkNumber(account);
			if(!boolea){
				 $(obj).val(account.substr(0,account.length-1));
				layer.msg("编号只能填写数字", {
					icon : 4
				});
			}
		}

	         function showequiment (agentid){
	        	 
	        	 var data = {
	     				"agentid" : agentid
	     			};
	        	 Refresh_data("management/equipmentlist",data);
	         } 
				
	       
    
		
</script>