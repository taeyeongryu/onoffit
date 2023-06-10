package com.ssafy.board.model.dto;

public class SearchCondition {
	// 변수 생성
	private String key = "none";
	private String word;

	// 기본 생성자
	public SearchCondition() {
	}
	

	public SearchCondition(String key, String word) {
		super();
		this.key = key;
		this.word = word;
	}


	// getter & setter
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
