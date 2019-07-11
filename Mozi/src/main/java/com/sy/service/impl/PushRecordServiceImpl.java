package com.sy.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sy.common.ResultData;
import com.sy.mapper.PushRecordMapper;
import com.sy.pojo.PushRecord;
import com.sy.service.PushRecordService;
import com.sy.utils.DataRow;

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
	public ResultData<DataRow> queryPushRecordInfo(DataRow map,ResultData<DataRow> re) throws Exception {
		DataRow data = pushRecordMapper.queryPushRecordCount(map);
		DataRow dataRow = new DataRow();
		DataRow afffff = null;
		List<DataRow> rowdddfff = new ArrayList<DataRow>();
			if(data!=null){
			List<DataRow> list =pushRecordMapper.queryPushRecordList(map);
			if(!list.isEmpty()){
				Set<String> times = new HashSet<String>();
				for(int i=0;i<list.size();i++){
					times.add(list.get(i).getString("time"));
				}
				
				for(String time:times){
					List<DataRow> rowddd = new ArrayList<DataRow>();
					 afffff = new DataRow();
					for(int o=0;o<list.size();o++){//8号
						if(time.equals(list.get(o).getString("time"))){
							if(list.get(o).getInt("heartUnusual")!=0){
								dataRow = new DataRow();
								dataRow.put("heartUnusual", "心率: "+list.get(o).getInt("heartUnusual")+"次/分");
								dataRow.put("createtime", list.get(o).getString("createtime"));
								rowddd.add(dataRow);
							}
							if(list.get(o).getInt("highBloodUnusual")!=0){
								dataRow = new DataRow();
								dataRow.put("highBloodUnusual", "舒张压: "+list.get(o).getInt("highBloodUnusual")+"mmHg");
								dataRow.put("createtime", list.get(o).getString("createtime"));
								rowddd.add(dataRow);
							}
							if(list.get(o).getInt("lowBloodUnusual")!=0){
								dataRow = new DataRow();
								dataRow.put("lowBloodUnusual", "收缩压: "+list.get(o).getInt("lowBloodUnusual")+"mmHg");
								dataRow.put("createtime", list.get(o).getString("createtime"));
								rowddd.add(dataRow);
							}
						}
						
					}
					
					afffff.put("time", time);
					afffff.put("data", rowddd);
					rowdddfff.add(afffff);
					
				}
				data.put("chartData", rowdddfff);
				re.setData(data);
				re.setCode(200);
				re.setMessage("获取成功");
			}else{
				re.setCode(350);
				re.setMessage("查询不到预警记录");
			}
		
			
			
		}else{
			re.setCode(350);
			re.setMessage("无预警记录");
		}
			
		return re;
	}
	

}
