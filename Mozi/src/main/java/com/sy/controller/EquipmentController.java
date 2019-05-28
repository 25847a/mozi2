package com.sy.controller;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sy.common.ResultBase;
import com.sy.common.ResultData;
import com.sy.mapper.SensorstatusMapper;
import com.sy.mapper.UserEqMapper;
import com.sy.mapper.UserMapper;
import com.sy.nettyulit.BluetoothMap;
import com.sy.nettyulit.NettyChannelMap;
import com.sy.pojo.Equipment;
import com.sy.pojo.Management;
import com.sy.pojo.Sensorstatus;
import com.sy.pojo.User;
import com.sy.pojo.UserEq;
import com.sy.service.EquipmentService;
import com.sy.utils.PageModel;
import com.sy.vo.EquipmentManagement;
import com.sy.vo.EquipmentVo;
import com.sy.vo.Equipmentbluetooth;
import com.sy.vo.Equipmentstatus;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.SocketChannel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "equipment")
public class EquipmentController {

	private final static Logger logger = LoggerFactory.getLogger(EquipmentController.class);
	@Autowired
	private EquipmentService equipmentservice;
	@Autowired
	private SensorstatusMapper sensorstatusMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserEqMapper userEqMapper;
	/**
	 * 获取设备基本信息
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "selectEquipment")
	@ResponseBody
	public ResultData<Equipmentbluetooth> selectdata(@RequestBody Map m) {
		ResultData<Equipmentbluetooth> re = new ResultData<Equipmentbluetooth>();
		//获取到设备基本信息
		Equipment e = equipmentservice.selectquipmentimei((String) m.get("imei"));
		boolean bluetoothStatus = false;
		boolean EqStatus = false;
		//H:1 在线   
		if (e.getEqStatus().equals("H:1")) {
			EqStatus = true;
		}
		
		if (e.getBluetoothType() != null && e.getBluetoothType().equals("1")) {
			bluetoothStatus = true;
		}
		String phone1 = "";
		String phone2 = "";
		String phone1name = "";
		String phone2name = "";

		if (StringUtils.isNotBlank(e.getPhone1()) && !e.getPhone1().equals("0,0")) {
			phone1 = e.getPhone1().split(",")[1];
			phone1name = e.getPhone1().split(",")[0];
		}
		if (StringUtils.isNotBlank(e.getPhone2()) && !e.getPhone2().equals("0,0")) {
			phone2 = e.getPhone2().split(",")[1];
			phone2name = e.getPhone2().split(",")[0];
		}

		String signalxhao = e.getSignalxhao();

		Double valueOf = Double.valueOf(signalxhao);
		Double signalxhaovalue = valueOf / 31 * 100;
		DecimalFormat df = new DecimalFormat("0");
		//获取imei设备最新的传感器状态
		Sensorstatus sen = sensorstatusMapper.selecttimesensorstatus(e.getImei());
		String bluetoothList = e.getBluetoothList();//获取蓝牙列表数据
		JSONArray arr = null;
		if (bluetoothList == null) {
			arr = new JSONArray();
		} else {
			arr = JSONArray.fromObject(bluetoothList);
		}

		Equipmentbluetooth eq = null;
		if (sen == null) {
			eq = new Equipmentbluetooth(e.getId(), e.getImei(), e.getLordpower(), df.format(signalxhaovalue) + "%",
					e.getBluetoothType(), EqStatus, e.getCreatetime(), e.getUpdatetime(), e.getEqtype(),
					e.getBluetoothName(), bluetoothStatus, e.getBluetoothElectricity(), phone1, phone2, e.getName(),
					e.getVersion(), e.getUploadtime(), phone1name, phone2name, e.getBluetoothmac(), null, null, arr);
		} else {
			String H = null;
			String G = null;
			if (sen.getH().equals("H:1")) {
				H = "正常";
			} else {
				H = "错误";
			}
			if (sen.getG().equals("G:1")) {
				G = "正常";
			} else {
				G = "错误";
			}
			eq = new Equipmentbluetooth(e.getId(), e.getImei(), e.getLordpower(), df.format(signalxhaovalue) + "%",
					e.getBluetoothType(), EqStatus, e.getCreatetime(), e.getUpdatetime(), e.getEqtype(),
					e.getBluetoothName(), bluetoothStatus, e.getBluetoothElectricity(), phone1, phone2, e.getName(),
					e.getVersion(), e.getUploadtime(), phone1name, phone2name, e.getBluetoothmac(), H, G, arr);
		}

		re.setCode(200);
		re.setData(eq);
		re.setMessage("获取设备基本信息成功！！！");

		return re;
	}

	/**
	 * 获取管理人员还有设备的权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "userequipments")
	@ResponseBody
	public ResultData<List<EquipmentVo>> selelctequipments(@RequestBody Map m) {
		ResultData<List<EquipmentVo>> re = new ResultData<List<EquipmentVo>>();
		List<EquipmentVo> evs = equipmentservice.selelctequipments(Integer.parseInt(String.valueOf(m.get("userid"))));
		if (evs != null) {
			re.setCode(200);
			re.setData(evs);
			re.setMessage("获取获取该用户关联设备成功！！！");
		} else {
			re.setCode(400);
			re.setMessage("该用户没有关联的设备！！！");
		}
		return re;
	}

	/**
	 * 设备管理主页
	 * 
	 * @return
	 */
	@RequestMapping(value = "EquipmentManagement")
	@ResponseBody
	public ResultData<EquipmentManagement> EquipmentManagement(@RequestBody Map m) {
		ResultData<EquipmentManagement> re = new ResultData<EquipmentManagement>();
		Equipment e = equipmentservice.selectquipmentimei((String) m.get("imei"));
		if (e != null) {
			// 在线状态
			boolean ifstatus = false;
			if (e.getBluetoothStatus().equals("H:1")) {
				ifstatus = true;
			}

			String equipmenttype = null;
			if (e.getEqtype().equals("1")) {
				equipmenttype = "衣服";
			} else if (e.getEqtype().equals("2")) {
				equipmenttype = "手带";
			} else if (e.getEqtype().equals("3")) {
				equipmenttype = "头带";
			}
			EquipmentManagement em = new EquipmentManagement(e.getName(), e.getName(), equipmenttype, ifstatus,
					e.getSignalxhao(), e.getLordpower(), e.getImei(), e.getVersion());
			re.setData(em);
			re.setCode(200);
			re.setMessage("获取设备信息成功！！！");
		} else {
			re.setCode(350);
			re.setMessage("没有该设备的信息！！！");
		}
		return re;

	}

	/**
	 * 修改设备紧急联系人
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateurgent")
	@ResponseBody
	public ResultBase updateurgent(@RequestBody Map m) {
		ResultBase re = new ResultBase();
		String phone1 = (String) m.get("phone1");
		String phone1name = (String) m.get("phone1name");
		String phone2name = (String) m.get("phone2name");
		String phone2 = (String) m.get("phone2");
		String imei = (String) m.get("imei");
		Equipment e = equipmentservice.selectquipmentimei(imei);
		if (e != null) {
			if (phone1 != null && !phone1.equals("") && phone1name != null && !phone1name.equals("")) {
				e.setPhone1(phone1name + "," + phone1);
			} else {
				e.setPhone1(0 + "," + 0);
			}

			if (phone2 != null && !phone2.equals("") && phone2name != null && !phone2name.equals("")) {
				e.setPhone2(phone2name + "," + phone2);
			} else {
				e.setPhone2(0 + "," + 0);
			}

			boolean st = equipmentservice.updateEquipment(e);
			if (st) {
				Equipment newe = equipmentservice.selectquipmentimei(imei);
				re.setCode(200);
				re.setMessage("修改紧急联系成功!!!");
				try {

					Channel c = NettyChannelMap.get((String) m.get("imei"));

					c.writeAndFlush("$R24|" + newe.getPhone1().split(",")[1] + ","
							+ getCode(newe.getPhone1().split(",")[0]) + ":" + newe.getPhone2().split(",")[1] + ","
							+ getCode(newe.getPhone2().split(",")[0]) + "\r\n");
				} catch (Exception e2) {

				}
			} else {
				re.setCode(350);
				re.setMessage("修改紧急联系异常!!!");
			}
		} else {
			re.setCode(350);
			re.setMessage("该设备不存在!!!");
		}
		return re;
	}

	@RequestMapping(value = "list")
	public ModelAndView list(Integer pageNo, String keyWord, String eqStatus, String time, HttpSession session) {
		ModelAndView mo = new ModelAndView();

		Map<String, Object> map = new HashMap<>();

		Management m = (Management) session.getAttribute("USER");
		String role = m.getRole();
		if ("代理商".equals(role)) {
			map.put("agentid", m.getId());
		}

		map.put("keyWord", keyWord);
		map.put("eqStatus", eqStatus);
		map.put("time", time);

		PageModel<Equipment> pagemodel = equipmentservice.getusersone(pageNo, map);
		mo.setViewName("equipment");
		mo.addObject("pagemodel", pagemodel);
		mo.addObject("keyWord", keyWord);
		mo.addObject("eqStatus", eqStatus);
		mo.addObject("time", time);
		return mo;
	}

	public static String getCode(String content) throws UnsupportedEncodingException {
		byte[] bytes = content.getBytes("gb2312");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toHexString(bytes[i] & 0xff).toUpperCase() + "");
		}

		return sb.toString();
	}

	@RequestMapping(value = "updatebluetooth")
	@ResponseBody
	public ResultBase updateEquipment(@RequestBody Equipment e) {
		ResultBase re = new ResultBase();

		Equipment newe = equipmentservice.selectequipment(e.getId());
		BluetoothMap.deletesb(newe.getImei());
		SocketChannel c = (SocketChannel) NettyChannelMap.get(newe.getImei());
		try {
			ChannelFuture writeAndFlush = c.writeAndFlush("$R18|" + newe.getBluetoothType() + ":" + e.getBluetoothName()
					+ ":" + e.getBluetoothmac() + "\r\n");
			writeAndFlush.addListener(new ChannelFutureListener() {
				public void operationComplete(ChannelFuture future) {
					logger.info("发送成功,连接设备,开始操作子设备");
				}
			});
			// 如果是切换蓝牙,就先更新为0,因为主控会断开蓝牙,防止新蓝牙连接不上出现不同步问题
			Equipment equipment = new Equipment();
			if (!e.getBluetoothmac().equals("000000000000")) {
				equipment.setId(e.getId());
				equipment.setBluetoothName("000000000000");
				equipment.setBluetoothStatus("0");
				equipment.setBluetoothType("0");
				equipment.setBluetoothmac("000000000000");
				equipmentservice.updateEquipment(equipment);
			}
			boolean st;
			int ss = 0;
			while (true) {
				Thread.sleep(1000);
				st = task(newe.getImei());
				if (st) {
					re.setMessage("操作成功！！！");
					/*if (e.getBluetoothmac().equals("000000000000")) {
						e.setBluetoothName("000000000000");
						e.setBluetoothStatus("0");
						e.setBluetoothType("0");
						equipmentservice.updateEquipment(e);
						re.setMessage("解绑成功！！！");
					} else {
						e.setBluetoothStatus("1");
						equipmentservice.updateEquipment(e);
						re.setMessage("绑定成功！！！");
					}*/
					re.setCode(200);
					break;
				} else {
					if (ss == 40) {
						re.setCode(601);
						re.setMessage("操作失败！！！");
						return re;
					}
					ss++;
				}
			}
		} catch (Exception e2) {
			logger.info(e2.getMessage());
			re.setCode(350);
			re.setMessage("设备不在线！！");
		}
		return re;
	}

	/**
	 * 添加子设备
	 * 
	 * @param map
	 *            bluetoothList 蓝牙列表 eqid
	 * @return
	 */
	@RequestMapping(value = "updateBluetoothList")
	@ResponseBody
	public ResultBase updateBluetoothList(@RequestBody JSONObject jsonObject) {
		ResultBase re = new ResultBase();
		try {

			JSONArray jsonArray = jsonObject.getJSONArray("bluetoothList");
			String eqid = jsonObject.getString("eqid");
			String imei = jsonObject.getString("imei");
			Map<String, Object> map = new HashMap<>();
			map.put("bluetoothList", jsonArray.toString());
			map.put("eqid", eqid);
			equipmentservice.updateBluetoothList(map);

			SocketChannel c = (SocketChannel) NettyChannelMap.get(imei);

			if (c != null) {
				String bluetoothList = jsonArray.toString();
				if (StringUtils.isNotBlank(bluetoothList)) {
					bluetoothList = bluetoothList.substring(1, bluetoothList.length() - 1).replace("\"", "");

				} else {
					bluetoothList = "";
				}
				c.writeAndFlush("$R28|" + bluetoothList + "\r\n");
				re.setCode(200);
				re.setMessage("操作成功");
			} else {
				re.setCode(200);
				re.setMessage("操作成功!");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			re.setCode(400);
			re.setMessage("操作失败");
		}
		return re;
	}

	/**
	 * 
	 * 
	 */
	public static boolean task(String newimei) {
		boolean st = false;

		String msg = BluetoothMap.getBs(newimei);
		System.out.println("获取的imei" + newimei + ">>>>>>>>>>>>>>>>" + msg);
		if (msg != null) {
			if (msg.contains("T08") || msg.contains("R18|OK")) {
				st = true;
			}
			if (msg.contains("R18|ERR1")) {
				st = false;
			}
		}
		BluetoothMap.deletesb(newimei);
		return st;

	}

	/**
	 * 校准
	 * 
	 * @throws Exception
	 * 
	 * 
	 */
	public static boolean taskhealthcali(String imei) throws Exception {
		boolean st = false;
		int i = 0;
		while (true) {
			Thread.sleep(1000);
			String msg = BluetoothMap.gethealthmap(imei);
			if (msg != null && msg.contains("A")) {
				st = true;
				logger.info("设备号<" + imei + ">健康数据学习成功=======================" + msg);
				break;
			} else if (msg != null && msg.contains("b")) {
				logger.info("设备号<" + imei + ">学习失败,返回的数据解析失败======");
				break;
			} else {
				if (i == 52) {
					logger.info("设备号<" + imei + ">学习失败,学习超时======");
					break;
				}
				i++;
			}
		}
		BluetoothMap.deletehealthmap(imei);
		return st;

	}

	@RequestMapping(value = "healthcali")
	@ResponseBody
	public ResultBase healthcali(@RequestBody Map m) {
		String imei = (String) m.get("imei");
		ResultBase re = new ResultBase();
		try {
			Equipment e = equipmentservice.selectquipmentimei(imei);
			System.out.println(imei + "=====imei ");
			if (e != null) {
				String bluetoothType = e.getBluetoothType();

				if (bluetoothType!=null&& bluetoothType.equals("1")) {
					
					BluetoothMap.deletehealthmap(imei);
					Channel c = NettyChannelMap.get(imei);

					if (c != null) {

						ChannelFuture writeAndFlush = c.writeAndFlush("$R17\r\n");
						writeAndFlush.addListener(new ChannelFutureListener() {
							public void operationComplete(ChannelFuture future) {
								logger.info("发送成功,开始学习");
							}
						});
						boolean st = taskhealthcali(imei);
						if (st) {
							User user = userMapper.getUser(imei);
							user.setCalibration("1");
							userMapper.updateCalibration(user);
							re.setCode(200);
							re.setMessage("学习完成！！！");
						} else {
							re.setCode(602);
							re.setMessage("学习失败！！！");
						}
					} else {
						logger.info(imei+"=====不在线");
						re.setCode(602);
						re.setMessage("设备不在线");
					}
				} else {
					logger.info(imei+ ">>>>>学习失败>>>>>>bluetoothType===="+bluetoothType);
					re.setCode(602);
					re.setMessage("没有衣服连接");
				}
			} else {
				re.setCode(602);
				re.setMessage("imei号错误");
			}
			// Thread.sleep(55000);
		} catch (Exception e) {
			logger.info(e.getMessage());
			re.setCode(350);
			re.setMessage("学习失败");
		}
		return re;
	}

	private User selectUser(String imei) {
		User user = null;
		Equipment equipment = equipmentservice.selectquipmentimei(imei);
		if (equipment != null) {
			UserEq usereq = userEqMapper.ifuse(equipment.getId());
			user = userMapper.selectByPrimaryKey(usereq.getUserId());
			user.setImei(imei);
			userMapper.updateImei(user);
		}
		return user;
	}

}
