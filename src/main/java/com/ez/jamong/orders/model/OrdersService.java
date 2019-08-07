package com.ez.jamong.orders.model;

import java.util.List;
import java.util.Map;

import com.ez.jamong.common.DateSearchVO;

public interface OrdersService {
	public int insertOrders(OrdersVO orderVo);
	public List<Map<String, Object>> selectByUserNo(DateSearchVO dateSearchVo);
	public int selectTotalRecord(DateSearchVO dateSearchVo);
	public List<Map<String, Object>> expertSelectByUserNo(DateSearchVO dateSearchVo);
	public int expertSelectTotalRecord(DateSearchVO dateSearchVo);
	public int updateProgressByOrderNo(OrdersVO ordersVo);
}
