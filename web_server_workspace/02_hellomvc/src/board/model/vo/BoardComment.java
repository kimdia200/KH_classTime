package board.model.vo;

import java.sql.Date;

public class BoardComment {
	
	private int no;
	//댓글 1, 대댓글 2
	private int commentLevel;
	private String writer;
	private String content;
	//참조 게시글
	private int boardNo;
	//대댓글인 경우 참조 댓글, 댓글인 경우 null
	private int commentRef;
	private Date regDate;
	
	//기본생성자
	public BoardComment() {
		super();
	}
	
	//파라미터 생성자
	public BoardComment(int no, int commentLevel, String writer, String content, int boardNo, int commentRef,
			Date regDate) {
		super();
		this.no = no;
		this.commentLevel = commentLevel;
		this.writer = writer;
		this.content = content;
		this.boardNo = boardNo;
		this.commentRef = commentRef;
		this.regDate = regDate;
	}
	
	
	//getter and setter
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCommentLeve() {
		return commentLevel;
	}
	public void setCommentLeve(int commentLevel) {
		this.commentLevel = commentLevel;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getCommentRef() {
		return commentRef;
	}
	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	//toString Override
	@Override
	public String toString() {
		return "BoardComment [no=" + no + ", commentLevel=" + commentLevel + ", writer=" + writer + ", content=" + content
				+ ", boardNo=" + boardNo + ", commentRef=" + commentRef + ", regDate=" + regDate + "]";
	}
}
