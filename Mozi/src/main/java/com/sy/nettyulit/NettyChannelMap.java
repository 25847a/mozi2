package com.sy.nettyulit;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sy.pojo.Equipment;
import com.sy.pojo.User;
import com.sy.service.EquipmentService;
import com.sy.service.PositionigService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.service.impl.EquipmentServiceImpl;
import com.sy.service.impl.PositionigServiceImpl;
import com.sy.service.impl.UserEqServiceImpl;
import com.sy.service.impl.UserServiceImpl;
import com.sy.utils.Addressuitl;
import com.sy.utils.Tiaoqi;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 储存硬件端的
 * jian:单例模式饿汉式
 * @author Administrator
 *
 */
public class NettyChannelMap {
	public static Map<String, SocketChannel> map = new ConcurrentHashMap<String, SocketChannel>();

	public static void add(String clientId, SocketChannel socketChannel) {
		map.put(clientId, socketChannel);
	}

	public static Channel get(String clientId) {
		return map.get(clientId);
	}

	public static void remove(SocketChannel socketChannel) {
		for (Map.Entry entry : map.entrySet()) {
			if (entry.getValue() == socketChannel) {
				map.remove(entry.getKey());
			}
		}
	}

	public static void Traverse(String text) {
		for (Map.Entry entry : map.entrySet()) {
			System.out.println("value=======" + entry.getValue()
					+ " key  =====  " + entry.getKey());
			try {
				SocketChannel channel = (SocketChannel) entry.getValue();
				channel.writeAndFlush(text);
			} catch (Exception e) {
				map.remove(entry.getKey());
			}

		}
	}

	/**
	 * 发送使用者的信息
	 * 
	 */
	public static void information() {
		UserEqService userequservice = new UserEqServiceImpl();
		UserService userservice = new UserServiceImpl();
		for (Map.Entry entry : map.entrySet()) {
			System.out.println("value=======" + entry.getValue()
					+ " key  =====  " + entry.getKey());
			try {
				Integer userid = userequservice
						.getimei((String) entry.getKey());
				SocketChannel channel = (SocketChannel) entry.getValue();
				User user = userservice.getUser(userid);
				channel.writeAndFlush("$R03|OK|" +getCode( user.getName()) + ","
						+ user.getHeight() + "," + user.getWeight() + ","
						+ "|ERR\r\n");
			} catch (Exception e) {
				map.remove(entry.getKey());
			}

		}
	}

	// 发送天气信息给硬件端
	public static void weather() {

		for (Map.Entry entry : map.entrySet()) {
			PositionigService poservie = new PositionigServiceImpl();
			System.out.println("value=======" + entry.getValue()
					+ " key  =====  " + entry.getKey());
			try {
				String txt = poservie.selectimeiPositionigtcp((String) entry
						.getKey());
				String[] sts = txt.split(":");
				String add = Addressuitl.getAdd(sts[0], sts[1]);
				JSONObject jsonObject = JSONObject.fromObject(add);
				JSONArray jsonArray = JSONArray.fromObject(jsonObject
						.getString("addrList"));
				JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
				String allAdd = j_2.getString("admName");
				String arr[] = allAdd.split(",");
				System.out.println("省：" + arr[0] + "\n市：" + arr[1] + "\n区："
						+ arr[2]);
				String tqxx = Tiaoqi.tiaonqi(arr[2]);
				SocketChannel channel = (SocketChannel) entry.getValue();
				channel.writeAndFlush("$R02|OK|" + tqxx + "|ERR\r\n");
			} catch (Exception e) {
				map.remove(entry.getKey());
			}

		}
	}

	// 发送紧急联系人数据给硬件端
	public static void urgent() {
		for (Map.Entry entry : map.entrySet()) {
			PositionigService poservie = new PositionigServiceImpl();
			System.out.println("value=======" + entry.getValue()
					+ " key  =====  " + entry.getKey());
			try {
				String txt = poservie.selectimeiPositionigtcp((String) entry
						.getKey());
				String[] sts = txt.split(":");
				String add = Addressuitl.getAdd(sts[0], sts[1]);
				JSONObject jsonObject = JSONObject.fromObject(add);
				JSONArray jsonArray = JSONArray.fromObject(jsonObject
						.getString("addrList"));
				JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0));
				String allAdd = j_2.getString("admName");
				String arr[] = allAdd.split(",");
				System.out.println("省：" + arr[0] + "\n市：" + arr[1] + "\n区："
						+ arr[2]);
				String tqxx = Tiaoqi.tiaonqi(arr[2]);
				SocketChannel channel = (SocketChannel) entry.getValue();
				channel.writeAndFlush("$R02|OK|" + tqxx + "|ERR\r\n");
			} catch (Exception e) {
				map.remove(entry.getKey());
			}

		}for (Map.Entry entry : map.entrySet()) {
			System.out.println("value=======" + entry.getValue()
					+ " key  =====  " + entry.getKey());
			try {
			
			    EquipmentService equipmentservice = new EquipmentServiceImpl();
			    Equipment eq = equipmentservice.selectquipmentimei((String) entry.getKey());
				SocketChannel channel = (SocketChannel) entry.getValue();
				channel.writeAndFlush("$R24|OK|" + eq.getPhone1() + "|"+ eq.getPhone2()+"|ERR\r\n");
			} catch (Exception e) {
				map.remove(entry.getKey());
			}

		}
	}
	 public static String getCode(String content) throws UnsupportedEncodingException {
	   	  byte[] bytes = content.getBytes("gb2312");
	   	  StringBuffer sb = new StringBuffer();
	   	  for (int i = 0; i < bytes.length; i++) {
	   	   sb.append(Integer.toHexString(bytes[i] & 0xff).toUpperCase() + "");
	   	  }
	   	   
	   	  return sb.toString();
	   	 }
}
