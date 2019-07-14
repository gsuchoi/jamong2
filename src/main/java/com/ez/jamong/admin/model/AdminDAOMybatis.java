package com.ez.jamong.admin.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOMybatis implements AdminDAO{
	@Autowired private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.admin.";
	
	@Override
	public String selectForAdminLogin(String userid) {
		return sqlSession.selectOne(namespace+"selectForAdminLogin",userid);
	}

	@Override
	public AdminVO selectAdmin(String userid) {
		return sqlSession.selectOne(namespace+"selectAdmin",userid);
	}

	@Override
	public List<AdminVO> selectAdminAll() {
		return sqlSession.selectList(namespace+"selectAdminAll");
	}

	@Override
	public int gradeAdminUpdate(AdminVO vo) {
		return sqlSession.update(namespace+"gradeAdminUpdate",vo);
	}
	@Override
	public int insertAdmin(AdminVO vo) {
		return sqlSession.insert(namespace+"insertAdmin",vo);
	}

	@Override
	public int deleteAdmin(String id) {
		return sqlSession.insert(namespace+"deleteAdmin",id);
	}

	@Override
	public int updateAdminPwd(AdminVO vo) {
		return sqlSession.update(namespace+"updateAdminPwd",vo);
	}

}
