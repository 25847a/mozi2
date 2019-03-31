package com.sy.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 * 
 * @author 帝血弑天——DDM——
 * 
 */
public class FileUtil {
	private static SystemConfig config;
	/**
	 * 保存
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static String save(MultipartFile items_pic) throws Exception {

		// 图片原始名称
		String originalFilename = items_pic.getOriginalFilename();
		// 上传图片
		if (items_pic != null && originalFilename != null
				&& originalFilename.length() > 0) {
			try {
				// 存储图片的物理路径
				String pic_path = SystemConfig.getValue("uploadPath");
				// 新图片名称
				String newFileName = UUID.randomUUID()
						+ originalFilename.substring(originalFilename
								.lastIndexOf("."));
				// 新图片
				File newFile = new File(pic_path, newFileName);
				// 判断文件夹是否存在不存在就创建
				if(!newFile.exists()){
					newFile.mkdirs();
				}
				
				// 将内存中的数据写入磁盘
				items_pic.transferTo(newFile);
				// 返回存储路径 
				return newFile.getAbsolutePath();
			} catch (Exception e) {
				e.printStackTrace();
				throw e; // 抛出异常
			}
		}
		return null;
	}
	/**
	 * 删除
	 * @param path
	 * @return
	 */
	public static void delete(String path){
		File file = new File(config.getValue("uploadPath")+"/"+path);
		// 判断是否存在 存在 就删除
		if(file.exists()){
			file.delete();
		}
	}
}
