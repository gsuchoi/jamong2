package com.ez.jamong.orders.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.jamong.common.DateSearchVO;

@Repository
public class OrdersDAOMybatis implements OrdersDAO{
	@Autowired private SqlSessionTemplate session;
	private String namespace="config.mybatis.mapper.oracle.orders.";
	@Override
	public int insertOrders(OrdersVO orderVo) {
		return session.insert(namespace+"insertOrders",orderVo);
	}
	@Override
	public List<Map<String, Object>> selectByUserNo(DateSearchVO dateSearchVo) {
		return session.selectList(namespace+"selectByUserNo", dateSearchVo);
	}
	@Override
	public int selectTotalRecord(DateSearchVO dateSearchVo) {
		return session.selectOne(namespace+"selectTotalRecord", dateSearchVo);
	}
	@Override
	public List<Map<String, Object>> expertSelectByUserNo(DateSearchVO dateSearchVo) {
		return session.selectList(namespace+"expertSelectByUserNo", dateSearchVo);
	}
	@Override
	public int expertSelectTotalRecord(DateSearchVO dateSearchVo) {
		return session.selectOne(namespace+"expertSelectTotalRecord", dateSearchVo);
	}
}
