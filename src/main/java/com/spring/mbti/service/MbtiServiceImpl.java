package com.spring.mbti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mbti.dao.MbtiDao;
import com.spring.mbti.vo.MbtiVo;

@Service
public class MbtiServiceImpl implements MbtiService{
	
	@Autowired
	MbtiDao mbtiDao;

	public List<MbtiVo> selectQuestion(String type) {
		return mbtiDao.selectQuestion(type);
	}


}
