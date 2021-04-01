package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

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
		return result;
	}
	
	//회원 탈퇴
	public int deleteMember(String memberId) {
		Connection conn = getConnection();
		int result = memberDao.deleteMember(conn,memberId);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	//회원정보 수정
	public int updateMember(Member member) {
		Connection conn = getConnection();
		int result = memberDao.updateMember(conn,member);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	//전체회원 조회
	public List<Member> selectList() {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectList(conn);
		close(conn);
		return list;
	}
	public List<Member> selectList(int start, int end) {
		Connection conn = getConnection();
		List<Member> list = memberDao.selectList(conn,start,end);
		close(conn);
		return list;
	}
	

	//관리자페이지에서의 조회
	public List<Member> searchMember(Map<String, String> param) {
		Connection conn = getConnection();
		List<Member> list = memberDao.searchMember(conn,param);
		close(conn);
		return list;
	}
	public List<Member> searchMember(Map<String, String> param, int start, int end) {
		Connection conn = getConnection();
		List<Member> list = memberDao.searchMember(conn,param,start, end);
		close(conn);
		return list;
	}

	public int selectMemberCount() {
		Connection conn = getConnection();
		int totalContents = memberDao.selectMemberCount(conn);
		close(conn);
		return totalContents;
	}

	public int selectFinderCount(Map<String, String> param) {
		Connection conn = getConnection();
		int totalContents = memberDao.selectFinderCount(conn,param);
		close(conn);
		return totalContents;
	}

	
}
