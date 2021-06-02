package com.kh.spring.menu.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.menu.model.vo.Menu;

@Repository
public class MenuDaoImpl implements MenuDao{
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Menu> selectMenuList() {
		return session.selectList("menu.selectMenuList");
	}

	@Override
	public List<Menu> selectMenuListByTypeAndTaste(Map<String, Object> param) {
		return session.selectList("menu.selectMenuListByTypeAndTaste",param);
	}

	@Override
	public int insertMenu(Menu menu) {
		return session.insert("menu.insertMenu",menu);
	}

	@Override
	public Menu selectMenu(int id) {
		return session.selectOne("menu.selectMenu",id);
	}

	@Override
	public int updateMenu(Menu menu) {
		return session.update("menu.updateMenu",menu);
	}

	@Override
	public int deleteMenu(String id) {
		return session.delete("menu.deleteMenu",id);
	}
}
