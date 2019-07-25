package com.sy.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.nettyulit.NettyChannelMap;
import com.sy.pojo.Chat;
import com.sy.pojo.Uploaddownload;
import com.sy.service.ChatService;
import com.sy.service.UploaddownloadService;
import com.sy.utils.GB2312Utils;
import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

/**用户发送信息接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "chat", method = RequestMethod.POST)
public class ChatController {
	
	@Autowired
	private ChatService chatservice;
	@Autowired
	private UploaddownloadService uploadDownladservie;
	/**
	 * APP发文本信息到设备
	 * @param m
	 * @return
	 */
	@RequestMapping(value="sendMessage")
	@ResponseBody
	public ResultBase sendMessage(@RequestBody Map m ){
		ResultBase re = new ResultBase();
			//TODO  不明白为什么发送文本到数据库
			SocketChannel c =	(SocketChannel) NettyChannelMap.get((String)m.get("imei"));
			if(c!=null){
			try {
			Chat chat= new Chat();
			chat.setCreatedate(new Date());
			chat.setImei((String)m.get("imei"));
			chat.setText((String)m.get("message"));
			
			String txt =GB2312Utils.gb2312eecode((String)m.get("message"));
			chatservice.addChat(chat);
			c.writeAndFlush("$R04|"+txt+"\r\n");
			re.setCode(200);
			re.setMessage("发送信息成功！！！");
			} catch (Exception e) {
				e.printStackTrace();
				re.setCode(350);
				re.setMessage("只能发送文字");
			}
			}else{
				re.setCode(350);
				re.setMessage("发送失败！！！");
			}
		
		return re;
	}
	

	/**
	 * 获取信息
	 * @param m
	 * @return
	 */
	@RequestMapping(value="selectchat")
	@ResponseBody
	public ResultData<T> selectchat(@RequestBody Map m ){
		ResultData<T> re =new ResultData<T>();
		List<Chat> cs;
		try {
			cs = chatservice.selectChat((String)m.get("imei"));
		if(cs !=null && cs.size()>0 ) {
			re.setCode(200);
			re.setMessage("获取最新数据成功！！！");
			re.setData(cs);
		}else {
			re.setCode(200);
			re.setMessage("当前设备未有通讯记录！！！");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	/**
	 * 清空蓝牙状态
	 * @param imei
	 * @return
	 */
	@RequestMapping(value="clearbluetooth")
	@ResponseBody
	public ResultBase clearbluetooth(String imei ){
		ResultBase re = new ResultBase();
		try {
			Channel c =	NettyChannelMap.get(imei);
			c.writeAndFlush("$R19\r\n");
			re.setCode(200);
			re.setMessage("清空蓝牙状态成功！！！");
		} catch (Exception e) {
			re.setCode(350);
			re.setMessage("清空失败！！！");
		}
		return re;
	}
	/**
	 * 获取最新的版本号升级
	 * @param imei
	 * @return
	 */
	@RequestMapping(value="upgrade")
	@ResponseBody
	public ResultBase upgrade(String imei ){
		ResultBase re = new ResultBase();
		try {
			//获取最新的版本号
			Uploaddownload uploadDownload = uploadDownladservie
					.selectUploaddownload(imei);
			//如果为空，batch应该是数据库默认值,获取最新的一个版本
			if(uploadDownload==null){
				uploadDownload = uploadDownladservie
						.selectUploaddownload("batch");
			}
			Channel c =	NettyChannelMap.get(imei);
			c.writeAndFlush("$R16|"+ uploadDownload.getZhuversion() + "_"
					+ uploadDownload.getZiversion() + "_"
					+ uploadDownload.getCompilation() + "_"
					+ uploadDownload.getVersiontype() + "\r\n");
			re.setCode(200);
			re.setMessage("强制升级成功！！！");
		} catch (Exception e) {
			re.setCode(350);
			re.setMessage("升级失败！！！");
		}
		return re;
	}
	/**
	 * 清空蓝牙状态
	 * @param imei
	 * @return
	 */
	@RequestMapping(value="shutdown")
	@ResponseBody
	public ResultBase shutdown(String imei ){
		ResultBase re = new ResultBase();
		try {
			Channel c =	NettyChannelMap.get(imei);
			c.writeAndFlush("$R20\r\n");
			re.setCode(200);
			re.setMessage("清空蓝牙状态成功！！！");
		} catch (Exception e) {
			re.setCode(350);
			re.setMessage("清空失败！！！");
		}
		return re;
	}
	
}
