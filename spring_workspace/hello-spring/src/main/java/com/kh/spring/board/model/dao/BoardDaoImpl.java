package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.BoardExt;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<BoardExt> selectBoardList() {
		return session.selectList("board.selectBoardList");
	}

	@Override
	public List<BoardExt> selectBoardList(Map<String, Object> param) {
		int offset = (int)param.get("offset");
		int limit = (int)param.get("limit");
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		//public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds)
		return session.selectList("board.selectBoardList", null, rowBounds);
	}
	
}
