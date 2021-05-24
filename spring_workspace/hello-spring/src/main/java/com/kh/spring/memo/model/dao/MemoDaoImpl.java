package com.kh.spring.memo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.memo.model.vo.Memo;

@Repository
public class MemoDaoImpl implements MemoDao{

	//root-context.xml에 정의되어 있어서 의존주입 가능!
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int insertMemo(String memo) {
		return session.insert("memo.insertMemo",memo);
	}

	@Override
	public List<Memo> selectMemoList() {
		return session.selectList("memo.selectMemoList");
	}

	@Override
	public int deleteMemo(String no) {
		return session.delete("memo.deleteMemo",no);
	}

}
