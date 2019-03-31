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
				<li><a href="javascript:return;">数据管理管理</a></i></li>
			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-edit"></i>健康数据管理
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
									<input class="m-wrap medium" id="keyword" name="keyword"
										placeholder="imei号" value="${keyword}" type="text">
									<input type="text" id="test1" name='time' placeholder="时间" value='${time }'>	
									<input type="text" id="userid" name='userid' placeholder="用户ID" value='${userid }'>	
										
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
							   <th>用户ID</th>
								<th>心率</th>
								<th>高压</th>
								<th>低压</th>
								<th>微循环</th>
								<th>呼吸频率</th>
								<th>血氧</th>
								<th>心跳异常</th>
								<th>体检报告</th>
								<th>添加时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lis" items="${pagemodel.list}">
								<tr>
									<td>${lis.imei}</td>
									<td>${lis.phone}</td>
									<td>${lis.heartrate}</td>
									<td>${lis.sbpAve}</td>
									<td>${lis.dbpAve}</td>
									<td>${lis.microcirculation}</td>
									<td>${lis.respirationrate}</td>
									<td>${lis.bloodoxygen}</td>
									<td>${lis.HRV}</td>
									<td style="width: 500px;">${lis.amedicalreport}</td>
									<td><fmt:formatDate value="${lis.createtime}" pattern="yyyy-MM-dd HH:mm"/></td>
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
									<ul id="decice_pagebar">${pagemodel.pageHtm}
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
	
	
//执行一个laydate实例
laydate.render({
  elem: '#test1' //指定元素
});
		function searchList() {
		toPage(0, "health/list");
	}
</script>