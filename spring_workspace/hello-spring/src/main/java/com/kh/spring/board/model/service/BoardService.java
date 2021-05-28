package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.BoardExt;

public interface BoardService {

	List<BoardExt> selectBoardList();

	List<BoardExt> selectBoardList(Map<String, Object> param);

	int selectBoardTotalContents();

	int insertBoard(BoardExt board);

	int insertAttachment(Attachment attach);

	BoardExt selectBoardOne(int no);

	List<Attachment> selectAttachList(int no);

	BoardExt selectBoardOneCollection(int no);

	Attachment selectOneAttachment(int no);

	List<BoardExt> selectBoardList(String search);

	List<Board> searchTitle(String searchTitle);
}
