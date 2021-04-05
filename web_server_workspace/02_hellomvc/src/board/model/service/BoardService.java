package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
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

	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = boardDao.insertBoard(conn,b);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
