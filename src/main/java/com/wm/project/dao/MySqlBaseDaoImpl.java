package com.wm.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.wm.project.common.SpringContextHolder;

public abstract class MySqlBaseDaoImpl<T> implements MysqlBaseDao<T> {
	public  SqlSession getSqlSession(){
		SqlSession sqlsession=SpringContextHolder.getBean("sqlSessionTemplate", SqlSession.class);
		  return sqlsession;
	}
	public List<T> queryList(String sqlMap, Map<String, Object> map) {
		return getSqlSession().selectList(sqlMap, map);
	}
	public List<T> queryPage(String sqlMap, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public T queryObject(String sqlMap, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public T save(String sqlMap, T po) {
		// TODO Auto-generated method stub
		return null;
	}

}
