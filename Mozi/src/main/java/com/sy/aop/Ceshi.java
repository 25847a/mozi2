package com.sy.aop;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sy.utils.DataRow;

public class Ceshi {
	static Connection con = null;
	public static void main(String[] args) {
		Connection MZ = null;
		try{
			// 加载墨子数据库;
			MZ = Mozi();
			
		//	List<DataRow> list =Query(MZ);
			//更新数据
		//	update(MZ, list);
			//插入数据
			insert(MZ);//惊凡数据表
		//	insetequipmentRecord(MZ);
	//		insetequipmentdata(MZ);
	//	  insetequipmentdata(MZ);//步数、卡里路
		//	insertMember(list,MZ);
		//	updateInsertUserEq(list,MZ);
			System.out.println("加载成功");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				MZ.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	/**
	 * 出t02健康数据
	 * @throws SQLException 
	*/
	public static void 	insetequipmentdata(Connection ZX){
		try {
		boolean fag = true;
		String date = "2019-06-28 00:00:00";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sf.parse(date));
			Calendar cd = Calendar.getInstance();   
			 cd.setTime(sf.parse(date));
			 while(fag) {
				 cd.add(Calendar.MINUTE, 5);
			  String sql = "insert into equipment_data ( user_id, step_when,carrieroad,createtime)values(?,?,?,?)";  
			PreparedStatement ten = ZX.prepareStatement(sql);
			ten.setInt(1,28920);
			ten.setInt(2,(int) (0+Math.random()*(140-0+1)));
			ten.setInt(3,(int) (0+Math.random()*(140-0+1)));
			String time = sf.format(cd.getTime());
			ten.setString(4, time);
			ten.executeUpdate();
			 if(time.equals("2018-08-30 23:55:00")) {
				 fag=false;
				 System.out.println("OK");
			 }
			 }
		} catch (Exception e) {
			System.out.println("插入失败"+e);
			
		}
		
	} 
	/**
	 * 出湿度,健康数据
	 * @throws SQLException 
	*/
	public static void 	insetequipmentRecord(Connection ZX){
		try {
		boolean fag = true;
		String date = "2019-07-05 00:00:00";
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sf.parse(date));
			Calendar cd = Calendar.getInstance();   
			 cd.setTime(sf.parse(date));
			 while(fag) {
				 cd.add(Calendar.MINUTE, 5);
			  String sql = "insert into equipment_record ( userId, humidity,temperature,createtime)values(?,?,?,?)";  
			PreparedStatement ten = ZX.prepareStatement(sql);
			ten.setInt(1,28704);
			ten.setInt(2,(int) (50+Math.random()*(100-50+1)));
			ten.setFloat(3,(float) (0+Math.random()*(140-0+1)));
			String time = sf.format(cd.getTime());
			ten.setString(4, time);
			ten.executeUpdate();
			 if(time.equals("2019-08-30 23:55:00")) {
				 fag=false;
				 System.out.println("OK");
			 }
			 }
		} catch (Exception e) {
			System.out.println("插入失败"+e);
			
		}
		
	} 
	/** 
	 * 获取数据
	 * @param ZX
	 * @return
	 * @throws SQLException
	*/
		public static List<DataRow> Query(Connection ZX){
			List<DataRow> list = new ArrayList<DataRow>();
			
			String sql = "SELECT id FROM user WHERE role='管理者' OR role='管理员'";
			Statement con;
			try {
				con = ZX.createStatement();
				ResultSet res = con.executeQuery(sql);
			
				while(res.next()){
					DataRow map = new DataRow();
					map.put("id", res.getInt("id"));
					list.add(map);
			}
			} catch (SQLException e) {
				System.out.println("获取失败"+e);
			}
			return list;
			
		} 
		
	public static void updateInsertUserEq(List<DataRow> list,Connection ZX) throws SQLException{
		String querySql = "SELECT * from user_eq WHERE user_id=?";
		//String updateSql = "update user_eq set follow=0 where user_id=?";
		PreparedStatement ten = ZX.prepareStatement(querySql);
		DataRow map = new DataRow();
		for(int i =0;i<list.size();i++){
			ten.setInt(1, list.get(i).getInt("id"));
			ResultSet res =ten.executeQuery();
			while(res.next()){
				
				map.put("id", res.getInt("id"));
		}
		}
		System.out.println(map);
	}
	/**
	 * 更新数据
	 * @throws SQLException 
	*/
	public static void 	update(Connection ZX,List<Map<String,String>> list){
		Map<String,String> map = new HashMap<String, String>();
		try {
			for(int i=0;i<list.size();i++){
				map=list.get(i);
			  String sql = "UPDATE jfhealth SET HRV=?, sbp_ave=?, dbp_ave=?, Heartrate=?, Bloodoxygen=?, microcirculation=?,respirationrate=? WHERE id=?";       
			PreparedStatement ten = ZX.prepareStatement(sql);
			ten.setString(1,String.valueOf((int)(100+Math.random()*(140-100+1))));
			ten.setString(2,String.valueOf((int)(100+Math.random()*(180-100+1))));
			ten.setString(3,String.valueOf((int)(50+Math.random()*(80-50+1))));
			ten.setString(4,String.valueOf((int)(125+Math.random()*(18-125+1))));
			ten.setString(5,String.valueOf((int)(80+Math.random()*(100-80+1))));
			ten.setString(6,String.valueOf((int)(50+Math.random()*(150-50+1))));
			ten.setString(7,String.valueOf((int)(70+Math.random()*(85-70+1))));
			ten.setString(8,map.get("id"));
			ten.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("插入失败"+e);
			
		}
		
	} 
		/**
		 * 插入数据
		 * @throws SQLException 
		*/
		public static void 	insert(Connection ZX){
			boolean fag = true;
			String date = "2019-06-01 00:00:00";
			try {
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(sf.parse(date));
				Calendar cd = Calendar.getInstance();   
				 cd.setTime(sf.parse(date));
				 while(fag) {
					 cd.add(Calendar.MINUTE, 5);
				String sql = "INSERT INTO jfhealth(HRV,sbp_ave,dbp_ave,Heartrate,Bloodoxygen,"
						+ "microcirculation,Amedicalreport,respirationrate,phone,imei,createtime)"
						+ "VALUES(?,?,?,?,?,?,?,?,?,862237045519076,?)";
				PreparedStatement ten = ZX.prepareStatement(sql);
				ten.setString(1,String.valueOf((int)(100+Math.random()*(140-100+1))));
				ten.setString(2,String.valueOf((int)(100+Math.random()*(180-100+1))));
				ten.setString(3,String.valueOf((int)(50+Math.random()*(80-50+1))));
				ten.setString(4,String.valueOf((int)(125+Math.random()*(18-125+1))));
				ten.setString(5,String.valueOf((int)(80+Math.random()*(100-80+1))));
				ten.setString(6,String.valueOf((int)(50+Math.random()*(150-50+1))));
				ten.setString(7,"HRV:57, 呼吸频率:13。\r\n" + 
						"1、适当增加食盐的摄入，同时多饮水；\r\n" + 
						"2、饮食荤素合理搭配，增加总热量；\r\n" + 
						"3、加强体育锻炼，增强体质。体育锻炼对预防高血压及低血压均有益处。\r\n" + 
						"4、低血压人群应当每天适当的补充完全蛋白质、维生素B族、抗压素。适当的补充维生素E可以帮助低血压患者消除疲劳。\r\n" + 
						"5、如有病理性的低血压，请到正规医院就诊。");
				ten.setString(8,String.valueOf((int)(70+Math.random()*(85-70+1))));
				String time = sf.format(cd.getTime());
				ten.setString(9,"mozistar28979");
				ten.setString(10,time);
				ten.executeUpdate();
				System.out.println("插入成功");
				if(time.equals("2019-07-23 15:00:00")) {
					 fag=false;
					 System.out.println("OK");
				 }
				 }
			} catch (Exception e) {
				System.out.println("插入失败"+e);
				
			}
			
		} 
		
	/**
	 * 插入数据会员制度
	* @throws SQLException 
	*/	
	public static void insertMember(List<DataRow> list,Connection ZX){
		try {
		String sql = "INSERT INTO member(userId,charges,integral,member,endTime)"
				+ "VALUES(?,?,?,?,?)";
		PreparedStatement ten = ZX.prepareStatement(sql);
		for(int i=0;i<list.size();i++){
			
			ten.setInt(1,list.get(i).getInt("id"));
		ten.setBigDecimal(2, BigDecimal.valueOf(20.0));
		
		ten.setInt(3,0);
		ten.setInt(4,0);
		String a = "2019-07-09 10:02:46";
		ten.setString(5,a);
		ten.executeUpdate();
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 插入数据
	 * @throws SQLException 
	*/
	public static void 	insertEQ(Connection ZX){
		boolean fag = true;
		String date = "2018-07-17 00:00:00";
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sf.parse(date));
			Calendar cd = Calendar.getInstance();   
			 cd.setTime(sf.parse(date));
			 while(fag) {
				 cd.add(Calendar.MINUTE, 5);
			String sql = "INSERT INTO equipment_data(user_id,sleeping,step_when,mocrocirculation,createtime,heartrate,highpressure,bottompressure,"
					+ "bloodpressure,breathe,carrieroad,sedentary,bodytemp,humidity,crash,qxygen,step_time,hrv,step_each,mood,"
					+ "movementstate,sleeping_s,run_s)"
					+ "VALUES(286,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ten = ZX.prepareStatement(sql);
			ten.setString(1,String.valueOf((int)(48+Math.random()*(96-48+1))));
			ten.setString(2,String.valueOf((int)(1+Math.random()*(10-1+1))));
		
			ten.setString(3,String.valueOf((int)(0+Math.random()*(2-0+1))));
			String moth = String.valueOf((int)(1+Math.random()*(12-1+1)));
			String day = String.valueOf((int)(1+Math.random()*(7-1+1)));
			String hour = String.valueOf((int)(1+Math.random()*(23-1+1)));
			String minute = String.valueOf((int)(1+Math.random()*(59-1+1)));
			String second = String.valueOf((int)(1+Math.random()*(59-1+1)));
			
		/*	String Hmoth = (moth.length()==1?"0"+moth:moth);
			String Hday = (day.length()==1?"0"+day:day);
			String Hhour = (hour.length()==1?"0"+hour:hour);
			String Hminute = (minute.length()==1?"0"+minute:minute);
			String Hsecond =(second.length()==1?"0"+second:second);
			String time = "2018-"+"05"+"-"+"14"+" "+Hhour+":"+Hminute+":"+Hsecond;*/
			String time = sf.format(cd.getTime());
			ten.setString(4,time);
			ten.setString(5,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(6,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(7,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(8,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(9,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(10,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(11,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(12,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(13,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(14,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(15,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(16,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(17,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(18,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(19,String.valueOf((int)(48+Math.random()*(180-48+1))));
			ten.setString(20,String.valueOf((int)(0+Math.random()*(2-0+1))));
			ten.setString(21,String.valueOf((int)(0+Math.random()*(4-0+1))));
			ten.setString(22,String.valueOf((int)(0+Math.random()*(2-0+1))));
			ten.executeUpdate();
			System.out.println("插入成功");
			if(time.equals("2018-07-19 23:55:00")) {
				 fag=false;
				 System.out.println("OK");
		 }
			
			 }
		} catch (Exception e) {
			System.out.println("插入失败"+e);
			
		}
		
	} 
	/**
	 * 连接墨子数据库
	 * 
	 * @return
	 */
	public static Connection Mozi() {
		String Driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://120.76.201.150:3306/mozi?useUnicode=true&characterEncoding=utf8";
		String UserName = "root";
		String Password = "root";
		try {
			Class.forName(Driver);
			con = DriverManager.getConnection(url, UserName, Password);
			// con.close();
		} catch (Exception e) {
			System.out.println("加载征询库失败" + e);
		}
		return con;
	}
}
