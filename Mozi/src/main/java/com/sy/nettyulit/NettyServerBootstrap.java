package com.sy.nettyulit;

//import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServerBootstrap {
	    private int port;
	 //   private SocketChannel socketChannel;
	    public NettyServerBootstrap(int port) throws InterruptedException {
	        this.port = port;
	        bind();
	    }
	 
	    private void bind() throws InterruptedException {
	        EventLoopGroup boss=new NioEventLoopGroup();
	        EventLoopGroup worker=new NioEventLoopGroup();
	        ServerBootstrap bootstrap=new ServerBootstrap();
	        bootstrap.group(boss,worker);
	        bootstrap.channel(NioServerSocketChannel.class);
	        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
	        bootstrap.option(ChannelOption.TCP_NODELAY, true);
	        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
	        bootstrap.childHandler(new NettyServerFilter()); //设置过滤器
	        ChannelFuture f= bootstrap.bind(port).sync();
	        if(f.isSuccess()){
	            System.out.println("server start");
	        }
	    }
	   /* public static void main(String []args) throws InterruptedException {
	    	    NettyServerBootstrap bootstrap=new NettyServerBootstrap(9);
	        while (true){
	            TimeUnit.SECONDS.sleep(5);
	        }
	    }*/
}
