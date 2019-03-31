package com.sy.nettyulit;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Mapsb {
   
	public static  Map<String,StringBuffer>   mapsb =new ConcurrentHashMap<String, StringBuffer>();
	public static void addBs(String text,StringBuffer sb){
		mapsb.put(text,sb);
	}
	public static StringBuffer getBs(String text){
		
		return mapsb.get(text);
	}
	   public static void BSS(){
	        for (Map.Entry entry:mapsb.entrySet()){
	            System.out.println("value======="+entry.getValue()+" key  =====  "+entry.getKey());
	        }
	    }
}
