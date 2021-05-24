package com.kh.spring.memo.model.service;

import java.util.List;

import com.kh.spring.memo.model.vo.Memo;

public interface MemoService {

	int insertMemo(String memo);

	List<Memo> selectMemoList();

	int deleteMemo(String no);

}
