package com.sy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sy.utils.Config;

/**
 * 下载App http://192.168.1.103:8080/Mozi/app/index
 * @author Administrator
 *
 */
@Controller
@RequestMapping({"app"})
public class DownAppController {
	private final static Logger logger = LoggerFactory.getLogger(DownAppController.class);

	
	@RequestMapping(value = "index")
	public ModelAndView list() {
		ModelAndView mo = new ModelAndView();
		mo.setViewName("downapp");
		return mo;
	}
	/**
	 * 下载APP
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping({"down"})
	public void serveFile(Model model, HttpServletRequest request, HttpServletResponse response) {
		try {
			String e = request.getSession().getServletContext().getRealPath("");
			File file = new File(e + "/../app/mozistar_app.apk");
			logger.info("请求下载文件的全路径：" + file.getAbsolutePath());
			if (!file.exists()) {
				response.setStatus(404);
				return;
			}
			Config config = new Config("systemConfig.properties");
			String app_version = config.getString("app_version");
			response.setHeader("Content-disposition",
					"attachment; filename=\"" + URLEncoder.encode("mozistar_app_"+app_version+".apk", "UTF-8") + "\"");
			FileInputStream in = new FileInputStream(file);
			ServletOutputStream out = response.getOutputStream();
			byte[] byteArr = new byte[1024];

			for (int len = in.read(byteArr); len > 0; len = in.read(byteArr)) {
				out.write(byteArr, 0, len);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception arg9) {
			this.logger.info("文件下载异常");
			arg9.printStackTrace();
		}

	}
}