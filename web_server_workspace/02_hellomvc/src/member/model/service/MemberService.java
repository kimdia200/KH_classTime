package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	
	public static final String MEMBER_ROLE = "U";
	public static final String ADMIN_ROLE = "A";
	
	//회원조회
	public Member selectOne(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.selectOne(conn,memberId);
		close(conn);
		return member;
	}

	//회원가입
	public int insertMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.insertMember(conn,member);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return 1;
	}
}
