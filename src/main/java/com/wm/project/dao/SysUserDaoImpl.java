package com.wm.project.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.wm.project.bean.SysUser;
@Component
public class SysUserDaoImpl extends MySqlBaseDaoImpl<SysUser>{
@Resource
private SqlSession sqlSession;
public List<SysUser> queryUserInfo(Map<String, Object> map) {
	// TODO Auto-generated method stub
	return 	sqlSession.selectList("queryUserInfo", map);
}
@Override
public SqlSession getSqlSession() {
	// TODO Auto-generated method stub
	return sqlSession;
}

}
