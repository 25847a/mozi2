package com.sy.utils;

import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

/**
 * jian
 * @author Administrator
 *
 */
public class MyMetaObjectHandler extends MetaObjectHandler{

	// 新增的时候自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createtime",new Date(),metaObject);
        this.setFieldValByName("updatetime",new Date(),metaObject);
    }

	// 更新的税后自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatetime", new Date(),metaObject);
    }
}
