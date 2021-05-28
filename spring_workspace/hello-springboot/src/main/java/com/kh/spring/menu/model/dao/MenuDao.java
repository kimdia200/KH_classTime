package com.kh.spring.menu.model.dao;

import java.util.List;

import com.kh.spring.menu.model.vo.Menu;

public interface MenuDao {

	List<Menu> selectMenuList();

}
