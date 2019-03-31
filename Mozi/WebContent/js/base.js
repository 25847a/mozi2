/*
 * 定义常量
 */

var resultCode = {"error":500,"success":200};


/* url  请求的url
 * data	请求的数据
 * sfun	执行成功要执行的方法
 * loandDiv  要显示加载层的方法
 * spams	请求成功后要带给成功函数的数据
 * async	异步获取同步  默认异步 true
 * sendType  发送的类型默认post
 * resultType  返回的数据类型默认json
 * efun  异常后要执行的方法
 */
function requestMthod(url,data,sfun,resultType,loandDiv,spams,async,sendType,efun){
	if(typeof(async) == 'undefined' || async == null){
		async = true;
	}
	if(typeof(sendType) == 'undefined' || sendType == null){
		sendType = "post";
	}
	if(typeof(resultType) == 'undefined' || resultType == null){
		resultType = "json";
	}
	 $.ajax({
	      type:"post",
	      url:url+"?math="+Math.random(),
	      async:async,
	      data:data,
	      datatype:resultType,
	      beforeSend:function(){
	    	 
	      },
	      complete:function(){
	    	 
	      },
	      success:function(result){
	    	if(typeof(sfun) != 'undefined' && sfun!= null){
	    		 if(typeof(spams) == 'undefined' || spams == null){
		    		 sfun(result);
		    	 }else{
		    		 sfun(data,spams);
		    	 }
	    	}
	      },
	      error:function(XMLHttpRequest,textStatus,errorThrown){
	    	  if(!typeof(efun) == 'undefined' && efun != null){
	    		  efun();
	    	  }else{
	    		  throw errorThrown;
	    	  }
	      }
	    });
}

/*
 * title 标题
 * content : 内容
 * cfun:点击关闭要执行的方法
 */
function message(title,content,cfun,sfun){
	$("#myModal").remove();
	var buttonHtml = "";
	if(typeof(sfun) != 'undefined'  && sfun != null){
		buttonHtml +=  "            <button type = \"button\" id=\"successBtn\" style= 'background:#4d90fe;hover:color:#4d90fe' class = \"btn btn-success\">\n" +
		"               确定\n" +
		"            </button>\n" ;
	}
	if(typeof(cfun) != 'undefined' && cfun != null){
		buttonHtml += "  <button type = \"button\" id=\"closeBtn\" class = \"btn btn-default\" data-dismiss = \"modal\">\n" +
					  "               取消\n" +
					  "            </button>\n" ;
	}
	
	var message = "<div  class = \"modal fade\" id = \"myModal\" tabindex = \"-1\" role = \"dialog\" style=\"z-index:10000\" \n" +
	"   aria-labelledby = \"myModalLabel\" aria-hidden = \"true\">\n" +
	"   \n" +
	"   <div class = \"modal-dialog\">\n" +
	"      <div class = \"modal-content\">\n" +
	"         \n" +
	"         <div class = \"modal-header\">\n" +
	"            <button type = \"button\" class = \"close\" data-dismiss = \"modal\" aria-hidden = \"true\">\n" +
	"                  \n" +
	"            </button>\n" +
	"            \n" +
	"            <h4 class = \"modal-title\" id = \"myModalLabel\">\n" + title +
	"            </h4>\n" +
	"         </div>\n" +
	"         \n" +
	"         <div class = \"modal-body\">\n" +
	"            " +content +
	"         </div>\n" +
	"         \n" +
	"         <div class = \"modal-footer\">\n" + buttonHtml +
	"         </div>\n" +
	"         \n" +
	"      </div><!-- /.modal-content -->\n" +
	"   </div><!-- /.modal-dialog -->\n" +
	"  \n" +
	"</div><!-- /.modal -->";
	
	$("body").append(message);
	if(typeof(cfun) != 'undefined' && cfun != null){
		 $("#myModal #closeBtn").bind("click",function(){
			 cfun();
			 $("#myModal").modal("hide");
		 });
	}
	
	if(typeof(sfun) != 'undefined'  && sfun != null){
		$("#myModal #successBtn").bind("click",function(){
			sfun();
			$("#myModal").modal("hide");
			
		 });
	}
	
	$("#myModal").modal('show');
}


/**
 * 该方法用户获取后台获取数据
 * @param url访问的路径
 * @param data所要带到后台的数据值
 * @param sf返回的数据
 * @param ef出异常所报的数据
 */
function sendAjaxMeth(url,data,sf,ef){
	 var ajaxFun = function(){ $.ajax({
			url : url,
			type : "post",
			data : data,
			dataType : "json",
			traditional: true, 
		    async: false,
			//成功执行
			success : function(data) {
				if(sf){
					sf(data);
				}
				
			},
			//异常执行
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				if(ef){
					ef();
				}
				layer.msg('发现异常请稍后再试。。。。', {
					icon : 4
				});
			},
			//完成请求后执行
			complete : function(XMLHttpRequest, textStatus) {
				if(layer){
					layer.closeAll('loading');
				}
				
			}
	  });
	}
	 
	setTimeout(ajaxFun,10);
}

//去特定页数
function toPage(pageNo,url) {
	//获取到整个页面的数据进行显示操作
	var sfun = function(html) {
		//alert(html);
		//判断带来的数据里面是否出现DOCTYPE  
		if (html.indexOf("DOCTYPE") > 0) {
			location.href = "login/loginIn";
		}else{
			//追加到某个地方进行显示
			$(".page-content").eq(0).html(html);
		}
		
	};
	var selectForm = $("#selectForm");
	if(!selectForm){
		selectForm = $(".page-content").append("<form id='selectForm'></form>");
	}
	selectForm.append("<input type='hidden' name = 'pageNo' value='"+pageNo+"'>");
	//定义获取方法将整个要返回的页面提添加到当前的页面；
	requestMthod(url, selectForm.serialize(), sfun);

}
function Refresh(url){
	{
		//获取到整个页面的数据进行显示操作
		var sfun = function(html) {
			
			//	alert(html);
			//判断带来的数据里面是否出现DOCTYPE  
			if (html.indexOf("DOCTYPE") > 0) {
				location.href = "login/loginIn";
			}
			//追加到某个地方进行显示
			$(".page-content").eq(0).html(html);
		}
		//定义获取方法将整个要返回的页面提添加到当前的页面；
		requestMthod(url, {}, sfun);
	}
	
}
function Refresh_data(url,data){
	{
		//获取到整个页面的数据进行显示操作
		var sfun = function(html) {
			
			//	alert(html);
			//判断带来的数据里面是否出现DOCTYPE  
			if (html.indexOf("DOCTYPE") > 0) {
				location.href = "login/loginIn";
			}
			//追加到某个地方进行显示
			$(".page-content").eq(0).html(html);
		}
		//定义获取方法将整个要返回的页面提添加到当前的页面；
		requestMthod(url, data, sfun);
	}
	
}


