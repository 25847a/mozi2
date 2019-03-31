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
							<div class="btn-group">
								
							</div>
						</div>
						<div class="span4">
							<form action="" id="selectForm">
								<div class="dataTables_filter" style="float: right;">
									<input class="m-wrap medium" id="keyword" name="keyword"
										placeholder="商品名称" value="${keyword }" type="text"> <a
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
								<th>用户id</th>
								<th>心率</th>
								<th>高压</th>
								<th>底压</th>
								<th>血压</th>
								<th>微循环</th>
								<th>呼吸</th>
								<th>睡眠</th>
								<th>当天步数</th>
								<th>每个小时步数</th>
								<th>每天步数目标</th>
								<th>卡里路</th>
								<th>久坐</th>
								<th>运动状态</th>
								<th>体温</th>
								<th>湿度</th>
								<th>冲撞</th>
								<th>血氧</th>
								<th>情绪</th>
								<th>心跳异常</th>
								<th>添加时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lis" items="${pagemodel.list}">
								<tr>
									<td>${lis.userId}</td>
									<td>${lis.heartrate}</td>
									<td>${lis.highpressure}</td>
									<td>${lis.bottompressure}</td>
									<td>${lis.bloodpressure}</td>
									<td>${lis.mocrocirculation}</td>
									<td>${lis.breathe}</td>
									<td>${lis.sleeping}</td>
									<td>${lis.stepWhen}</td>
									<td>${lis.stepTime}</td>
									<td>${lis.stepEach}</td>
									<td>${lis.carrieroad}</td>
									<td>${lis.sedentary}</td>
									<td>${lis.movementstate}</td>
									<td>${lis.bodytemp}</td>
									<td>${lis.humidity}</td>
									<td>${lis.crash}</td>
									<td>${lis.qxygen}</td>
									<td>${lis.mood}</td>
									<td>${lis.HRV}</td>
									<td>${lis.createtime}</td>



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
	

		function searchList() {
		toPage(0, "equipmentData/list");
	}

	
</script>