<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  

<!-- 可选的Bootstrap主题文件（一般不用引入） -->

<div class="container-fluid" id="loglist">
	<div class="row-fluid">
		<div class="span12" style="margin-top: 10px">
			<ul class="breadcrumb">
				<li><i class="icon-home"></i> <a href="javascript:return;">主页</a><i
					class="icon-angle-right"></i></li>
				<li><a href="javascript:return;">后台管理</a></i></li>

			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-edit"></i>设备管理
					</div>
				</div>
				<div class="portlet-body flip-scroll">
					<div class="row-fluid">
						<div class="span8">
							<div class="btn-group"></div>
							<div class="btn-group"></div>
						</div>
						<div class="span4">
							<form action="" id="selectForm">
								<div class="dataTables_filter" style="float: right;">
									<input class="m-wrap medium" id="keyWord" name="keyWord"
										placeholder="imei" value="${keyWord }" type="text"> 
										
									<input type="text" id="test1" name='time' placeholder="时间" value='${time }' >	
									<select  id = "eqStatus" name="eqStatus" >
										<option  value = "0" >请选择设备状态</option>
										<option  value = "H:1" >在线</option>
										<option  value = "H:0" >离线</option>
									</select >
									<a class="btn green" id="search" onclick="searchList();">查询</a>
								</div>
							</form>
						</div>
					</div>
					<table
						class="table-bordered table-striped table-condensed flip-content"
						id="decicecolset_table">
						<thead>
							<tr>
								<th>imei</th>
								<th>主机电量</th>
								<th>主机信号</th>
								<th>蓝牙类型</th>
								<th>设备在线状态</th>
								<th>设备类型</th>
								<th>紧急联系人1</th>
								<th>紧急联系人2</th>
								<th>版本</th>
								<!-- <th>h</th>
								<th>g</th> -->
								<th>更新时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lis" items="${pagemodel.list}">
								<tr>
									<td>${lis.imei}</td>
									<td>${lis.lordpower}</td>
									<td>${lis.signalxhao}</td>
									<td>${lis.bluetoothType}</td>
									<c:if test="${lis.eqStatus=='H:1'}">
										<td>在线</td>
									</c:if>
									<c:if test="${lis.eqStatus!='H:1'}">
										<td>离线</td>
									</c:if>
									<td>${lis.eqtype}</td>
									<td>${lis.phone1}</td>
									<td>${lis.phone2}</td>
									<td>${lis.version}</td>
									<%-- <th>${lis.h}</th>
									<th>${lis.g}</th> --%>
									<td><fmt:formatDate value="${lis.updatetime}" pattern="yyyy-MM-dd HH:mm"/></td>
									<td><button class="btn green"
											onclick="clearbluetooth(${lis.imei});">&nbsp;清除蓝牙</button>&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn green" onclick="shutdown(${lis.imei});">&nbsp;关机</button>&nbsp;&nbsp;&nbsp;
										<button class="btn green" onclick="upgrade(${lis.imei});">&nbsp;升级</button>&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="btn green" onclick="selectusereq(${lis.id});">&nbsp;关联用户信息</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="row-fluid" style="margin-top: 5px;">
						<c:if test="${pagemodel.count !=0}">
							<div class="span6">
								<div id="decice_page_info" class="dataTables_info"
									style="line-height: 30px">当前第 ${pagemodel.pageNo} 页 / 共
									${pagemodel.pageCount}页 共 ${pagemodel.count} 条</div>
							</div>
							<div class="span6">
								<div class="dataTables_paginate paging_bootstrap pagination"
									style="margin: 0px; float: right;">
									<ul id="decice_pagebar">${pagemodel.pageHtm}</ul>
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
//执行一个laydate实例
laydate.render({
  elem: '#test1' //指定元素
});

$(document).ready(function() {
	var eqStatus = '${eqStatus}';
	if(eqStatus == ""){
		eqStatus = "0";
	}
	$("#eqStatus").val(eqStatus);
});

function searchList() {
	toPage(0, "equipment/list");
}

function clearbluetooth(imei){
	var _init = function(data){
		layer.msg(data.message, {
			icon : 4
		});
	};
	var data ={"imei":imei};
	sendAjaxMeth("chat/clearbluetooth", data, _init, null);
}
	
function clearbluetooth(imei){
	var _init = function(data){
		layer.msg(data.message, {
			icon : 4
		});
	};
	var data ={"imei":imei};
	sendAjaxMeth("chat/clearbluetooth", data, _init, null);
}
function upgrade(imei){
	var _init = function(data){
		layer.msg(data.message, {
			icon : 4
		});
	};
	var data ={"imei":imei};
	sendAjaxMeth("chat/upgrade", data, _init, null);
	
}
function shutdown(imei){
	var _init = function(data){
		layer.msg(data.message, {
			icon : 4
		});
	};
	var data ={"imei":imei};
	sendAjaxMeth("chat/shutdown", data, _init, null);
}


function selectusereq(id){
	var _init = function(data){
		if(data.code==200){
			 
			var html ="<table class='table table-bordered' >";
			  html+=  "<tr><th>账号</th><th>ID</th><th>昵称</th><th>角色</th><th>操作</th></tr>";
			   
			for(var i= 0 ; i <data.data.length; i++){
				if(data.data[i].role=="使用者"  ||  data.data[i].role=="观察者" ){
					html+=  "<tr><th>账号</th><th>"+data.data[i].id+"</th><th>"+data.data[i].name+"</th><th>"+data.data[i].role+"</th><th><button class='btn green' onclick='deleterole("+data.data[i].id+",this);'>&nbsp;删除</button></th></tr>";
						 
				}else {
					 html+=  "<tr><th>"+data.data[i].account+"</th><th>"+data.data[i].id+"</th><th>"+data.data[i].name+"</th><th>"+data.data[i].role+"</th><th><button class='btn green' onclick='deleterole("+data.data[i].id+",this);'>&nbsp;删除</button></th></tr>";
				}
				  
			}
			   html+="</table>";
        message("设备关联管理", html, function(){}, null);
		}else{
			layer.msg(data.message, {
				icon : 4
			});
		}
		
	};
	var data ={"eqId":id};
	sendAjaxMeth("usereq/selectuserequser", data, _init, null);

}

function deleterole(id,is){
	//$(is).parents("table").remove();
	$(".close").click();
	
	var _init = function(data){
		layer.msg(data.message, {
			icon : 4
		});
	};
	var data ={"id":id};
	sendAjaxMeth("usereq/deleteusereq", data, _init, null);
	
}
</script>