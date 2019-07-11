package com.sy.nettyulit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sy.controller.TcpController;
import com.sy.utils.MD5Util;
import com.sy.utils.StringUtil;

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
		String msg16 =StringUtil.Bytes2HexString(msg.getBytes());
		logger.info("打印全部信息msg16>>>>>>>>>>>>>"+msg16);
		if( msg16.substring(msg16.length()-4, msg16.length()).equals("0D0A") || msg.substring(msg.length()-1, msg.length()).equals("n")) {
			if( msg.substring(msg.length()-1, msg.length()).equals("n")  ){
				msg =msg.replaceAll("n", "");
			}
			sb =Mapsbtcp.getBs(MD5Util.MD5(channelHandlerContext.toString()));
			if(sb!=null){
				sb.append(msg);
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
			sb.append(msg);
		}
	}

	
	/**
	 * 接入连接
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
		System.out.println("CLIENT" + ctx.channel().remoteAddress().toString() + " 接入连接");
	}
	/**
	 * 异常信息
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		System.out.println("异常信息：\r\n" + cause.getMessage());
	}
}
