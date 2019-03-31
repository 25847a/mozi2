package com.sy.service;

import java.util.List;

import com.sy.pojo.Versionhistory;

public interface VersionhistoryService {
	
	public boolean addVersionhistory(Versionhistory v);
	
	public boolean deleteversionhistory(Integer id);
	
	public List<Versionhistory> selectVersionhistory();
	
	public Versionhistory seleversiontory(Integer id );
}
