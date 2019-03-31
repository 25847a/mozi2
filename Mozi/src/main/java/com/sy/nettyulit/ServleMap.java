package com.sy.nettyulit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

/**
 * 储存服务的tomcat服务端接收硬件数据
 * @author Administrator
 *
 */
public class ServleMap {
	
		    public static Map<String,SocketChannel> map=new ConcurrentHashMap<String, SocketChannel>();
		    public static void add(String clientId,SocketChannel socketChannel){
		        map.put(clientId,socketChannel);
		    }
		    public static Channel get(String clientId){
		       return map.get(clientId);
		    }
		    public static void remove(SocketChannel socketChannel){
		        for (Map.Entry entry:map.entrySet()){
		            if (entry.getValue()==socketChannel){
		                map.remove(entry.getKey());
		            }
		        }
		    }
		    
		    public static void Traverse(String txet){
		    	SocketChannel channel;
		        for (Map.Entry entry:map.entrySet()){
		            System.out.println("value======="+entry.getValue()+" key  =====  "+entry.getKey());
		                channel=(SocketChannel) entry.getValue();
		               try {
		            	   channel.writeAndFlush(txet);
		            	   break;
				    	} catch (Exception e) {
				    		map.remove(entry.getKey());
						 System.out.println("服务端通讯不可用。。。。。。");
					   }
		             
		        }
		    }
	

}
