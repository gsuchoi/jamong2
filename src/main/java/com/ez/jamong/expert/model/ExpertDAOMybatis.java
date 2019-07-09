package com.ez.jamong.expert.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.jamong.common.SearchVO;

@Repository
public class ExpertDAOMybatis implements ExpertDAO{
	@Autowired private SqlSessionTemplate session;
	private String namespace="config.mybatis.mapper.oracle.expert.";

	@Override
	public List<Map<String, Object>> selectExpertSearch(SearchVO searchVo) {
		return session.selectList(namespace+"selectExpertSearch", searchVo);
	}

	@Override
	public int selectTotalCount(SearchVO searchVo) {
		return session.selectOne(namespace+"selectTotalCount", searchVo);
	}
	
	
}