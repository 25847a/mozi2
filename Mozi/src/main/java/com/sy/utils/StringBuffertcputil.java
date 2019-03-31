package com.sy.utils;

import java.util.Arrays;

public class StringBuffertcputil {
	
	public static void main(String[] args) {
		addsb("$425325|T09|10086,1,1,1025253520,1250253251\r\n");
		addsb("$425325|T09|10086,1,1,");
		addsb("1025253520,1250253251\r\n");
		String [] sts =Cutting();
		System.out.println(Arrays.toString(sts));
		System.out.println(sts[0]);
		String S =Atlast();
		System.out.println(sb.toString());
		resb(sts[0]);
		System.out.println(sb.toString());
	}
	public static String Atlast(){
    	return (String) sb.toString().subSequence(sb.toString().length()-1,sb.toString().length());
    }
	public static StringBuffer sb = new StringBuffer();

	public static void addsb(String st) {
		sb.append(st);
	}

	public static void resb(String st) {
		String sts =sb.toString().replace( st + "G\r\n", "");
		sb.delete(0,sb.length());
		sb.append(sts);
	}


	/**切割
	 * @return
	 */
	public static String[] Cutting() {
		String[] data = sb.toString().split("\r\n");
		System.out.println(data.length);
		
		return data;
	}
	
}
