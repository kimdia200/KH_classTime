package common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MvcUtils {

	
	/**
	 * 단방향 암호화 알고리즘 (sha256 이상을 사용하는것을 추천함)
	 *  - md5
	 *  - sha1 (160byte)
	 *  - sha256 (256byte)
	 *  - sha512 (512byte)
	 *  
	 * 1. MessageDigest : 단방향 암호화 처리를 함(return = 읽어낼수 없은 이진데이터,깨져있음)
	 * 
	 * 2. Base64 인코딩 처리 : 암호화된 byte[](위에서 처리된 이진데이터)를 64개의 문자로 변환하여 리턴
	 */
	public static String getSha512(String password) {
		String encryptedPassword = null;
		
		//1. 암호화
		
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = null;
		try {
			bytes = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//MessageDigest에 byte배열을 넣어줌
		md.update(bytes);
		
		//실질적인 암호화 작업을 하는 구문
		byte[] encryptedBytes = md.digest();
		System.out.println("인코딩 처리전 : "+ encryptedBytes.toString());
		//2. 문자 인코딩 처리
		encryptedPassword = Base64.getEncoder().encodeToString(encryptedBytes);
		System.out.println("인코딩 처리후 : "+ encryptedPassword);
		return encryptedPassword;
	}

	/*
	 * B. Pagebar Section : html
	 * 		1. totalContents 총 컨텐츠 수
	 * 		2. totalPage 전체페이지수
	 * 		3. pageBarSize 페이지바에 표시할 페이지 갯수 (ex.10개 페이지인데 5개 까지 표시해서 1,2,3,4,5 ...라고 )
	 * 		4. pageNo 증감변수
	 * 		5. pageStart ~ pageEnd : pageNo의 범위
	 */
	public static String getPageBar(int cPage, int numPerPage, int totalContents, String url) {
		StringBuilder pageBar = new StringBuilder();
		
		int pageBarSize = 5;//한번에 5개 페이지만 표시하겠다.
		int totalPage = (int)Math.ceil((double)totalContents/numPerPage);
		//cPage속성 추가전 키워드 작업
		//cPage이외의 다른 사용자입력값(Get방식)이 있는 경우 대비
		url = url.indexOf("?") > -1 ? url + "&" : url + "?";
		
		/**
		 * 1 2 3 4 5
		 * 6 7 8 9 10
		 * 11 12 13 14 15 이렇게 5개씩 표현해줄거임
		 */
		int pageStart = (cPage-1)/pageBarSize*pageBarSize +1;
		int pageEnd = pageStart+pageBarSize-1;
		
		//증감변수는 pageStart부터 시작
		int pageNo = pageStart;
		
		//1. 이전
		if(pageNo != 1 ) {
//			pageBar.append("<a href='"+url+"?cPage="+(pageNo-1)+"'/></a>");
			pageBar.append("<a href='"+url+"cPage="+(pageNo-1)+"'/>prev</a>\n");
		}
		//2. pageNo
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			//현재페이지는 링크를 만들 필요가 없으니까~
			if(pageNo == cPage) {
				pageBar.append("<span class='cPage'>"+pageNo+"</span>");
			}else {
				pageBar.append("<a href='"+url+"cPage="+pageNo+"'/>"+pageNo+"</a>\n");
			}
			pageNo++;
		}
		
		//3. 다음
		if(pageNo>totalPage) {
			//아무것도 안함
		}
		else {
			pageBar.append("<a href='"+url+"cPage="+pageNo+"'/>next</a>\n");
		}
		return pageBar.toString();
	}
}
