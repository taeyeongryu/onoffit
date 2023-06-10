package com.ssafy.board.model.dto;

public class BoardLike {
	// 변수 생성
	private int like_idx;
	private String writer_id;
	private int board_idx;

	// 기본 생성자
	public BoardLike() {
	}

	// 매개변수 포함된 생성자
	public BoardLike(int like_idx, String writer_id, int board_idx) {
		this.like_idx = like_idx;
		this.writer_id = writer_id;
		this.board_idx = board_idx;
	}

	// getter & setter
	public int getLike_idx() {
		return like_idx;
	}

	public void setLike_idx(int like_idx) {
		this.like_idx = like_idx;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	// toString()
	@Override
	public String toString() {
		return "Board_like [like_idx=" + like_idx + ", writer_id=" + writer_id + ", board_idx=" + board_idx + "]";
	}

}
