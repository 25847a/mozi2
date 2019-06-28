package com.sy.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.SensorstatusMapper;
import com.sy.mapper.WaveformMapper;
import com.sy.nettyulit.BluetoothMap;
import com.sy.nettyulit.NettyChannelMap;
import com.sy.pojo.Bluetooth;
import com.sy.pojo.Equipment;
import com.sy.pojo.EquipmentData;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Positionig;
import com.sy.pojo.Sensorstatus;
import com.sy.pojo.Uploaddownload;
import com.sy.pojo.User;
import com.sy.pojo.Waveform;
import com.sy.service.BluetoothService;
import com.sy.service.EquipmentDataService;
import com.sy.service.EquipmentService;
import com.sy.service.PositionigService;
import com.sy.service.UploaddownloadService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.service.impl.BluetoothServiceImpl;
import com.sy.service.impl.EquipmentDataServiceImpl;
import com.sy.service.impl.EquipmentServiceImpl;
import com.sy.service.impl.HealthtoolServiceImpl;
import com.sy.service.impl.PositionigServiceImpl;
import com.sy.service.impl.UploaddownloadServiceImpl;
import com.sy.service.impl.UserEqServiceImpl;
import com.sy.service.impl.UserServiceImpl;
import com.sy.utils.GB2312Utils;
import com.sy.utils.StringUtil;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;

@Controller
public class TcpController {
	public static StringBuffer sb;
	private final static Logger logger = LoggerFactory.getLogger(TcpController.class);
	private static EquipmentService eservice = new EquipmentServiceImpl();
	private static EquipmentDataService eqdataserive = new EquipmentDataServiceImpl();
	private static UserEqService usereqservice = new UserEqServiceImpl();
	private static UserService userservice = new UserServiceImpl();
	private static UploaddownloadService uploadDownladservie = new UploaddownloadServiceImpl();
	private static PositionigService positionigservice = new PositionigServiceImpl();
	@Autowired
	private static SensorstatusMapper sensorstatusmapper;
	@Autowired
	private static EquipmentMapper equipmentMapper;

	//@Autowired
	//private static JfhealthMapper jfhealthMapper;
	@Autowired
	private static JfhealthNewMapper jfhealthNewMapper;
	@Autowired
	private static WaveformMapper waveformMapper;

	private static void init() {

		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

		if (sensorstatusmapper == null) {
			sensorstatusmapper = (SensorstatusMapper) webApplicationContext.getBean("sensorstatusMapper");
		}

		if (equipmentMapper == null) {
			equipmentMapper = (EquipmentMapper) webApplicationContext.getBean("equipmentMapper");
		}

		if (jfhealthNewMapper == null) {
			jfhealthNewMapper = (JfhealthNewMapper) webApplicationContext.getBean("jfhealthNewMapper");
		}
		//if (jfhealthMapper == null) {
		//	jfhealthMapper = (JfhealthMapper) webApplicationContext.getBean("jfhealthMapper");
		//}
		if (waveformMapper == null) {
			waveformMapper = (WaveformMapper) webApplicationContext.getBean("waveformMapper");
		}
	}

	public static void tcpservie(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
		String[] texts = msg.split("\\|");
		String imei = texts[0].substring(1, texts[0].length());
		if (msg.contains("T08") || msg.contains("R18")) {
			// System.out.println(imei+"存储蓝牙信息>>>>>>>>>>"+msg);
			BluetoothMap.addBs(imei, msg);
		}
		//上传数据间隔的R27
		if(msg.contains("R27")){
			BluetoothMap.addBs(imei, msg);
		}
		// System.out.println("硬件请求数据>>>>>>>>>>"+msg);
		// 正常访问
		logger.info(">>>>>>>>>>>>>>硬件数据" + msg);
		operating(channelHandlerContext, msg, imei);
	}

	/**
	 * 硬件数据操作
	 * 
	 * @param channelHandlerContext
	 * @param msg
	 * @throws Exception
	 */
	public static void operating(ChannelHandlerContext channelHandlerContext, String msg, String imei)
			throws Exception {
		try {
			init();
			Equipment equipment = eservice.selectquipmentimei(imei);
			if (equipment != null) {
				if (msg.contains("HEARD")) {
					// 将imei通讯通道存储到map里面进行聊天通讯
					NettyChannelMap.add(imei, (SocketChannel) channelHandlerContext.channel());
					eservice.updateEqStatus("H:1", "1", imei, null, null, null,equipment);
						
				} else {

					String[] texts = msg.split("\\|");
					if (texts != null && texts.length != 0) {
						
						eservice.updateEqStatus("H:1", "1", imei, null, null, null,equipment);
						// 将imei通讯通道存储到map里面进行聊天通讯
						NettyChannelMap.add(imei, (SocketChannel) channelHandlerContext.channel());
						//T01
						String instruction = texts[1];
						User user = userservice.getUser(imei);
						
						if (instruction.equals("T01")) {
							T01(user, texts, imei, equipment, channelHandlerContext);						
						} else if (instruction.equals("T02")) {
							T02(texts, imei, equipment);
						} else if (instruction.equals("T03")) {
							channelHandlerContext.writeAndFlush("T03|OK\r\n");
						} else if (instruction.equals("T04")) {

							Equipment e = equipmentMapper.getequipment(imei);
							String h = texts[2].substring(0, 1);
							if (h.equals("H")) {
								String text = texts[2].substring(2, texts[2].length());
								e.setBluetoothStatus(text);
								e.setEqStatus(text);
								e.setUpdatetime(new Date());
								boolean est = eservice.updateEquipment(e);
								if (est) {
									channelHandlerContext.writeAndFlush("T04|OK\r\n");
								} else {
									channelHandlerContext.writeAndFlush("T04|ERR\r\n");
								}
							}
						} else if (instruction.equals("T05")) {
							
							String[] hs = texts[2].split(",");
							Positionig p = new Positionig();

							p.setCratetime(new Date());
							p.setImei(imei);
							
							p.setPositioningS(hs[0].split(":")[1]);
							String[] split = hs[1].split(":");
							
							p.setPositioningData(split[1] + ":" + split[2]);
							
							positionigservice.addPosition(p);
							
							
						} else if (instruction.equals("T06")) {
						} else if (instruction.equals("T07")) {
							BluetoothService bluetoothservice = new BluetoothServiceImpl();
							Bluetooth b = bluetoothservice.getBluetoothid(imei);
							if (b != null) {
								b.setBluetoothnd(texts[2]);
								b.setRecentime(new Date());
								b.setImei(imei);
								if (bluetoothservice.updateBluetooth(b)) {

									channelHandlerContext.writeAndFlush("T07|OK\r\n");
								} else {
									channelHandlerContext.writeAndFlush("T07|ERR\r\n");
								}
							} else {
								b = new Bluetooth();
								b.setBluetoothnd(texts[2]);
								b.setRecentime(new Date());
								b.setCratetime(new Date());
								b.setImei(imei);
								bluetoothservice.addBluetooth(b);
							}
						} else if (instruction.equals("T08")) {
							Equipment e = equipmentMapper.getequipment(imei);
							try {
								String[] bs = texts[2].split(":");
								//有没有连接
								e.setBluetoothStatus(bs[0]);
								//有没有衣服
								e.setBluetoothType(bs[1]);
								e.setBluetoothName(bs[2]);
								e.setBluetoothmac(bs[3]);
								equipmentMapper.updateByPrimaryKeySelective(e);
							} catch (Exception e2) {
								e2.printStackTrace();
							}

							eservice.updateEquipment(e);

						} else if (instruction.equals("T09")) {

						}else if(instruction.equals("T10")){
							
							Map<String,Object> map = new HashMap<>();
							map.put("imei", imei);
							Waveform data = waveformMapper.getWaveform(imei);
							
							StringBuilder sb = new StringBuilder();
							byte[] fromBASE64 = Base64Utils.decodeFromString(texts[2].trim());
							for (byte b : fromBASE64) {
								sb.append(b+128+",");
							}
							
							if(data==null){
								Waveform waveform = new Waveform();
								waveform.setData(sb.toString());
								waveform.setImei(imei);
								if(user!=null){
									waveform.setUserId(user.getId());
									JfhealthNew jfBean = jfhealthNewMapper.selectJfhealthNew("mozistar"+user.getId());
									waveform.setInstructions(jfBean.getAmedicalreport());
								}
								waveformMapper.insertSelective(waveform);
							}else{
								if(user!=null){
									data.setUserId(user.getId());
									JfhealthNew jfBean = jfhealthNewMapper.selectJfhealthNew("mozistar"+user.getId());
									data.setInstructions(jfBean.getAmedicalreport());
								}
								data.setData(sb.toString());
								waveformMapper.update(data);
							}
							
							
						}else if (instruction.equals("T11")) {

							Integer userid = usereqservice.getimei(imei);
							User u = userservice.getUser(userid);
							channelHandlerContext.writeAndFlush("$R03|OK|" + getCode(u.getName()) + "|ERR\r\n");
						} else if (instruction.equals("T12")) {
							channelHandlerContext
									.writeAndFlush("$R01|OK|" + StringUtil.dateToStamp(new Date()) + "|ERR\r\n");
						} else if (instruction.equals("T13")) {
						} else if (instruction.equals("T14")) {
							// 惊凡上传数据
							String jfshuju = HealthtoolServiceImpl.addjfthealth(imei, texts[3].trim(), texts[2], "T14");
							if("nouser".equals(jfshuju)){
								logger.info(imei+"=============没有绑定不需要上传数据");
							}else if("nocalibration".equals(jfshuju)){
								logger.info(imei+"=============没有学习不允许上传数据");
							}else if (jfshuju==null){
								logger.info(imei+"=============数据解析出错");
							}else{
								channelHandlerContext.writeAndFlush(jfshuju);
							}
						} else if (instruction.equals("T15")) {
							// 惊凡上传数据
							String jfshuju = HealthtoolServiceImpl.addjfthealth(imei, texts[3].trim(), texts[2], "T15");
							
							if (jfshuju!=null) {
								channelHandlerContext.writeAndFlush(jfshuju);
								BluetoothMap.addhealthmap(imei, "A");
							} else {
								logger.info(imei+"=============学习失败,数据解析出错");
								BluetoothMap.addhealthmap(imei, "B");
							}
							
						} else if (instruction.equals("T21")) {
						} else if (instruction.equals("T22")) {
						} else if (instruction.equals("T23")) {
						}
					}
				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);

			stringBuilder.append(i + ":");

			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv + ";");
		}
		return stringBuilder.toString();
	}

	/**
	 *    *    * @param b byte[]    * @return String   
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

	public static String getCode(String content) throws Exception {

		return content;
	}

	//
	// public static void main(String[] args) throws
	// UnsupportedEncodingException {
	// String txt = "我";
	// byte[] sour = txt.getBytes("utf-8");
	// String dest = new String(sour, "gb2312");
	// System.out.println(dest);
	// }
	//

	public static void socketurl(String txt) throws IOException {
		Socket s = new Socket("lanotec.iask.in", 38679);
		OutputStream out = s.getOutputStream();
		out.write(txt.getBytes());
		InputStream inputStream = s.getInputStream();
		byte[] bytes = new byte[1024];
		int len;
		StringBuilder sb = new StringBuilder();
		while ((len = inputStream.read(bytes)) != -1) {
			// 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
			sb.append(new String(bytes, 0, len, "UTF-8"));
		}
		s.close();
	}

	public static Integer randomvalue(int value) {
		int n = (int) (value * 0.7);
		// value = (int) (value + n * Math.random());
		// return value;
		return n;
		// value = value+(int)((value * 5) / 100);
		// return value;
	}

	public static Integer randomvaluexy(int value) {
		int n = (int) (value * 0.3);
		// value = (int) (value + n * Math.random());
		// return value;
		return n;
		// value = value+(int)((value * 5) / 100);
		// return value;
	}

	public static Integer randomvaluehq(int value) {
		int n = (int) (value * 0.15);
		value = (int) (value + n * Math.random());
		return value;
		// value = value+(int)((value * 5) / 100);
		// return value;
	}

	private static String getR06(User user) throws Exception {
		String R06 = "$R06|";
		try {
			String name = user.getName();
			String address = user.getAddress();
			if (!StringUtils.isNotBlank(name)) {
				R06 += ":";
			} else {
				R06 += GB2312Utils.gb2312eecode(user.getName()) + ":";
			}
			if (!StringUtils.isNotBlank(address)) {
				R06 += "\r\n";
			} else {
				R06 += GB2312Utils.gb2312eecode(user.getAddress()) + "\r\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			R06 = "$R06|ERR\r\n";
		}
		return R06;
	}
	private static void T01(User user,String[] texts,String imei,Equipment equipment,ChannelHandlerContext channelHandlerContext)
	throws Exception{
		if (user != null) {
			Sensorstatus record = sensorstatusmapper.selecttimesensorstatus(imei);
			
			if (record == null) {
				record = new Sensorstatus();
				record.setG(texts[5]);
				record.setH(texts[3]);
				record.setImei(imei);
				record.setAdddate(new Date());
				sensorstatusmapper.insert(record);
			} else {
				
				record.setG(texts[5]);
				record.setH(texts[3]);
				record.setImei(imei);
				record.setAdddate(new Date());
				sensorstatusmapper.updateByPrimaryKey(record);
			}

			String lordpower = texts[6].split(":")[1];
			String signalxhao = texts[7].split(":")[1];
			
			//更新设备数据
			//主控上固定值 texts[3]H:1只要T01已发送表示设备在线
			Equipment e = eservice.updateEqStatus("H:1", texts[2], imei,
					Integer.parseInt(lordpower), signalxhao, texts[8], equipment);

			String model = e.getModel();

			Uploaddownload uploadDownload = uploadDownladservie.selectModelAndImeiVo(imei, model);
			if (uploadDownload != null) {
					e.setBluetoothmac("000000000000");
					e.setBluetoothName("000000000000");
					e.setBluetoothStatus("0");
					e.setBluetoothType("0");
					eservice.updateEquipment(e);
					logger.info("返回给 "+imei+" 主控的版本>>>>>>>>>>>>>>$R01|" + StringUtil.dateToStamp(new Date())
					+ "|" + uploadDownload.getZhuversion() + "_"
					+ uploadDownload.getZiversion() + "_" + uploadDownload.getCompilation()
					+ "_" + uploadDownload.getVersiontype() + "\r\n");
				channelHandlerContext.writeAndFlush("$R01|" + StringUtil.dateToStamp(new Date())
						+ "|" + uploadDownload.getZhuversion() + "_" + uploadDownload.getZiversion()
						+ "_" + uploadDownload.getCompilation() + "_"
						+ uploadDownload.getVersiontype() + "\r\n");
				eservice.equipmentstatus("H:1", "1", imei, null, null,
						uploadDownload.getZhuversion() + "_" + uploadDownload.getZiversion() + "_"
								+ uploadDownload.getCompilation() + "_"
								+ uploadDownload.getVersiontype());
			} else {
				uploadDownload = uploadDownladservie.selectModelAndImeiVo("batch", model);
				// uploadDownload =
				// uploadDownladservie.selectUploaddownload("batch");
				if (uploadDownload != null) {
					e.setBluetoothmac("000000000000");
					e.setBluetoothName("000000000000");
					e.setBluetoothStatus("0");
					e.setBluetoothType("0");
					eservice.updateEquipment(e);
					
					logger.info("返回给 "+imei+" 主控的版本>>>>>>>>>>>>>>$R01|" + StringUtil.dateToStamp(new Date())
							+ "|" + uploadDownload.getZhuversion() + "_"
							+ uploadDownload.getZiversion() + "_" + uploadDownload.getCompilation()
							+ "_" + uploadDownload.getVersiontype() + "\r\n");
					
					channelHandlerContext.writeAndFlush("$R01|" + StringUtil.dateToStamp(new Date())
							+ "|" + uploadDownload.getZhuversion() + "_"
							+ uploadDownload.getZiversion() + "_" + uploadDownload.getCompilation()
							+ "_" + uploadDownload.getVersiontype() + "\r\n");
					eservice.equipmentstatus("H:1", "1", imei, null, null,
							uploadDownload.getZhuversion() + "_" + uploadDownload.getZiversion()
									+ "_" + uploadDownload.getCompilation() + "_"
									+ uploadDownload.getVersiontype());
					
				} else {
					channelHandlerContext.writeAndFlush("$R01|0\r\n");
				}
			}
			Thread.sleep(1000);
			StringBuffer R24 = new StringBuffer("$R24|");

			if (e.getPhone1() == null || e.getPhone1().equals("")) {
				R24.append("0,0:");
			} else {
				String[] phone1Arr = e.getPhone1().split(",");
				R24.append(phone1Arr[1] + "," + GB2312Utils.gb2312eecode(phone1Arr[0]) + ":");
			}
			if (e.getPhone2() == null || e.getPhone2().equals("")) {
				R24.append("0,0\r\n");
			} else {
				String[] phone2Arr = e.getPhone2().split(",");
				R24.append(phone2Arr[1] + "," + GB2312Utils.gb2312eecode(phone2Arr[0]) + "\r\n");
			}
			
			
			// 增加R06,发送名字和地址
			channelHandlerContext.writeAndFlush(getR06(user));
			Thread.sleep(1000);
			
			//发送蓝牙列表
			String bluetoothList = equipment.getBluetoothList();
			if(StringUtils.isNotBlank(bluetoothList)){
				bluetoothList = bluetoothList.substring(1, bluetoothList.length()-1).replace("\"", "");
			}else{
				bluetoothList = "";
			}
			channelHandlerContext.writeAndFlush("$R28|"+bluetoothList+"\r\n");
			Thread.sleep(1000);
			
			//channelHandlerContext.writeAndFlush("$R18|" + e.getBluetoothType() + ":"
					//+ e.getBluetoothName() + ":" + e.getBluetoothmac() + "\r\n");

			channelHandlerContext.writeAndFlush(R24.toString());
			
		} else {
			channelHandlerContext.writeAndFlush("$R01|0\r\n");
		}
	}
	/**
	 * 上传步数和卡路里数据
	 * @param texts
	 * @param imei
	 * @param equipment
	 */
	private static void T02(String[] texts,String imei,Equipment equipment){
		logger.info("才健测试>>>>>>>>>>>>>>>>>>>>>"+imei);
		User user = userservice.getUser(imei);
		if (user != null) {
			if (equipment != null) {
				EquipmentData edata = new EquipmentData();

				String[] hs = texts[2].split(",");
				for (int i = 0; i < hs.length; i++) {
					String h = hs[i].substring(0, 2);
					String text = hs[i].substring(3, hs[i].length());
					String q = hs[i].substring(0, 1);

					if (q.equals("Q")) {
						equipment.setBluetoothElectricity(Integer.parseInt(text));
					}

					switch (h) {
					/*case "H1":
						edata.setHeartrate(Integer.parseInt(text));
						break;
					case "H2":
						edata.setHighpressure(Integer.parseInt(text));
						break;
					case "H3":
						edata.setBottompressure(Integer.parseInt(text));
						break;
					case "H4":
						edata.setQxygen(Integer.parseInt(text));
						break;
					case "H5":
						edata.setMocrocirculation(Integer.parseInt(text));
						break;
					case "H6":
						edata.setHrv(Integer.parseInt(text));
						break;
					case "H7":
						edata.setMood(Integer.parseInt(text));
						break;
					case "H8":
						edata.setBreathe(Integer.parseInt(text));
						break;*/
					case "G1":
						edata.setStepWhen(Integer.parseInt(text));
						break;
				/*	case "G2":
						edata.setSleeping(Double.parseDouble(text));
						break;*/
					case "G3":
						edata.setCarrieroad(Integer.parseInt(text));
						break;
			/*		case "G4":
						edata.setSedentary(text);
						break;
					case "G5":
						edata.setCrash(Integer.parseInt(text));
						break;
					case "G6":
						edata.setMovementstate(Integer.parseInt(text));
						break;
					case "T1":
						edata.setBodytemp(Integer.parseInt(text));
						break;
					case "T2":
						edata.setHumidity(Integer.parseInt(text));
						break;*/
					case "M1":
						equipment.setLordpower(Integer.parseInt(text));
						break;
					case "M2":
						equipment.setSignalxhao(text);
						break;
					}
				}
				edata.setUserId(user.getId());
				boolean stu = false;
				stu = eqdataserive.addEquipmentData(edata);
				if (stu) {
				} else {
				}
				equipment.setUpdatetime(new Date());
				boolean est = eservice.updateEquipment(equipment);
				if (est) {
				} else {
				}
			} else {
			}
		} else {
			logger.info(imei + ">>>>>>>>>>>>>>>>>>>该设备没有绑定使用者,不需要上传数据");
		}
	}

}
