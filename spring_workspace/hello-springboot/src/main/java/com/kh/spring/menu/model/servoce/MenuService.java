package com.kh.spring.menu.model.servoce;

import java.util.List;
import java.util.Map;

import com.kh.spring.menu.model.vo.Menu;

public interface MenuService {

	List<Menu> selectMenuList();

	List<Menu> selectMenuListByTypeAndTaste(Map<String, Object> param);

	int insertMenu(Menu menu);

	Menu selectMenu(int id);

	int updateMenu(Menu menu);

}
