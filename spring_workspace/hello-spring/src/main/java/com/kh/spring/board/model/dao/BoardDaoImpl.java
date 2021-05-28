package com.kh.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
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

	@Override
	public int selectBoardTotalContents() {
		return session.selectOne("board.selectBoardTotalContents");
	}

	@Override
	public int insertBoard(BoardExt board) {
		return session.insert("board.insertBoard",board);
	}

	@Override
	public int insertAttachment(Attachment attach) {
		return session.insert("board.insertAttachment",attach);
	}

	@Override
	public BoardExt selectBoardOne(int no) {
		return session.selectOne("board.selectBoardOne",no);
	}

	@Override
	public List<Attachment> selectAttachList(int no) {
		return session.selectList("board.selectAttachList",no);
	}

	@Override
	public BoardExt selectBoardOneCollection(int no) {
		//컬렉션을 사용해서 받을때는 List라고 생각할수있지만 One으로 작성해야함
		return session.selectOne("board.selectBoardOneCollection",no);
	}

	@Override
	public Attachment selectOneAttachment(int no) {
		return session.selectOne("board.selectOneAttachment",no);
	}

	@Override
	public List<BoardExt> selectBoardList(String search) {
		return session.selectList("board.autocomplete",search);
	}

	@Override
	public List<Board> searchTitle(String searchTitle) {
		return session.selectList("board.searchTitle",searchTitle);
	}
	
}
