<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 可选的Bootstrap主题文件（一般不用引入） -->

<div class="container-fluid" id="loglist">
	<div class="row-fluid">
		<div class="span12" style="margin-top:10px">
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="javascript:return;">主页</a><i
					class="icon-angle-right"></i></li>
				<li><a href="javascript:return;">系统管理</a></i></li>

			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-edit"></i>权限管理管理
					</div>
				</div>
				<div class="portlet-body flip-scroll">
					<div class="row-fluid">
						<div class="span8">
							<div class="btn-group"></div>
							<div class="btn-group">
								<a href="javascript:void(0)">
									<button class="btn green" onclick="addmenu()">&nbsp;添加</button>
								</a>
							</div>
						</div>
						<div class="span4">
							<form action="" id="selectForm">
								<div class="dataTables_filter" style="float:right;">
									<input class="m-wrap medium" id="keyword" name="keyword"
										placeholder="菜单名称" value="${keyword }" type="text"> <a
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
								<th>栏目名称</th>
								<th>栏目地址</th>
								<th>栏目描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="menu" items="${pageModel.list}">
								<tr>
									<td>${menu.authName}</td>
									<td>${menu.url}</td>
									<td>${menu.descn}</td>
									

									<td><a onclick="editmenu(${menu.id})"><i
											class="icon-edit"></i></a>&nbsp;<a
										onclick="deletemenu(${menu.id})"><i class="icon-remove"></i></a></td>
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
								style="margin:0px;float:right;">
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
	function searchList() {
		toPage(0, "antn/list");
	}

	//对某个管理栏进行删除操作
	function deletemenu(id) {
		var deleteMenu = function() {
			var date = {
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
				Refresh("antn/list");
			};
			sendAjaxMeth("antn/deletemenu", date, _init, null);
		}

		message("提示", "是否删除菜单？", function() {
		}, deleteMenu);
	}

	function editmenu(id) {
		var modifymenu = function() {

			var meun_name = $("#meun_name").val();
			var url = $("#url").val();
			var description = $("#description").val();
			var id = $("#id").val();

			var data = {
				"meun_name" : meun_name,
				"url" : url,
				"description" : description,
				"id" : id
			};

			var _init = function(data) {

				layer.close(ss);
				if (data.code == 200) {
					layer.msg("编辑成功！！", {
						icon : 4
					});
				} else {
					layer.msg("编辑失败！！", {
						icon : 4
					});
				}

				Refresh("antn/list");

			};

			sendAjaxMeth("antn/modifymenu", data, _init, null);

		}
		var data = {
			"id" : id
		};
		var _init = function(data) {

			var html = "<table class='table table-bordered' >"
			html += "<tr><th>栏目名称</th><th><input type='email' class='form-control' id='meun_name' placeholder='"+data.data.authName+"' name='name' value='"+data.data.authName+"'></th></tr>";
			html += "<tr><th>栏目地址</th><th><input type='email' class='form-control' id='url' placeholder='"+data.data.url+"' name='name' value='"+data.data.url+"'></th></tr>";
			html += "<tr><th>栏目描述</th><th><input type='email' class='form-control' id='description' placeholder='"+data.data.descn+"' name='name' value='"+data.data.descn+"'></th></tr>";
			html += "<input type='hidden' name='id' id='id' value='"+data.data.id+"'>";
			html += "</table>";
			message("修改菜单", html, function() {
			}, modifymenu);
		}
		sendAjaxMeth("antn/findid", data, _init, null);

	}

	//添加菜单栏数据
	function addmenu() {
		var addmenucz = function() {
			var meun_name = $("#meun_name1").val();
			var url = $("#url1").val();
			var description = $("#description1").val();
			var data = {
				"meun_name" : meun_name,
				"url" : url,
				"description" : description
			};
			var _init = function(data) {
				if (data.code == 200) {
					layer.msg("添加成功！！", {
						icon : 4
					});
				} else {
					layer.msg("添加失败！！", {
						icon : 4
					});
				}
				Refresh("antn/list");
			};
			sendAjaxMeth("antn/addmenucz", data, _init, null);
		};
		var html = "<table class='table table-bordered' >";
		html += "<tr><th>栏目名称</th><th><input type='email' class='form-control' id='meun_name1' placeholder='栏目名称' name='name' ></th></tr>";
		html += "<tr><th>栏目地址</th><th><input type='email' class='form-control' id='url1' placeholder='栏目地址' name='name' ></th></tr>";
		html += "<tr><th>栏目描述</th><th><input type='email' class='form-control' id='description1' placeholder='栏目描述' name='name' ></th></tr>";
		html += "</table>";
		message("添加菜单", html, function() {
		}, addmenucz);
	}
</script>