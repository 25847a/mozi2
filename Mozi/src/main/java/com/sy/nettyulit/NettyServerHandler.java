package com.sy.nettyulit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sy.controller.TcpController;
import com.sy.utils.MD5Util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
	public static StringBuffer sb ;
	
	private static Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		logger.info(ctx.name()+"连接已断开");
		// channel失效，从Map中移除
		NettyChannelMap.remove((SocketChannel) ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
		logger.info("打印全部信息1>>>>>>>>>>>>>"+msg);
		//int length = msg.length();
		String msg16 =Bytes2HexString(msg.getBytes());
		logger.info("打印全部信息msg16>>>>>>>>>>>>>"+msg16);
		//System.out.println(msg+"==="+length+"==="+msg16);
		if( msg16.substring(msg16.length()-4, msg16.length()).equals("0D0A")  || msg.substring(msg.length()-1, msg.length()).equals("n")   ) {
			if( msg.substring(msg.length()-1, msg.length()).equals("n")  ){
				msg =msg.replaceAll("n", "");
			}
			sb =Mapsbtcp.getBs(MD5Util.MD5(channelHandlerContext.toString()));
			if(sb!=null){
				addsb(msg);
				logger.info("打印全部信息2>>>>>>>>>>>>>"+msg);
				TcpController.tcpservie(channelHandlerContext, sb.toString());
				Mapsbtcp.deletesb(MD5Util.MD5(channelHandlerContext.toString()));
			}else {
				logger.info("打印全部信息3>>>>>>>>>>>>>"+msg);
				TcpController.tcpservie(channelHandlerContext, msg);
			}
			
		}else {
			sb =Mapsbtcp.getBs(MD5Util.MD5(channelHandlerContext.toString()));
			if(sb !=null){
				sb=Mapsbtcp.getBs(MD5Util.MD5(channelHandlerContext.toString()));
			}else {
				sb = new StringBuffer();
				Mapsbtcp.addBs(MD5Util.MD5(channelHandlerContext.toString()), sb);
			}
			
			addsb(msg);
		}
		
		if (msg.equals("0")) {
			//System.out.println("心跳======" + channelHandlerContext.toString());
			// 回复心跳
			channelHandlerContext.writeAndFlush("1");
			//
		} else {
			
		}

	}
	
	/**
	 * 添加
	 * 
	 * @param st
	 */
	public static void addsb(String st) {
		sb.append(st);
	}

	

	/**
	 *    *    * @param b byte[]    * @return String   
	 */
	public static String Bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		System.out.println("CLIENT" + getRemoteAddress(ctx) + " 接入连接");
	}

	public static String getRemoteAddress(ChannelHandlerContext ctx) {
		String socketString = "";
		socketString = ctx.channel().remoteAddress().toString();
		return socketString;
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
			stringBuilder.append(' ');
		}
		return stringBuilder.toString();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		System.out.println("异常信息：\r\n" + cause.getMessage());
	}
	
	public static void main(String[] args) {
		String msg ="lM�Ŕ��Q�z3IR hϏ�����Sk��D�LT����";
		bytesToHexString(msg.getBytes());
		System.out.println("成功换行========" + msg);
	}

}
