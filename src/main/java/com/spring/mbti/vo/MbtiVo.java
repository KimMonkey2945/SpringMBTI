package com.spring.mbti.vo;

public class MbtiVo {
	
	private String score;
	private String result;
	private String type;
	private String questionType;
	private String question;
	private String resultImg;
	
	public String getScore() {
		return score;
	}
	public String setScore(String score) {
		return this.score = score;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}	
	public String getResultImg() {
		return resultImg;
	}
	public void setResultImg(String resultImg) {
		this.resultImg = resultImg;
	}
	
	@Override
	public String toString() {
		return "MbtiVo [score=" + score + ", result=" + result + ", type=" + type + ", questionType=" + questionType
				+ ", question=" + question + "]";
	}
	
	

}
