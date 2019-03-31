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
				<li><a href="javascript:return;">版本管理</a></i></li>

			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-edit"></i>版本升级管理
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
									<button class="btn green" onclick="adduploaddownload();">&nbsp;上传到版本库</button>
								</a>
							</div>
							<div class="btn-group">
								<a href="javascript:void(0)">
									<button class="btn green" onclick="adduploaddownloaddaoge();">&nbsp;单个升级</button>
								</a>
							</div>
							<div class="btn-group">
								<a href="javascript:void(0)">
									<button class="btn green" onclick="pinglianguploaddownload();">&nbsp;批量升级</button>
								</a>
							</div>
							<div class="btn-group">
								<a href="javascript:void(0)">
									<button class="btn green" onclick="deletversion();">&nbsp;版本库记录删除</button>
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
								<th>id</th>
								<th>版本标题</th>
								<th>imei</th>
								<th>当前版本</th>
								<th>主版本号</th>
								<th>子版本号</th>
								<th>编译号</th>
								<th>版本类型</th>
								<th>版本描述</th>
								<th>上传时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="uploaddownload" items="${pageModel.list}">
								<tr>
									<td>${uploaddownload.id}</td>
									<td>${uploaddownload.name}</td>
									<c:if test="${uploaddownload.imei =='batch'}">
									<td>批量升级</td>
									</c:if>
									<c:if test="${uploaddownload.imei !='batch'}">
									
									<td>${uploaddownload.imei}</td>
									</c:if>
									<td>${uploaddownload.currentversion}</td>
									<td>${uploaddownload.zhuversion}</td>
									<td>${uploaddownload.ziversion}</td>
									<td>${uploaddownload.compilation}</td>
									
									<c:if test="${uploaddownload.versiontype =='1'}">
									<td>公测</td>
									</c:if>
									<c:if test="${uploaddownload.versiontype =='2'}">
									<td>发布</td>
									</c:if>
									<c:if test="${uploaddownload.versiontype =='3'}">
									<td>测试</td>
									</c:if>
									<td>${uploaddownload.description}</td>
									<%-- <td>${uploaddownload.createtime}</td> --%>
									<td><fmt:formatDate value="${uploaddownload.createtime}" pattern="yyyy-MM-dd HH:mm"/></td>
									<td>&nbsp;<a
										onclick="deleteuploaddownload(${uploaddownload.id})"><i class="icon-remove"></i></a></td>
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
	
function initData(obj){
	
	 var filePath = $(obj).val();
     var fileName = filePath.substring(1+filePath.lastIndexOf("\\")); //截取最后一个\位置到字符长度，也就是截取文件名 
     var name = fileName.split(".")[0]; //截取最后一个\位置到字符长度，也就是截取文件名 
	 var inputVal = name.split("_");
     if(inputVal.length!=4){
	     $(obj).val("");
			layer.msg("文件名格式不正确,请重新上传", {
				icon : 4
			});
	 	
     }else{
	     $("#zhuversion").val(inputVal[0])
		 $("#ziversion").val(inputVal[1]);
		 $("#compilation").val(inputVal[2]);
		 $("#test1").val(inputVal[3]);
		 $("#model").val(inputVal[0]);
     }
}

function deleteuploaddownload(id) {
var deleteuploaddownload = function(){
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
	Refresh("uploaddownload/list");
	};
	sendAjaxMeth("uploaddownload/deleteuploaddownload", data, _init, null);
}
	

   message("提示", "是否删除版本？", function(){}, deleteuploaddownload);
}

function searchList() {
toPage(0, "uploaddownload/list");
}



	
		function adduploaddownload() {
			   var addimg =function(){
			        var fd = new FormData();
			    	var sbkfile = document.getElementById('sbkfile').files[0];
			    	fd.append("sbkfile", sbkfile);
			    	var name = $("#name").val();
			    	fd.append("name", name);
			    	var model = $("#model").val();
			    	fd.append("model", model);
			    	var description = $("#description").val();
			    	fd.append("description", description);
			    	var zhuversion = $("#zhuversion").val();
			    	fd.append("zhuversion", zhuversion);
			    	var ziversion = $("#ziversion").val();
			    	fd.append("ziversion", ziversion);
			    	var compilation = $("#compilation").val();
			    	fd.append("compilation", compilation);
			    	var myselect1 = document.getElementById("test1");
					var index1 = myselect1.selectedIndex;
					var versiontype = myselect1.options[index1].value;
			    	fd.append("versiontype", versiontype);
			    	var xhr = new XMLHttpRequest();
				xhr.upload.addEventListener("progress", uploadProgress, false);
				xhr.addEventListener("load", uploadComplete, false);
				xhr.addEventListener("error", uploadFailed, false);
				xhr.addEventListener("abort", uploadCanceled, false);
				//发送请求 
				xhr.open("POST", "versionhistory/addVersionhistory", true);
				xhr.send(fd);
			    }
						var html ="<table class='table table-bordered' >";
								  html+=  "<tr><th>版本说明</th><th><input type='email' class='form-control' id='name' placeholder='版本说明' name='版本说明' ></th></tr>";
								  html+=  "<tr><th>主版本号</th><th><input type='email' class='form-control' id='zhuversion' placeholder='主版本号' name='主版本号' ></th></tr>";
								  html+=  "<tr><th>子设备号</th><th><input type='email' class='form-control' id='ziversion' placeholder='子设备号' name='子设备号' ></th></tr>";
								  html+=  "<tr><th>编译号</th><th><input type='email' class='form-control' id='compilation' placeholder='编译号' name='编译号' ></th></tr>";
								  html+= "<tr><th>版本描述</th><th><input type='email' class='form-control' id='description' placeholder='描述' name='描述' ></th></tr>";
								  html+= "<tr><th>版本类型</th><th><select id='test1'>";
								  
								  html+= "<option value='1'>beta</option>";
								  html+= "<option value='2'>release</option>";
								  html+= "<option value='3'>test</option>";
								  html += "</select></th></tr>";
								  html+= "<tr><th>机型</th><th>";
								  html+= "<select name='model' id='model' >";
								  html+= "<option value='LN073CV4'>LN073CV4</option>";
								  html+= "<option value='LN073OV1'>LN073OV1</option>";
								  html+= "</select>";
								  html+= "</th></tr>";		
								  html+= "<tr><th>sbk</th><th><input type='file'  onchange='initData(this)' class='form-control' id='sbkfile'  name='sbkfile' ></th></tr>";
								  html+= "</table>";
              message("上传版本", html, function(){}, addimg);
		}
		
		function pinglianguploaddownload() {
			var _init = function(data){
				   var addimg =function(){
				        var fd = new FormData();
				    	fd.append("imei", "batch");
				    	var myselect1 = document.getElementById("test1");
						var index1 = myselect1.selectedIndex;
						var versiontype = myselect1.options[index1].value;
				    	fd.append("id", versiontype);
				    	var xhr = new XMLHttpRequest();
					xhr.upload.addEventListener("progress", uploadProgress, false);
					xhr.addEventListener("load", uploadComplete, false);
					xhr.addEventListener("error", uploadFailed, false);
					xhr.addEventListener("abort", uploadCanceled, false);
					//发送请求 
					xhr.open("POST", "uploaddownload/addUploaddownload", true);
					xhr.send(fd);
				    }
				   var ht = data.data;
							var html ="<table class='table table-bordered' >";
									  html+= "<tr><th>版本类型</th><th><select id='test1'>";
									  for(var i = 0; i<ht.length; i++){
										  var h = ht[i];
										  html+= "<option value='"+h.id+"'>"+h.name+"</option>";
									  }
									
									  html += "</select></th>";
									  html+="</table>";
	          message("升级版本", html, function(){}, addimg);
			}
       
       sendAjaxMeth("versionhistory/selectVersionhistory", null, _init, null);
		
		}
		
		
		function adduploaddownloaddaoge(){
			var _init = function(data){
				   var addimg =function(){
				        var fd = new FormData();
				    	var imei = $("#imei").val();
				    	fd.append("imei", imei);
				    	var myselect1 = document.getElementById("test1");
						var index1 = myselect1.selectedIndex;
						var versiontype = myselect1.options[index1].value;
				    	fd.append("id", versiontype);
				    	var xhr = new XMLHttpRequest();
					xhr.upload.addEventListener("progress", uploadProgress, false);
					xhr.addEventListener("load", uploadComplete, false);
					xhr.addEventListener("error", uploadFailed, false);
					xhr.addEventListener("abort", uploadCanceled, false);
					//发送请求 
					xhr.open("POST", "uploaddownload/addUploaddownload", true);
					xhr.send(fd);
				    }
				   var ht = data.data;
							var html ="<table class='table table-bordered' >";
								      html+= "<tr><th>imei</th><th><input type='email' class='form-control' id='imei' placeholder='imei' name='imei' ></th></tr>";
									  html+= "<tr><th>版本类型</th><th><select id='test1'>";
									  for(var i = 0; i<ht.length; i++){
										  var h = ht[i];
										  html+= "<option value='"+h.id+"'>"+h.name+"</option>";
									  }
									
									  html += "</select></th>";
									  html+="</table>";
	          message("升级版本", html, function(){}, addimg);
			}
          
          sendAjaxMeth("versionhistory/selectVersionhistory", null, _init, null);
  		
		}
      
		 //上传文件中所触发的事件；
		function uploadProgress(evt) {
			

          }
          //上传好文件后所触发的事件；
          	function uploadComplete(evt) {
			Refresh("uploaddownload/list");

          }
          	function uploadFailed(evt) {
			

          }
          //文件上传失败
          	function uploadCanceled(evt) {
			

          }
          
       
          function deletversion(){
        		var _init = function(data){
 				   var addimg =function(){
 				       
 				    }
 				   var ht = data.data;
 							var html ="<table class='table table-bordered'  id='versionid'>";
 									  for(var i = 0; i<ht.length; i++){
 										  var h = ht[i];
 										  html+= "<tr><td value='"+h.id+"'>"+h.name+"</td><td><button class='btn green' onclick='deletevsersions("+h.id+");'>&nbsp;删除</button></td></tr>";
 									  }
 									
 									  html+="</table>";
 	          message("升级版本", html, function(){}, addimg);
 			}
           
           sendAjaxMeth("versionhistory/selectVersionhistory", null, _init, null);
   		
 		}
          
          function deletevsersions(id){
        	  var _deleteinit=function(data){
        		  var _init = function(data){
    				  
   				            var ht = data.data;
   							var html ="";
   									  for(var i = 0; i<ht.length; i++){
   										  var h = ht[i];
   										  html+= "<tr><td value='"+h.id+"'>"+h.name+"</td><td><button class='btn green' onclick='deletevsersions("+h.id+");'>&nbsp;删除</button></td></tr>";
   									  }
   								 $("#versionid").html(html);	  
                };
             
           	  sendAjaxMeth("versionhistory/selectVersionhistory", null, _init, null);
             
        	  }
        	
        	  var data ={"id" :id};
          sendAjaxMeth("versionhistory/deleteVersionhistory", data, _deleteinit, null);
  		
          }
        
</script>