package com.sy.service;

import com.sy.pojo.Bluetooth;

public interface BluetoothService {

	/**根据imei获取蓝牙数据
	 * @param imei
	 * @return
	 */
	public Bluetooth getBluetoothid(String imei);
	
	/**更新蓝牙数据
	 * @param b
	 * @return
	 */
	public boolean updateBluetooth(Bluetooth b );
	
	/**添加蓝牙数据
	 * @param b
	 * @return
	 */
	public boolean addBluetooth(Bluetooth b );
}
