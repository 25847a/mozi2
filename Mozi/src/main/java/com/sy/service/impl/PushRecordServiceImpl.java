package com.sy.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.common.ResultData;
import com.sy.mapper.PushRecordMapper;
import com.sy.pojo.PushRecord;
import com.sy.service.PushRecordService;

@Service
public class PushRecordServiceImpl implements PushRecordService {


	@Autowired
	private PushRecordMapper pushRecordMapper;

	
	/**
	 * 插入预警历史记录
	 * @param pushRecord
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int insertPushRecord(PushRecord pushRecord) throws SQLException {
		int row =pushRecordMapper.insertPushRecord(pushRecord);
		return row;
	}

	/**
	 * 查询预警记录的各项总数
	 * @param map
	 * @return
	 */
	@Override
	public ResultData<Map<String,Object>> queryPushRecordInfo(Map<String, String> map,ResultData<Map<String,Object>> re) throws Exception {
		Map<String,Object> data = pushRecordMapper.queryPushRecordCount(map);
		if(data!=null){
			List<Map<String,String>> list =pushRecordMapper.queryPushRecordList(map);
			data.put("chartData", list);
			re.setData(data);
			re.setCode(200);
			re.setMessage("获取成功");
		}else{
			re.setCode(350);
			re.setMessage("当天无预警记录");
		}
		return re;
	}
	

}
