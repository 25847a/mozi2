package com.sy.common;

/**
 * 测试类(cj)
 * @author Administrator
 *
 */
public class sdf {
	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < 1000; i++) {
				JpushClientUtil.sendToAlias("25741", 
						"的心率异常", "当前心率", 
						"已经超过正常范围值",
						"2", "1111");
				Thread.sleep(180000);
			}	
		}
	}
		
