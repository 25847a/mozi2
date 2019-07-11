package com.sy.service;

import com.baomidou.mybatisplus.service.IService;
import com.sy.pojo.Useravatar;

public interface UseravatarService extends IService<Useravatar>{
	public boolean adduseravatar(Useravatar ua);
	
	public Useravatar selectavartar();

}
