package com.sy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.sy.mapper.BluetoothMapper;
import com.sy.pojo.Bluetooth;
import com.sy.service.BluetoothService;
@Service
public class BluetoothServiceImpl implements BluetoothService{
    @Autowired
	private BluetoothMapper bluetoothMapper;

	@Override
	public Bluetooth getBluetoothid(String imei) {
		if (bluetoothMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			bluetoothMapper = (BluetoothMapper) webApplicationContext
					.getBean("bluetoothMapper");
		}
		// TODO Auto-generated method stub
		return bluetoothMapper.getBluetoothid(imei);
	}

	@Override
	public boolean updateBluetooth(Bluetooth b) {
		if (bluetoothMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			bluetoothMapper = (BluetoothMapper) webApplicationContext
					.getBean("bluetoothMapper");
		}
		Integer num = bluetoothMapper.updateByPrimaryKeySelective(b);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	
	}

	@Override
	public boolean addBluetooth(Bluetooth b) {
		if (bluetoothMapper == null) {
			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			bluetoothMapper = (BluetoothMapper) webApplicationContext
					.getBean("bluetoothMapper");
		}
		Integer num = bluetoothMapper.insertSelective(b);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}
}
