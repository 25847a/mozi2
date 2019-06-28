package com.sy.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.mapper.JfhealthdaoMapper;
import com.sy.pojo.JfhealthNew;
import com.sy.pojo.Jfhealthdao;
import com.sy.pojo.User;
import com.sy.service.JfhealthNewService;
import com.sy.service.JfhealthdaoService;
import com.sy.service.UserEqService;
import com.sy.service.UserService;
import com.sy.utils.Managementconstant;

@Service
public class JfhealthNewServiceImpl extends ServiceImpl<JfhealthNewMapper, JfhealthNew> implements JfhealthNewService {
	@Autowired
	private JfhealthNewMapper jfhealthNewMapper;

	
}
