package board.model.vo;

import java.sql.Date;

public class BoardPlus extends Board {
	private int CommentCount;

	public BoardPlus() {
		super();
	}

	public BoardPlus(int no, String title, String writer, String content, Date regDate, int readCount,
			Attachment attach) {
		super(no, title, writer, content, regDate, readCount, attach);
	}

	public BoardPlus(int commentCount) {
		super();
		CommentCount = commentCount;
	}

	public int getCommentCount() {
		return CommentCount;
	}

	public void setCommentCount(int commentCount) {
		CommentCount = commentCount;
	}

	@Override
	public String toString() {
		return "BoardPlus [CommentCount=" + CommentCount + "]";
	}
}
