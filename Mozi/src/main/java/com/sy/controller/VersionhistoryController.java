package com.sy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.pojo.Versionhistory;
import com.sy.service.VersionhistoryService;

@Controller
@RequestMapping(value = "versionhistory")
public class VersionhistoryController {
	@Autowired
	private VersionhistoryService versionhistoryservice;
	
	@RequestMapping(value = "addVersionhistory")
	public ResultBase addVersionhistory(String model,String name,@RequestParam(value = "sbkfile", required = false) CommonsMultipartFile sbkfile,String description,String imei,String ziversion,String zhuversion,String compilation,String versiontype){
		new File("E:\\Project\\uploadsdk").mkdirs();
		String filename = sbkfile.getOriginalFilename();
		String filenames[] = filename.split("\\.");
		arrayUploadFile("E:\\Project\\uploadsdk", sbkfile,zhuversion+"_"+ziversion+"_"+compilation+"_"+versiontype+"."+filenames[1]);
		
		ResultBase re = new ResultBase();
		Versionhistory u = new Versionhistory();
		u.setName(name);
		u.setUrl("http://www.mzstar.cn:8080/Mozi/uploadsdk/"+zhuversion+"_"+ziversion+"_"+compilation+"_"+versiontype+"."+filenames[1]);
		u.setZiversion(ziversion);
		u.setZhuversion(zhuversion);
		u.setCompilation(compilation);
		u.setDescription(description);
		u.setVersiontype(versiontype);
		u.setCreatetime(new Date());
		u.setModel(model);
		boolean status = versionhistoryservice.addVersionhistory(u);
		if(status){
			re.setCode(200);
			re.setMessage("上传成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("上传失败！！！");
		}
		return re ;
	}
	
	
	@RequestMapping("deleteVersionhistory")
	@ResponseBody
	public ResultBase deleteVersionhistory(Integer id){
		ResultBase re = new ResultBase();
		boolean status =versionhistoryservice.deleteversionhistory(id);
		if(status){
			re.setCode(200);
			re.setMessage("删除成功！！！");
		}else {
			re.setCode(400);
			re.setMessage("删除失败！！！");
		}
		return re ;
		
	}
	
	@RequestMapping("selectVersionhistory")
	@ResponseBody
	public ResultData<Versionhistory> selectVersionhistory(){
		 ResultData<Versionhistory>  re = new  ResultData<Versionhistory> ();
		 re.setData(versionhistoryservice.selectVersionhistory());
		 re.setCode(200);
		 re.setMessage("获取成功！！！");
		 return re;
	}

	/**
	 * 上传单个文件
	 * @param path
	 * @param file
	 */
	public static void arrayUploadFile(String path ,CommonsMultipartFile file,String fliename){
		  if(!file.isEmpty()){  
                try {  
                    FileOutputStream os = new FileOutputStream(path +"/"+fliename);  
                    FileInputStream in = (FileInputStream) file.getInputStream();  
                    int len= 0;  
                    byte[] buffer = new byte[1024];
                    while ((len = in.read(buffer)) > 0) {
                    	os.write(buffer, 0, len);
					}
                    os.flush();  
                    os.close();  
                    in.close();  
                	
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
        } 
		
	}
}
