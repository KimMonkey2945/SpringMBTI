package com.spring.mbti.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mbti.vo.MbtiVo;

@Repository
public class MbtiDaoImpl implements MbtiDao{
	
	@Autowired
	private SqlSession sqlSession;

	public List<MbtiVo> selectQuestion(String type) {
		return sqlSession.selectList("mbti.selectQuestion", type);
	}
	
	

}
