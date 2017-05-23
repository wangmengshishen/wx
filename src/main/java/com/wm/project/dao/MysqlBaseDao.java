package com.wm.project.dao;

import java.util.List;
import java.util.Map;


public interface MysqlBaseDao<T> {
	public  List<T>queryList(String sqlMap,Map<String,Object>map);
	public  List<T>queryPage(String sqlMap,Map<String,Object>map);
	public  T queryObject(String sqlMap,Map<String,Object>map);
	public  T save(String sqlMap,T po);
}
