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

}
