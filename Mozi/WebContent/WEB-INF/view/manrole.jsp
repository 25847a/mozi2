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
				<li><a href="javascript:return;">管理人员角色管理</a></i></li>

			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-edit"></i>管理人员角色管理
					</div>
				</div>
				<div class="portlet-body flip-scroll">
					<div class="row-fluid">
						<div class="span8">
							<div class="btn-group">
								<a href="javascript:void(0)">
									<button class="btn green" onclick="addrolemenu()">&nbsp;添加</button>
								</a>
							</div>
							<div class="btn-group"></div>
						</div>
						<div class="span4">
							<form action="" id="selectForm">
								<div class="dataTables_filter" style="float:right;"></div>
							</form>
						</div>
					</div>
					<table
						class="table-bordered table-striped table-condensed flip-content"
						id="decicecolset_table">
						<thead>
							<tr>
								<th>管理人员名称</th>
								<th>角色名称</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="rmu" items="${pageModel.list}">
								<tr>
									<td>${rmu.manname}</td>
									<td>${rmu.rolename}</td>
									


									<td><a
										onclick="deleterolemenu(${rmu.id})"><i class="icon-remove"></i></a></td>
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
		toPage(0, "manrole/list");
	}
	//角色菜单添加表，需要获取菜单进行下拉框选择才进行操作
	function addrolemenu() {
		
		    var _init = function(data) {
			var ms = data.data;
			var meus = ms.ms;
			var roles = ms.rs;
			var html = "";
			html += "<table><tr><td><h4>添加选择：</h4><div></td><tr>";
			html += "<tr><td><select id='test'>";
			for (var i = 0; i < meus.length; i++) {
				var meu = meus[i];
				html += "<option value='"+meu.id +"'>" + meu.name
						+ "</option>";
			}

			html += "</select></td><tr>";
			html += "<tr><td><select id='test1'>";
		
			for (var i = 0; i < roles.length; i++) {
				var role = roles[i];
				html += "<option value='"+role.id+"'>" + role.roleName
						+ "</option>";
			}
			html += "<tr><td></select></td><tr>";
			html += "</div></td><tr></table>";

			
			message("添加管理", html, "", addmenurole);

		}
		sendAjaxMeth("manrole/selectgetmeunroles", null, _init, null)
	}
	
	var addmenurole = function() {
				var myselect = document.getElementById("test");
				var index = myselect.selectedIndex;
				var roleid = myselect.options[index].value;
				var myselect1 = document.getElementById("test1");
				var index1 = myselect1.selectedIndex;
				var menuid = myselect1.options[index1].value;
				var data = {
					"roleid" : roleid,
					"menuid" : menuid
				};
				
				var _init = function(data) {
					if (data.code == 200) {
						layer.msg("添加成功！！", {
							icon : 4
						});
						Refresh("manrole/list");
					} else {
						if (data.code == 200) {
							layer.msg("添加成功！！", {
								icon : 4
							});
						}
						Refresh("manrole/list");
					}
					}
					sendAjaxMeth("manrole/addmenurole", data, _init, null);
				}



		//删除角色跟菜单的关联数据
		function deleterolemenu(id) {
		var deleteRolemenu = function(){
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
					Refresh("manrole/list");
			}
			sendAjaxMeth("manrole/deleterolemun", date, _init, null);
			
		
		}
			
			message("提示", "确定要删除", "", deleteRolemenu);
		}

</script>