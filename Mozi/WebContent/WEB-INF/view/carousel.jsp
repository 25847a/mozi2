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
				<li><a href="javascript:return;">素材管理</a></i></li>

			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-edit"></i>轮播图片管理
					</div>
				</div>
				<div class="portlet-body flip-scroll">
					<div class="row-fluid">
						<div class="span8">
							<div class="btn-group">
								<a href="javascript:void(0)"> </a>
							</div>
							<div class="btn-group">
								<a href="javascript:void(0)">
									<button class="btn green" onclick="addrole();">&nbsp;添加</button>
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
								<th>标题</th>
								<th>图片</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="role" items="${pageModel.list}">
								<tr>
									<td>${role.title}</td>
									<td><img src="${role.img}"
										style="height: 40px; width: 40px;" /></td>
									<td>&nbsp;<a
										onclick="deleterole(${role.id})"><i class="icon-remove"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="row-fluid" style="margin-top: 5px;">
					<c:if test="${pageModel.count !=0}">
						<div class="span6">
							<div id="decice_page_info" class="dataTables_info"
								style="line-height: 30px">当前第 ${pageModel.pageNo} 页 / 共
								${pageModel.pageCount}页 共 ${pageModel.count} 条</div>
						</div>
						<div class="span6">
							<div class="dataTables_paginate paging_bootstrap pagination"
								style="margin: 0px; float: right;">
								<ul id="decice_pagebar">${pageModel.pageHtm}
								</ul>
							</div>
						</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
		//编制
	
	
	

//删除
		function deleterole(id) {
		var deleteRole = function(){
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
			Refresh("carousel/list");
			}
			sendAjaxMeth("carousel/deleteCarousel", data, _init, null);
		}
			
		
           message("提示", "是否删除广告？", function(){}, deleteRole);
		}

		function searchList() {
		toPage(0, "carousel/list");
	}

	
		function addrole() {
			   var addimg =function(){
			        var fd = new FormData();
			    	var img = document.getElementById('img').files[0];
			    	fd.append("img", img);
			    	var title = $("#title").val();
			    	fd.append("title", title);
			    	var xhr = new XMLHttpRequest();
				xhr.upload.addEventListener("progress", uploadProgress, false);
				xhr.addEventListener("load", uploadComplete, false);
				xhr.addEventListener("error", uploadFailed, false);
				xhr.addEventListener("abort", uploadCanceled, false);
				//发送请求 
				xhr.open("POST", "carousel/addCarousel", true);
				xhr.send(fd);
			    	
			    }
						var html ="<table class='table table-bordered' >";
								  html+=  "<tr><th>标题</th><th><input type='email' class='form-control' id='title' placeholder='标题' name='标题' ></th></tr>";
								  html+=  "<tr><th>图片</th><th><input type='file'  multiple accept='image/*' class='form-control' id='img'  name='img' ></th></tr>";
								  html+="</table>";
              message("添加图片", html, function(){}, addimg);
		}

		 //上传文件中所触发的事件；
		function uploadProgress(evt) {
			

          }
          //上传好文件后所触发的事件；
          	function uploadComplete(evt) {
			Refresh("carousel/list");

          }
          	function uploadFailed(evt) {
			

          }
          //文件上传失败
          	function uploadCanceled(evt) {
			

          }
		
</script>