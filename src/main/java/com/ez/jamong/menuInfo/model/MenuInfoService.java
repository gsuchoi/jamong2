package com.ez.jamong.menuInfo.model;

import java.util.List;
import java.util.Map;

public interface MenuInfoService {
	public int suspendedByPdNo(int productNo);
	public List<MenuInfoVO> selectMenuInfoAll();
	public MenuInfoVO selectMenuInfoByNo(int productNo);
	public int updateMenuInfo(MenuInfoVO vo);
	public int updateActivation(MenuInfoVO vo);
	public int deleteMenuInfo(int productNo);
	public Map<String, Object> selectMenuinfoView(int productNo);
	public List<Map<String, Object>> selectMenuinfoAllView();
	public List<Map<String, Object[]>> selectMenuinfoYearCount();
	public List<Map<String, Object[]>> selectAdsYearCount();
	
	//주영 상품등록 사용 메서드
	public int saveProduct(MenuInfoVO vo);
	public MenuInfoVO NonAvtivatedProduct(int expertNo);
	public int updateMenuInfo2(MenuInfoVO menuInfoVo);
	public int registProduct(int productNo);
	
	//원준 상품목록화면 사용 메서드
	public List<MenuInfoExtendsVO> menuinfoByList(MenuInfoSearchVO searchVo);
	public int selectTotalCount(MenuInfoSearchVO searchVo);
}