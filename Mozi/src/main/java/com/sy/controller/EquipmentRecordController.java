package com.sy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sy.service.EquipmentDataService;
/**
 * 湿度、温度
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "equipmentRecord")
public class EquipmentRecordController {
	
	@Autowired
	private EquipmentDataService equipmentdateservice;
	
	 
}
