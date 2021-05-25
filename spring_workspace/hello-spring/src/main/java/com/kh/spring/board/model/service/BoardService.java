package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.BoardExt;

public interface BoardService {

	List<BoardExt> selectBoardList();

	List<BoardExt> selectBoardList(Map<String, Object> param);

}
