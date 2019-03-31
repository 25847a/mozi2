package com.sy.utils;

import java.util.List;

public class PageModel<T> {
	private String url;
	private Integer pageNo = 0;// 当前页数
	private Integer pageSize;//每页的数据
	private Integer pageCount;// 一共的页数
	private Boolean hasPre;//是否有上一页
	private Boolean hasNext;//是否有下一页
	private Integer count;// 总数据条数
	private String methodname = "toPage";
	private String pageHtm = "";
	private List<T> list = null;
	public void init(){
		int pageCount_x=(int)count/pageSize;
		 if(count>=pageSize){
	            this.pageCount = count%pageSize==0?pageCount_x:pageCount_x+1;
	        }else{
	            this.pageCount = 1;
	        }
		 if(pageNo > pageCount){
			 pageNo = pageCount;
		 }
		 
		 if(pageNo > 1){
			 hasPre = true;
		 }else{
			 hasPre = false;
		 }
		 
		 if(pageNo < pageCount){
			 hasNext = true;
		 }else{
			 hasNext = false; 
		 }
		 getPageHtm(); 
	}
	public PageModel(Integer pageNo, Integer pageSize, Integer count, List<T> list,String url) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.count = count;
		this.list = list;
		this.url = url;
	}
	public PageModel(Integer pageNo, Integer pageSize, Integer count, List<T> list,String methodname,String url) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.count = count;
		this.list = list;
		this.methodname = methodname;
		this.url = url;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Boolean getHasPre() {
		return hasPre;
	}
	public void setHasPre(Boolean hasPre) {
		this.hasPre = hasPre;
	}
	public Boolean getHasNext() {
		return hasNext;
	}
	public void setHasNext(Boolean hasNext) {
		this.hasNext = hasNext;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageModel [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", pageCount=" + pageCount + ", hasPre=" + hasPre
				+ ", hasNext=" + hasNext + ", count=" + count + ", list="
				+ list + "]";
	}
	public String getMethodname() {
		return methodname;
	}
	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}
	public String getPageHtm() {
		String sContent="";
		if(pageCount > 0){
				 if(pageNo==1){
		        	 sContent="<li class='active'><a href='javascript:;'>F<span class=\'hidden-480\'>首页</span></a></li>";
		         }else{
		        	 sContent="<li><a href=\"javascript:"+methodname+"(1,'"+url+"');\">首页</span></a></li>";
		         }
			    
				  if (hasPre==true) {
				    	 int pre = pageNo-1;
				    	 sContent+="<li><a href=\"javascript:"+methodname+"("+pre+",'"+url+"');\">P<span class='hidden-480'>上一页</span></a></li>";
				     }else{
				         sContent+="<li class='active'><a href='javascript:;'>P<span class='hidden-480'>上一页</span></a></li>";
				   }
			
			     int startIndex = (pageNo - 2 > 0)? pageNo - 2 : 1;  
			     //i <= pagesize 改为 i<=5 只显示5个页码
			     for(int i = 1; i <= 5 && startIndex <= pageCount && pageCount > 1; startIndex++, i++) {
			       if(startIndex == pageNo) {
			    	     sContent+="<li class='active'><a href='javascript:;'>"+startIndex + "</a></li>";
			             continue;
			         }
			            sContent+="<li><a href=\"javascript:"+methodname+"("+startIndex+",'"+url+"');\">"+startIndex+"</a></li>";
			     }
			     
				 if (hasNext==true) {
			    	 int next = pageNo+1;
			    	 sContent+="<li><a href=\"javascript:"+methodname+"("+next+",'"+url+"');\"><span class='hidden-480'>下一页</span>N</a></li>";
			     }else{
			    	 sContent+="<li class='active'><a href='javascript:;'><span class='hidden-480'>下一页</span>N</a></li>";
			     }
			
			 	if(pageNo == pageCount){
			 		sContent+="<li  class='active'><a href='javascript:;'><span class='hidden-480'>末页</span>L</a></li>";
			 	}else{
			 		sContent+="<li><a href=\"javascript:"+methodname+"("+pageCount+",'"+url+"');\"><span class='hidden-480'>末页</span>L</a></li>";
			 	}
		}
		return sContent;
	}
	public void setPageHtm(String pageHtm) {
		this.pageHtm = pageHtm;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
   
}
