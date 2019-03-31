package com.sy.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class Flowtcp {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws IOException {
	//	folwoperating("01", "FF");
//		System.out.println("iccid======" + folwoiccid("01"));
//		System.out.println("imsi======" + folwoIMSI("01"));
		//System.out.println("位置======" + folwoposition("01"));
	//	String stx = Flowtcp.folwoperating(null, "FF");
		//String S =stx.substring(10, stx.length())+"Z";
	//	System.out.println("响应硬件端数据====" + S);
//		bytesToHexString(String.valueOf(1).getBytes());
//		System.out.println("响应硬件端数据===="+	decimalToHex(19));
		String stx1 = Flowtcp.folwoperating(null, "FF");
		System.out.println("启动====" + stx1);
		String stx = Flowtcp.folwoperating(null, "FF1011FE");
		System.out.println("响应硬件端数据====" + stx);
	}

	public static byte[] decodeHex(String nm) {
		int len = nm.length();
		byte[] result = new byte[len / 2];
		for (int i = 0; i < len; i++) {
			char c = nm.charAt(i);
			byte b = Byte.decode("0x" + c);
			c = nm.charAt(++i);
			result[i / 2] = (byte) (b << 4 | Byte.decode("0x" + c));
		}
		return result;
	}

	/* *
	 * Convert byte[] to hex
	 * string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
	 * 
	 * @param src byte[] data
	 * 
	 * @return hex string
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
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
		}
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param numbering   
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */

	public static String folwoperating(String number, String data) throws UnknownHostException, IOException {
		           number="01";
		        	int   d =data.length(); 
		            int  num =	d/2;
		            String n  = decimalToHex(num);
		            if(Integer.parseInt(n)<10){
		            n=	"AA66"+number+"0"+decimalToHex(num)+data+"";
		            }else {
		            	 n=	"AA66"+number+decimalToHex(num)+data+"";
					}
		System.out.println("tcp----------请求数据===="+n);
		byte[] asd = decodeHex(n);
		Socket s = new Socket("192.168.1.199", 6666);
		OutputStream out = s.getOutputStream();
		out.write(asd);
		boolean status = true;
		String txt = null;
		while (status) {
			try {

				// 创建一个流套接字并将其连接到指定主机上的指定端口号
				// 读取服务器端数据
				DataInputStream input = new DataInputStream(s.getInputStream());
				byte[] buffer;
				buffer = new byte[input.available()];
				if (buffer.length != 0) {
					// 读取缓冲区
					input.read(buffer);
					// 转换字符串
					txt = bytesToHexString(buffer);
					System.out.println("tcp-------------向应数据===="+txt);
					status = false;
					s.close();
				}
			} catch (Exception e) {
				System.out.println("客户端异常:" + e.getMessage());
			}
		}
		return txt;
	}

	/**
	 * @param response
	 * @param responseObject
	 */
	public static void responseOutWithJson(HttpServletResponse response,
			Object responseObject) {
		JSONObject json = new JSONObject();
		json.put("data", responseObject);
		// 灏嗗疄浣撳璞¤浆鎹负JSON Object杞崲
		JSONObject responseJSONObject = JSONObject.fromObject(json);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(responseJSONObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 获取iccid 操作
	 * @param number
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public   static  String  folwoiccid(String number ) throws UnknownHostException, IOException{
		
		ArrayList<String > ls = new ArrayList<String >();
		ls.clear();
		ls.add("00a4080402");
		ls.add("2fe2");
		ls.add("00c0000019");
		ls.add("00b000000a");
		ArrayList<String > rls = new ArrayList<String >();
		rls.clear();
		boolean res =true;
		for(int i =0; i<ls.size(); i++){
			
			if(rls.size() == 2){
				String tst =rls.get(1) ;
				if(tst.indexOf("6a82")!=-1){
					res=false;
				}else {
					String t =tst.substring(tst.length()-2);
					rls.add(folwoperating(number, "00c00000"+t));
				}
				
			}else {
				if(res){
				rls.add(folwoperating(number, ls.get(i)));
				}
			}
		}
		//b0开始，9000结束
		String RETU="";
		if(res){
		String str = rls.get(3) ;
		RETU =str.substring(str.indexOf("b0")+2,str.indexOf("9000"));
		}
		return RETU;
	}
	
	/**获取imsi数据
	 * @param number
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public   static  String  folwoIMSI(String number ) throws UnknownHostException, IOException{
		
		ArrayList<String > ls = new ArrayList<String >();
		ls.clear();
		ls.add("00a4080404");
		ls.add("7F206f07");
		ls.add("00c0000019");
		ls.add("00b0000009");
		boolean res =true;
		ArrayList<String > rls = new ArrayList<String >();
		rls.clear();
		for(int i =0; i<ls.size(); i++){

			if(rls.size() == 2){
				String tst =rls.get(1) ;
				if(tst.indexOf("6a82")  > -1){
					System.out.println("文件为空停止请求");
					res=false;
				}else {
					String t =tst.substring(tst.length()-2);
					rls.add(folwoperating(number, "00c00000"+t));
				}
				
			}else {
				if(res){
				rls.add(folwoperating(number, ls.get(i)));
				}
			}
		}
		//b0开始，9000结束
		String RETU="";
		if(res){
		String str = rls.get(3) ;
		RETU =str.substring(str.indexOf("b0")+2,str.indexOf("9000"));
		}
		return RETU;
	}

	
	/**获取位置数据
	 * 80后面字节    （字节几位就是拿取后面的几位数字）
	 * @param number
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public   static  String  folwoposition(String number ) throws UnknownHostException, IOException{
		
		ArrayList<String > ls = new ArrayList<String >();
		ls.clear();
		ls.add("00a4080404");
		ls.add("7F206f07");
		ls.add("00c0000019");
		ls.add("00b000000");
		ArrayList<String > rls = new ArrayList<String >();
		rls.clear();
		for(int i =0; i<ls.size(); i++){

			if(rls.size() == 2){
				String tst =rls.get(1) ;
				String t =tst.substring(tst.length()-2);
				rls.add(folwoperating(number, "00c00000"+t));
			}else if(rls.size() == 3){
				String tst =rls.get(2) ;
				String t =tst.substring(tst.length()-2);
				rls.add(folwoperating(number, "00b000000"+t));
			}else {
				rls.add(folwoperating(number, ls.get(i)));
			}
		}
		//b0开始，9000结束
		return  rls.get(3) ;
	}
	
	   /**将10进制转换为16进制
	 * @param decimal
	 * @return
	 */
	public static String decimalToHex(int decimal) {
	        String hex = "";
	        while(decimal != 0) {
	            int hexValue = decimal % 16;
	            hex = toHexChar(hexValue) + hex;
	            decimal = decimal / 16;
	        }
	        return  hex;
	    }
	    //将0~15的十进制数转换成0~F的十六进制数
	    public static char toHexChar(int hexValue) {
	        if(hexValue <= 9 && hexValue >= 0)
	            return (char)(hexValue + '0');
	        else
	            return (char)(hexValue - 10 + 'A');
	    }
}
