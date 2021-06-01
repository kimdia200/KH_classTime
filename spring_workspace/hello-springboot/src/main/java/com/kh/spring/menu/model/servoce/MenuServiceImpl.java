package com.kh.spring.menu.model.servoce;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.menu.model.dao.MenuDao;
import com.kh.spring.menu.model.vo.Menu;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	MenuDao menuDao;

	@Override
	public List<Menu> selectMenuList() {
		return menuDao.selectMenuList();
	}

	@Override
	public List<Menu> selectMenuListByTypeAndTaste(Map<String, Object> param) {
		return menuDao.selectMenuListByTypeAndTaste(param);
	}

	@Override
	public int insertMenu(Menu menu) {
		return menuDao.insertMenu(menu);
	}

	@Override
	public Menu selectMenu(int id) {
		return menuDao.selectMenu(id);
	}

	@Override
	public int updateMenu(Menu menu) {
		return menuDao.updateMenu(menu);
	}
}
