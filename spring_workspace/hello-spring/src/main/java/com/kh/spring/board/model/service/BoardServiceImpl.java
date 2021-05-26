package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.BoardExt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
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

	@Override
	public int selectBoardTotalContents() {
		return boardDao.selectBoardTotalContents();
	}

	/**
	 * rollbackFor - 트랜잭션 rollback처리하기위한 예외 등록. 
	 * Exception - > 모든 예외 처리
	 * 이설정이 빠지면 기본적으로 RuntimeException만 rollback한다.
	 * 메서드 레벨에 써도 되지만 클래스 레벨에 써주면 모든 메서드에 등록된다.
	 */
//	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertBoard(BoardExt board) {
		int result = 0;
		
		//1. board등록
		log.debug("Before board = {}",board);
		result = boardDao.insertBoard(board);
		log.debug("After board = {}",board);
		
		//2. attachment 등록
		if(board.getAttachList().size()>0) {
			for(Attachment attach : board.getAttachList()) {
				attach.setBoardNo(board.getNo());
				result = insertAttachment(attach);
			}
		}
		return result;
	}

	@Override
	public int insertAttachment(Attachment attach) {
		return boardDao.insertAttachment(attach);
	}

	@Override
	public BoardExt selectBoardOne(int no) {
		BoardExt board = boardDao.selectBoardOne(no);
		board.setAttachList(selectAttachList(no));
		return board;
	}

	@Override
	public List<Attachment> selectAttachList(int no) {
		return boardDao.selectAttachList(no);
	}

	@Override
	public BoardExt selectBoardOneCollection(int no) {
		return boardDao.selectBoardOneCollection(no);
	}
}
