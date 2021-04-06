package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
import board.model.vo.Attachment;
import board.model.vo.Board;

public class BoardService {
	private BoardDao boardDao = new BoardDao();

	//페이지에 나열할 리스트
	public List<Board> selectPageList(int start, int end) {
		Connection conn = getConnection();
		List<Board> list = boardDao.selectPageList(conn, start, end);
		close(conn);
		return list;
	}

	public int selectBoardCount() {
		Connection conn = getConnection();
		int result = boardDao.selectBoardCount(conn);
		close(conn);
		return result;
	}

	/**
	 * 첨부파일이 있는 경우, attach객체를 attachment테이블에 등록한다.
	 *  - board등록, attachment등록은 하나의 트랜잭션으로 처리되어야 한다.
	 *  - 하나의 Connection에 의해 처리 되어야한다.
	 *  (게시글은 등록이 안됬는데 첨부파일만 등록되는 경우가 없게 해야되기 때문)
	 * @param b
	 * @return
	 */
	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			boardDao.insertBoard(conn,b);
			int boardNo = boardDao.selectLastBoardNo(conn);
			System.out.println("boardNo@service = "+ boardNo);
			
			if(b.getAttach() != null) {
				b.getAttach().setBoardNo(boardNo);
				boardDao.insertAttachment(conn, b.getAttach());
			}
			commit(conn);
			result = 1;
		} catch (Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		return result;
	}

	public Board selectOneBoard(int no) {
		Connection conn = getConnection();
		Board board = boardDao.selectOneBoard(conn, no);
		close(conn);
		
		return board;
	}

	public Attachment selectOneAttach(int no) {
		Connection conn = getConnection();
		Attachment attach = boardDao.selectOneAttach(conn, no);
		close(conn);
		
		return attach;
	}
}
