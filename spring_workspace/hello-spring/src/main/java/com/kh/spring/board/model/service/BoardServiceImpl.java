package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.BoardExt;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public List<BoardExt> selectBoardList() {
		return boardDao.selectBoardList();
	}

	@Override
	public List<BoardExt> selectBoardList(Map<String, Object> param) {
		return boardDao.selectBoardList(param);
	}
}
