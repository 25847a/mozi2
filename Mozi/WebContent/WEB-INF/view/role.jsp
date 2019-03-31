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
				<li><a href="javascript:return;">菜单管理</a></i></li>

			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-edit"></i>菜单管理
					</div>
				</div>
				<div class="portlet-body flip-scroll">
					<div class="row-fluid">
						<div class="span8">
							<div class="btn-group">
								<a href="javascript:void(0)">
									
								</a>
							</div>
							<div class="btn-group">
								<a href="javascript:void(0)">
									<button class="btn green" onclick="addrole();">&nbsp;添加</button>
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
								<th>角色ID</th>
								<th>角色名称</th>
								<th>描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="role" items="${pageModel.list}">
								<tr>
									<td>${role.id}</td>
									<td>${role.roleName}</td>
									<td>${role.descn}</td>
									
									<td><a onclick="editarole(${role.id})"><i
											class="icon-edit"></i></a>&nbsp;<a
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
		//编制
		function editarole(id) {
	
		var editaroleas = function(){
		
			//修改
			var rolename = $("#rolename1").val();
			var roledescription = $("#roledescription1").val();
			var roleid = $("#roleid").val();
			var data = {
				"rolename" : rolename,
				"roledescription" : roledescription,
				"roleid" : roleid
			};
			var _init = function(data) {
				if (data.code == 200) {
				layer.msg("修改成功！！", {
					icon : 4
				});
				} else {
				layer.msg("修改失败！！", {
					icon : 4
				});
				}
			Refresh("role/getroles");

			}
			sendAjaxMeth("role/editarole", data, _init, null);
		
		
		}
		
			var data = {
				"roleid" : id
			}

			var _init = function(data) {
				var role = data.data;
				
							var html = "<table class='table table-bordered' >"
								 	html+= "<th><input type='hidden' id='roleid' placeholder=''  value = '"+role.id+"'></th></tr>"
								    html+= "<tr><th>角色名称</th><th><input type='email' class='form-control' id='rolename1' placeholder='"+role.roleName+"' name='name' ></th></tr>"
									html+= "<tr><th>角色描述</th><th><input type='email' class='form-control' id='roledescription1' placeholder='"+role.descn+"' name='name' ></th></tr>"
									html+= "</table>"
                  message("修改一级菜单", html, function(){}, editaroleas);
			}
			
			sendAjaxMeth("role/getroleId", data, _init, null);
		}

	

//删除
		function deleterole(id) {
		var deleteRole = function(){
		var data = {
				"roleid" : id
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
			Refresh("role/getroles");
			}
			sendAjaxMeth("role/deleterole", data, _init, null);
		}
			
		
           message("提示", "是否删除菜单？", function(){}, deleteRole);
		}

		function searchList() {
		toPage(0, "role/getroles");
	}

	
		function addrole() {
			      var czaddrole =function(){
			  	  var rolename = $("#rolename").val();
			  	  var roledescription =$("#roledescription").val();
			  	 var data ={"rolename":rolename,"roledescription" :roledescription};
			  	 var _init=function(data){
			  	 Refresh("role/getroles");
			  	 }
			  	 sendAjaxMeth("role/addrole", data, _init, null);
			  	 
			  	 
			    }
						var html ="<form action='role/addrole' method='post'enctype='multipart/form-data'><table class='table table-bordered' >";
								  html+= "<tr><th>角色名称</th><th><input type='email' class='form-control' id='rolename' placeholder='角色名称' name='rolename' ></th></tr>";
								  html+=  "<tr><th>角色描述</th><th><input type='email' class='form-control' id='roledescription' placeholder='角色描述' name='roledescription' ></th></tr>";
								  html+="</table></form>";
               message("添加一级菜单", html, function(){}, czaddrole);
		}


		
</script>