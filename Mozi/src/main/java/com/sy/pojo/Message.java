package com.sy.pojo;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
@TableName("message")
public class Message extends Model<Message>{
	private static final long serialVersionUID = 1L;
	/**
	 * 编号id
	 */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 * 登录 的mid做为alias
	 */
	private Integer alias;
	/**
	 * 消息类型 0.预警信息   (其他的后续添加)
	 */
	private Integer type;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 0.未读  1.已读
	 */
	private Integer read;
	/**
	 * 创建时间
	 */
	@TableField(value = "createtime",fill = FieldFill.INSERT )
	private String createtime;


	public Integer getAlias() {
		return alias;
	}

	public void setAlias(Integer alias) {
		this.alias = alias;
	}


	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRead() {
		return read;
	}

	public void setRead(Integer read) {
		this.read = read;
	}

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}

	
	
	
		
}
