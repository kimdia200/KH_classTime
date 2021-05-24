package com.kh.spring.memo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.memo.model.dao.MemoDao;
import com.kh.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemoServiceImpl implements MemoService{

	@Autowired
	private MemoDao memoDao;
	
	@Override
	public int insertMemo(String memo) {
		return memoDao.insertMemo(memo);
	}

	@Override
	public List<Memo> selectMemoList() {
		log.debug("memoDao = {}", memoDao.getClass());
		return memoDao.selectMemoList();
	}

	@Override
	public int deleteMemo(String no) {
		return memoDao.deleteMemo(no);
	}

}
