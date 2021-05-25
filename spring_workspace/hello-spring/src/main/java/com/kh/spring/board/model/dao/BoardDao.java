package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.BoardExt;

public interface BoardDao {

	List<BoardExt> selectBoardList();

	List<BoardExt> selectBoardList(Map<String, Object> param);

}
