package com.kh.spring.memo.model.dao;

import java.util.List;

import com.kh.spring.memo.model.vo.Memo;

public interface MemoDao {

	int insertMemo(String memo);

	List<Memo> selectMemoList();

	int deleteMemo(String no);

}
