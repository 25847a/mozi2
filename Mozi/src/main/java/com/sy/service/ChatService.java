package com.sy.service;

import java.util.List;

import com.sy.pojo.Chat;

/**硬件聊天接口
 * @author Groot
 *
 */
public interface ChatService {
	/**添加数据
	 * @param c
	 * @return
	 */
	public boolean addChat(Chat c);
	
	/**获取新消息
	 * @return
	 */
	public List<Chat>selectChat(String imei);
}
