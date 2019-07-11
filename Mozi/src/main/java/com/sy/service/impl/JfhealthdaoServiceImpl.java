package com.sy.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.common.ResultBase;
import com.sy.mapper.JfhealthdaoMapper;
import com.sy.mapper.PushMapper;
import com.sy.pojo.Jfhealthdao;
import com.sy.pojo.Push;
import com.sy.pojo.User;
import com.sy.service.JfhealthdaoService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.utils.Managementconstant;

@Service
public class JfhealthdaoServiceImpl extends ServiceImpl<JfhealthdaoMapper, Jfhealthdao> implements JfhealthdaoService {
	@Autowired
	private JfhealthdaoMapper jfhealthdaoMapper;

	@Override
	public boolean addJfhealthdao(Jfhealthdao j) {
		Integer num = jfhealthdaoMapper.insertSelective(j);
		if (num != 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Jfhealthdao selelctJfhealthdao(String imei) {
		if (jfhealthdaoMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			jfhealthdaoMapper = (JfhealthdaoMapper) webApplicationContext.getBean("jfhealthdaoMapper");
		}
		Integer id = jfhealthdaoMapper.selelctJfhealthdao(imei);
		//System.out.println("获取到的id" + id);
		Jfhealthdao health = jfhealthdaoMapper.selectByPrimaryKey(id);
		if (health == null) {
			UserService userservice = new UserServiceImpl();
			UserEqService usereqservice = new UserEqServiceImpl();
			Integer userid = usereqservice.getimei(imei);
			User u = userservice.getUser(userid);
			String account = Managementconstant.channel_id + String.valueOf(u.getId());
			//System.out.println("绑定惊凡账号》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》" + account);
			// 输入默认的健康值
			health = new Jfhealthdao();
			health.setAmedicalreport("");
			health.setBloodoxygen(70);
			health.setSbpAve(68);
			health.setDbpAve(76);
			health.setCreatetime(new Date());
			health.setHeartrate(70);
			health.setHRV(59);
			health.setMicrocirculation(60);
			health.setRespirationrate(70);
			health.setPhone(account);
			health.setImei(imei);
		}
		return health;
	}

	public static Integer randomvalue(int value) {
		// int n = (int)(value * 0.05);
		// value = (int) (value + n * Math.random());
		// System.out.println(value);
		// return value;
		value = value + (int) ((value * 5) / 100);
		return value;
	}

	public static void main(String[] args) {
		/*
		 * double x =100+(100*0.05*Math.random()); System.out.println(x);
		 */
		// int value = 135;
		// value = value+(int)((value * 5) / 100);
		System.out.println(randomvalue(125));

	}
	/**
	 * 阿健-查询校验表
	 * 
	 * @param imei
	 * @param account
	 * @return
	 */
	@Override
	public Jfhealthdao JfhealthdaoInfo(String imei, String account) {
		if (jfhealthdaoMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
			jfhealthdaoMapper = (JfhealthdaoMapper) webApplicationContext.getBean("jfhealthdaoMapper");
		}
		return jfhealthdaoMapper.JfhealthdaoInfo(imei, account);
	}

	@Override
	public Jfhealthdao getjfhealthdao(String phone) {
		return jfhealthdaoMapper.getjfhealthdao(phone);
	}

	@Override
	public void updatajf(Jfhealthdao bean) {
		jfhealthdaoMapper.updateByPrimaryKey(bean);
		
	}

	@Override
	public void delectjfhealthdao(String phone) {
		EntityWrapper<Jfhealthdao> ew = new EntityWrapper<Jfhealthdao>();
		ew.eq("phone", phone);
		int aa = jfhealthdaoMapper.delete(ew);
			System.out.println("aa"+aa);
	}
	/**
	 * 修改人工学习
	 * @return
	 */
	@Override
	public ResultBase updateJfhealthdao(Jfhealthdao jfhealthdao,ResultBase re)throws Exception{
		jfhealthdao.setPhone("mozistar"+jfhealthdao.getPhone());
		EntityWrapper<Jfhealthdao> ew = new EntityWrapper<Jfhealthdao>();
		ew.eq("phone", jfhealthdao.getPhone());
		int row =jfhealthdaoMapper.update(jfhealthdao, ew);
		if(row>0){
			re.setCode(200);
			re.setMessage("修改成功");
		}else{
			re.setCode(400);
			re.setMessage("修改失败");
		}
		return re;
		
	}
}
