package com.sy.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sy.common.ResultData;
import com.sy.mapper.EquipmentMapper;
import com.sy.mapper.WaveformMapper;
import com.sy.pojo.Equipment;
import com.sy.pojo.UserEq;
import com.sy.pojo.Waveform;
import com.sy.service.UserEqService;
@Controller
@RequestMapping(value = "waveform")
public class WaveformController {
	
	@Autowired
	private WaveformMapper waveformmapper;
	@Autowired
	private UserEqService usereqservice;
	@Autowired
	private EquipmentMapper equipmentMapper;
	
		
		@RequestMapping(value = "getData")
		@ResponseBody
		public ResultData<List<Waveform>> getData(@RequestBody Map map) {
			String mid =(String)map.get("mid");
			List<UserEq> ues = usereqservice.selectuserqe(Integer.valueOf(mid));
			Set<String> set = new HashSet<>();
			if(ues!=null&&ues.size()>0){
				for (UserEq ue : ues) {
					Equipment e = equipmentMapper.selectById(ue.getEqId());
					set.add(e.getImei());
				}
			}
			ResultData<List<Waveform>> re = new ResultData<>();
			List<Waveform> wflist = new ArrayList<>();
			if(set.size()>0){
				for (String imei : set) {
						Waveform waveform = waveformmapper.getWaveform(imei);
						if(waveform!=null){
							wflist.add(waveform);
						}
				}
				re.setData(wflist);
			}
			re.setCode(200);
			re.setMessage("ok");
			return re;
		}
}
