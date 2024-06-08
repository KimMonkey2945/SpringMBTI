package com.spring.mbti.service;

import java.util.List;

import com.spring.mbti.vo.MbtiVo;

public interface MbtiService {
	
	public List<MbtiVo> selectQuestion(String type);

}
