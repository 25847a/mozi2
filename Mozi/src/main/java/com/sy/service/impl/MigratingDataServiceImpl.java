package com.sy.service.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.date.converter.DataSourceType;
import com.sy.date.converter.DynamicDataSourceHolder;
import com.sy.mapper.JfhealthNewMapper;
import com.sy.pojo.JfhealthNew;
import com.sy.service.MigratingDataService;
import com.sy.utils.DataRow;

/**
 * 该类作用:把删除数据迁移到从数据库存放
 * @author Administrator
 */
@Service
public class MigratingDataServiceImpl implements MigratingDataService{

	@Autowired
	private JfhealthNewMapper jfNewMapper;
	
	/**
	 * 迁移数据
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean migratingData(DataRow map) throws SQLException {
		//
	//	JfhealthNew j =jfNewMapper.selectJfhealthNew("mozistar"+userId);
		JfhealthNew j = new JfhealthNew();
		j.setPhone("mozistar28971");
		j.setImei("518110320191461");	
		DynamicDataSourceHolder.setDbType(DataSourceType.SLAVE);
		int a111 = jfNewMapper.insert(j);
		System.out.println(a111);
		DynamicDataSourceHolder.clearDataSource();
		
		/*int a = positionigMapper.deletePositionigInfo(imei);//删除定位
		System.out.println("不默认者a"+a);
		int a1 = pushMapper.deletePushInfo(userId);//删除所有的预警关联
		System.out.println("a1"+a1);
		int a2 = eqMapper.deleteguardian(eqId); //删除关系表数据
		System.out.println("a2"+a2);
		int a3 = userservice.deleteUser(userId);//更改用户OK==删除用户
		System.out.println("a3"+a3);
		int a4 = chatMapper.deleteCharInfo(imei);//APP发文本信息到设备的表
		System.out.println("a4"+a4);
		int a5 = jfhealthdaoMapper.deleteJfhealthdaoInfo( "mozistar" + userId);//删除校准数据
		System.out.println("a5"+a5);
		int a6 = sensorstatusMapper.deleteSensorstatusInfo(imei);//设备的返回数据保存的表
		System.out.println("a6"+a6);
		jMapperNew.delete(ew);*/
	//	jMapperNew.deletejfhealth("mozistar"+userId);//删除最新的健康数据
		
		//chat(APP发文本信息到设备)、comment(评论表)、equipment_data、equipment_record、group(朋友圈群组)
		//group_relation(朋友圈群组关联)、jfhealth、member(会员制度)、pushrecord(预警历史记录表)、realhealth(有效真实数据)
		//user、usercode、waveform
		return false;
	}

	
	
	
	
}
