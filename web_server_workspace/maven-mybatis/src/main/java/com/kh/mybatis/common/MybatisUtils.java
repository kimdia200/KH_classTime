package com.kh.mybatis.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
	
	/**
	 * mybatis-config.xml 설정파일 기반으로 SqlSession객체를 생성반환
	 * build-path(target/classes)로 배포된 설정파일을 읽어들임.
	 * 
	 * 공장짓는이 - 공장 - sqlsession
	 * @return
	 */
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		//경로는 navigator 에서 보면 루트경로는 target폴더이다
		String resource = "/mybatis-config.xml";
		//1. 공장 FactoryBuilder
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//2. Factory생성 - 이때! 설정파일 필요
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory factory = builder.build(is);
		//3. SqlSession 받기
		//파라미터 false = autoCommit 설정
		session = factory.openSession(false);
		return session;
	}
}
