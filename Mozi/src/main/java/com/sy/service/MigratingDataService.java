package com.sy.service;

import java.sql.SQLException;
import com.sy.utils.DataRow;

/**
 * 该类作用:把删除数据迁移到从数据库存放
 * @author Administrator
 */
public interface MigratingDataService {

	/**
	 * 迁移数据
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public boolean migratingData(DataRow dataRow)throws SQLException;
}
