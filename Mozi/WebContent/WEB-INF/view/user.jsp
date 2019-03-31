<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 可选的Bootstrap主题文件（一般不用引入） -->

<div class="container-fluid" id="loglist">
	<div class="row-fluid">
		<div class="span12" style="margin-top: 10px">
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="javascript:return;">主页</a><i
					class="icon-angle-right"></i></li>
				<li><a href="javascript:return;">用户管理</a></i></li>

			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-edit"></i>用户管理
					</div>
				</div>
				<div class="portlet-body flip-scroll">
					<div class="row-fluid">
						<div class="span8">
							<div class="btn-group"></div>
								<div class="btn-group">
								<a href="javascript:void(0)">
									<button class="btn green" onclick="addttxiang();">&nbsp;添加</button>
								</a>
							</div>
						</div>
						<div class="span4">
							<form action="" id="selectForm">
								<div class="dataTables_filter" style="float: right;">
									<input class="m-wrap medium" id="keyword" name="keyword"
										placeholder="关键字" value="${keyword }" type="text"> <a
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
								<th>账号</th>
								<th>姓名</th>
								<th>角色</th>
								<th>年龄</th>
								<th>性别</th>
								<th>手机号码</th>
								<th>地址</th>
								<th>微信</th>
								<th>QQ</th>
								<th>注册时间</th>
								<th>最后登陆时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lis" items="${pagemodel.list}">
								<tr>
									<td>${lis.account}</td>
									<td>${lis.name}</td>
										<td>${lis.role}</td>
									<td>${lis.age}</td>
									<td>${lis.gender}</td>
									<td>${lis.phone}</td>
									<td>${lis.address}</td>
									<td>${lis.wechat}</td>
									<td>${lis.qq}</td>
									<td>${lis.createtime}</td>
									<td>${lis.atlasttime}</td>
								
									<%-- <td><a onclick="commodityimgedit(${lis.id})"><i
											class="icon-edit"></i></a>&nbsp;<a
										onclick="deleteimg(${lis.id})"><i
											class="icon-remove"></i></a></td>
								</tr> --%>
							</c:forEach>
						</tbody>
					</table>
					<div class="row-fluid" style="margin-top: 5px;">
						<div class="span6">
							<div id="decice_page_info" class="dataTables_info"
								style="line-height: 30px">当前第 ${pagemodel.pageNo} 页 / 共
								${pagemodel.pageCount}页 共 ${pagemodel.count} 条</div>
						</div>
						<%-- <div class="span6">
							<div class="dataTables_paginate paging_bootstrap pagination"
								style="margin: 0px; float: right;">
								<ul id="decice_pagebar">${pagemodel.pageHtm}
								</ul>
							</div>
						</div> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
		//编制
		function commodityimgedit(id) {
	         var data ={"id":id};
	         var _init = function(data){
	         var updatecommodityimg=function(){
			        var fd = new FormData();
			        var avatar = document.getElementById('avatar1').files[0];
			    	fd.append("avatar", avatar);
			    	var name = $("#name1").val();
			    	fd.append("name", name);
			    	var age = $("#age1").val();
			    	fd.append("age", age);
			    
			    	var gender = $("#gender1").val();
			    	fd.append("gender", gender);
			    	var phone = $("#phone1").val();
			    	fd.append("phone", phone);
			    	var address = $("#address1").val();
			    	fd.append("address", address);
			    	var QQ = $("#QQ1").val();
			    	fd.append("qq", QQ);
			    	var wechat = $("#wechat1").val();
			    	fd.append("wechat", wechat);
			    	fd.append("id", id);
			    	var xhr = new XMLHttpRequest();
				xhr.upload.addEventListener("progress", uploadProgress, false);
				xhr.addEventListener("load", uploadComplete, false);
				xhr.addEventListener("error", uploadFailed, false);
				xhr.addEventListener("abort", uploadCanceled, false);
				//发送请求 
				xhr.open("POST", "management/updateManagement", true);
				xhr.send(fd);
	         }
	         
	         	var html ="<table class='table table-bordered' >";
								  html+=  "<tr><th>账号</th><th><input type='email' class='form-control' id='account1' placeholder='账号' name='account' value='"+data.data.account+"' ></th></tr>";
								  html+=  "<tr><th>姓名</th><th><input type='email' class='form-control' id='name1' placeholder='姓名' name='name'  value='"+data.data.name+"'></th></tr>";
								  html+=  "<tr><th>年龄</th><th><input type='email' class='form-control' id='age1' placeholder='年龄' name='age'  value='"+data.data.age+"'></th></tr>";
								  html+=  "<tr><th>性别</th><th><input type='email' class='form-control' id='gender1' placeholder='性别' name='gender'  value='"+data.data.gender+"'></th></tr>";
								  html+=  "<tr><th>手机号码</th><th><input type='email' class='form-control' id='phone1' placeholder='手机号码' name='phone'  value='"+data.data.phone+"'></th></tr>";
								  html+=  "<tr><th>地址</th><th><input type='email' class='form-control' id='address1' placeholder='地址' name='address'  value='"+data.data.address+"'></th></tr>";
								  html+=  "<tr><th>QQ</th><th><input type='email' class='form-control' id='QQ1' placeholder='QQ' name='QQ' value='"+data.data.qq+"' ></th></tr>";
								  html+=  "<tr><th>微信</th><th><input type='email' class='form-control' id='wechat1' placeholder='微信' name='wechat' value='"+data.data.wechat+"' ></th></tr>";
								  html+=  "<tr><th>头像</th><th><input type='file'  multiple accept='image/*' class='form-control' id='avatar1'  name='avatar' ></th></tr>";
								  html+="</table>";
	            message("修改管理员", html, function(){}, updatecommodityimg);
	         }
			sendAjaxMeth("management/getmanagementid", data, _init, null);
		
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
			Refresh("management/list");
			}
			sendAjaxMeth("management/deleteManagement", data, _init, null);
		}
			
		
           message("提示", "是否删除管理人员？", function(){}, deleteimgs);
		}

		function searchList() {
		toPage(0, "user/list");
	}

	
		function addcommodityimg() {
			        var addimg =function(){
			        var fd = new FormData();
			    	var avatar = document.getElementById('avatar').files[0];
			    	fd.append("avatar", avatar);
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
			    	var phone = $("#phone").val();
			    	fd.append("phone", phone);
			    	var address = $("#address").val();
			    	fd.append("address", address);
			    	var QQ = $("#QQ").val();
			    	fd.append("qq", QQ);
			    	var wechat = $("#wechat").val();
			    	fd.append("wechat", wechat);
			    	var xhr = new XMLHttpRequest();
				xhr.upload.addEventListener("progress", uploadProgress, false);
				xhr.addEventListener("load", uploadComplete, false);
				xhr.addEventListener("error", uploadFailed, false);
				xhr.addEventListener("abort", uploadCanceled, false);
				//发送请求 
				xhr.open("POST", "management/addManagement", true);
				xhr.send(fd);
			    	
			    }
						var html ="<table class='table table-bordered' >";
								  html+=  "<tr><th>账号</th><th><input type='email' class='form-control' id='account' placeholder='账号' name='account' ></th></tr>";
								  html+=  "<tr><th>姓名</th><th><input type='email' class='form-control' id='name' placeholder='姓名' name='name' ></th></tr>";
								  html+=  "<tr><th>年龄</th><th><input type='email' class='form-control' id='age' placeholder='年龄' name='age' ></th></tr>";
								  html+=  "<tr><th>密码</th><th><input type='email' class='form-control' id='password' placeholder='密码' name='password' ></th></tr>";
								  html+=  "<tr><th>性别</th><th><input type='email' class='form-control' id='gender' placeholder='性别' name='gender' ></th></tr>";
								  html+=  "<tr><th>手机号码</th><th><input type='email' class='form-control' id='phone' placeholder='手机号码' name='phone' ></th></tr>";
								  html+=  "<tr><th>地址</th><th><input type='email' class='form-control' id='address' placeholder='地址' name='address' ></th></tr>";
								  html+=  "<tr><th>QQ</th><th><input type='email' class='form-control' id='QQ' placeholder='QQ' name='QQ' ></th></tr>";
								  html+=  "<tr><th>微信</th><th><input type='email' class='form-control' id='wechat' placeholder='微信' name='wechat' ></th></tr>";
								  html+=  "<tr><th>头像</th><th><input type='file'  multiple accept='image/*' class='form-control' id='avatar'  name='avatar' ></th></tr>";
								  html+="</table>";
               message("添加管理人员", html, function(){}, addimg);
		}
		

		function addttxiang() {
			        var addttxiangimg =function(){
			        var fd = new FormData();
			    	var avatar = document.getElementById('avatar').files[0];
			    	fd.append("avatar", avatar);
			    	
			    	var xhr = new XMLHttpRequest();
				xhr.upload.addEventListener("progress", uploadProgress, false);
				xhr.addEventListener("load", uploadComplete, false);
				xhr.addEventListener("error", uploadFailed, false);
				xhr.addEventListener("abort", uploadCanceled, false);
				//发送请求 
				xhr.open("POST", "useravatar/adddavatar", true);
				xhr.send(fd);
			    	
			    }
						var html ="<table class='table table-bordered' >";
								  html+=  "<tr><th>头像</th><th><input type='file'  multiple accept='image/*' class='form-control' id='avatar'  name='avatar' ></th></tr>";
								  html+="</table>";
               message("添加管理人员", html, function(){}, addttxiangimg);
		}


	       //上传文件中所触发的事件；
			function uploadProgress(evt) {
				

	          }
	          //上传好文件后所触发的事件；
	          	function uploadComplete(evt) {
				Refresh("user/list");

	          }
	          	function uploadFailed(evt) {
				

	          }
	          //文件上传失败
	          	function uploadCanceled(evt) {
				

	          }
	          
	          
	          
		
</script>