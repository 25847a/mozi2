package com.sy.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class StringUtil {
	/**
	 * @param byte[]数组转String字符串
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
	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 字符串转换成十六进制字符串
	 * 
	 * @param String
	 *            str 待转换的ASCII字符串
	 * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
	 */
	public static String str2HexStr(String str) {

		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;

		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
			// sb.append(' ');
		}
		return sb.toString().trim();
	}

	/**
	 * 十六进制转换字符串
	 * 
	 * @param String
	 *            str Byte字符串(Byte之间无分隔符 如:[616C6B])
	 * @return String 对应的字符串
	 */
	public static String hexStr2Str(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;

		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}

	/**
	 * bytes转换成十六进制字符串
	 * 
	 * @param byte[]
	 *            b byte数组
	 * @return String 每个Byte值之间空格分隔
	 */
	public static String byte2HexStr(byte[] b) {
		String stmp = "";
		StringBuilder sb = new StringBuilder("");
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
			sb.append(" ");
		}
		return sb.toString().toUpperCase().trim();
	}

	/**
	 * bytes字符串转换为Byte值
	 * 
	 * @param String
	 *            src Byte字符串，每个Byte之间没有分隔符
	 * @return byte[]
	 */
	public static byte[] hexStr2Bytes(String src) {
		int m = 0, n = 0;
		int l = src.length() / 2;
		System.out.println(l);
		byte[] ret = new byte[l];
		for (int i = 0; i < l; i++) {
			m = i * 2 + 1;
			n = m + 1;
			ret[i] = Byte.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
		}
		return ret;
	}
	
	/**
     * 两个Double数相减
     * @param v1
     * @param v2
     * @return Double
     */
    public static Double sub(Double v1,Double v2){
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.subtract(b2).doubleValue();
    }
    

	/**
	 * String的字符串转换成unicode的String
	 * 
	 * @param String
	 *            strText 全角字符串
	 * @return String 每个unicode之间无分隔符
	 * @throws Exception
	 */
	public static String strToUnicode(String strText) throws Exception {
		char c;
		StringBuilder str = new StringBuilder();
		int intAsc;
		String strHex;
		for (int i = 0; i < strText.length(); i++) {
			c = strText.charAt(i);
			intAsc = (int) c;
			strHex = Integer.toHexString(intAsc);
			if (intAsc > 128)
				str.append("\\u" + strHex);
			else // 低位在前面补00
				str.append("\\u00" + strHex);
		}
		return str.toString();
	}

	/**
	 * unicode的String转换成String的字符串
	 * 
	 * @param String
	 *            hex 16进制值字符串 （一个unicode为2byte）
	 * @return String 全角字符串
	 */
	public static String unicodeToString(String hex) {
		int t = hex.length() / 6;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hex.substring(i * 6, (i + 1) * 6);
			// 高位需要补上00再转
			String s1 = s.substring(2, 4) + "00";
			// 低位直接转
			String s2 = s.substring(4);
			// 将16进制的string转为int
			int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
			// 将int转换为字符
			char[] chars = Character.toChars(n);
			str.append(new String(chars));
		}
		return str.toString();
	}

	/**
	 * 对用户数据进行序列化可以存储到redis里面
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object obj) {
		ObjectOutputStream obi = null;
		ByteArrayOutputStream bai = null;
		try {
			bai = new ByteArrayOutputStream();
			obi = new ObjectOutputStream(bai);
			obi.writeObject(obj);
			byte[] byt = bai.toByteArray();
			return byt;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对存在redis里面的数据获取出来进行反序列化获取到原有对象 反序列化
	 * 
	 * @param byt
	 * @return
	 */
	public static Object unserizlize(byte[] byt) {
		ObjectInputStream oii = null;
		ByteArrayInputStream bis = null;
		bis = new ByteArrayInputStream(byt);
		try {
			oii = new ObjectInputStream(bis);
			Object obj = oii.readObject();
			return obj;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取令牌
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public static String getToken(String name, String password) {

		return MD5Util.MD5(name + password + new Date().getTime());
	}

	/**获取redis实例化对象
	 * @return
	 */
	public static Jedis getJedis(Integer index) {
		Jedis je =new Jedis("localhost", 6379);
		je.select(index);
		return je ;
	}
	
		/**redsi里面存放List数据
		 * @param tavs
		 * @return
		 */
//		public static byte[] serializels(List<RoleAuthVo> tavs) {
//			if (tavs == null) {
//				throw new NullPointerException("Can't serialize null");
//			}
//			byte[] rv = null;
//			ByteArrayOutputStream bos = null;
//			ObjectOutputStream os = null;
//			try {
//				bos = new ByteArrayOutputStream();
//				os = new ObjectOutputStream(bos);
//				for (RoleAuthVo user : tavs) {
//					os.writeObject(user);
//				}
//				os.writeObject(null);
//				os.close();
//				bos.close();
//				rv = bos.toByteArray();
//			} catch (IOException e) {
//				throw new IllegalArgumentException("Non-serializable object", e);
//			} finally {
//				close(os);
//				close(bos);
//			}
//			return rv;
//		}

		static class ListTranscoder {
			public static byte[] serialize(Object value) {
				if (value == null) {
					throw new NullPointerException("Can't serialize null");
				}
				byte[] rv = null;
				ByteArrayOutputStream bos = null;
				ObjectOutputStream os = null;
				try {
					bos = new ByteArrayOutputStream();
					os = new ObjectOutputStream(bos);
					os.writeObject(value);
					os.close();
					bos.close();
					rv = bos.toByteArray();
				} catch (IOException e) {
					throw new IllegalArgumentException("Non-serializable object", e);
				} finally {
					close(os);
					close(bos);
				}
				return rv;
			}

		}
	
	/**redis里面解析List数据
	 * @param in
	 * @return
	 */
//	public static List<RoleAuthVo> deserialize(byte[] in) {
//		List<RoleAuthVo> list = new ArrayList<RoleAuthVo>();
//		ByteArrayInputStream bis = null;
//		ObjectInputStream is = null;
//		try {
//			if (in != null) {
//				bis = new ByteArrayInputStream(in);
//				is = new ObjectInputStream(bis);
//				while (true) {
//					Object j = is.readObject();
//					if (j == null) {
//						break;
//					} else {
//						list.add((RoleAuthVo) j);
//					}
//				}
//				is.close();
//				bis.close();
//			}
//		} catch (IOException e) {
//		} catch (ClassNotFoundException e) {
//		} finally {
//		}
//		return list;
//	}
	
	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
			}
		}
	}
	/**返回信息
	 * @param response
	 * @param responseObject
	 */
	public static  void responseOutWithJson(HttpServletResponse response,  
	        Object responseObject) {  
		JSONObject json= new JSONObject();
		json.put("data", responseObject);
	    //将实体对象转换为JSON Object转换  
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
	 * 上传多个文件
	 * @param path
	 * @param files
	 */
	public static void arrayUploadFiles(String path,CommonsMultipartFile[]  files){
		 for(int i = 0;i<files.length;i++){  
			 arrayUploadFile(path, files[i]);
	        }  	
	}
	
	
	
	/**
	 * 上传单个文件
	 * @param path
	 * @param file
	 */
	public static void arrayUploadFile(String path ,CommonsMultipartFile file){
		  if(!file.isEmpty()){  
                try {  
                    FileOutputStream os = new FileOutputStream(path +"/"+ file.getOriginalFilename());  
                    //FileInputStream in = (FileInputStream) file.getInputStream();  
                    InputStream in = file.getInputStream();
                    
                    int len= 0;  
                    byte[] buffer = new byte[1024];
                    while ((len = in.read(buffer)) > 0) {
                    	os.write(buffer, 0, len);
					}
                    os.flush();  
                    os.close();  
                    in.close();  
                	
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
        } 
		
	}
	
	public static String  comparedouble(double a, double b){
		    BigDecimal data1 = new BigDecimal(a);  
		    BigDecimal data2 = new BigDecimal(b);
		    return  compare(data1, data2);
	}
	public static String compare(BigDecimal val1, BigDecimal val2) {  
	    String result = "";  
	    if (val1.compareTo(val2) < 0) {  
	        result = "第二位数大！";  
	    }  
	    if (val1.compareTo(val2) == 0) {  
	        result = "两位数一样大！";  
	    }  
	    if (val1.compareTo(val2) > 0) {  
	        result = "第一位数大！";  
	    }  
	    return result;  
	}  
	  private final static String ENCODE = "GBK"; 
		/**
	     * URL 转码
	     *
	     * @return String
	     * @author lifq
	     * @date 2015-3-17 下午04:10:28
	     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**  
     * 将分为单位的转换为元 （除100）  
     *   
     * @param amount  
     * @return  
     * @throws Exception   
     */ 
    /**金额为分的格式 */    
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";    
        
    public static String changeF2Y(String amount) throws Exception{    
        if(!amount.matches(CURRENCY_FEN_REGEX)) {    
            throw new Exception("金额格式有误");    
        }    
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();    
    }    
    
    public static Date Convert(Integer num ) throws ParseException{
    	SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Long time=new Long(num);  
        String d = format.format(time);  
        Date date=format.parse(d);
		return date;  
    }
    
    /* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(Date date) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
