package com.ssafy.board.model.dto;

public class BoardComment {
	// 변수 생성
	private int comment_idx;
	private String comment_content;
	private String writer_id;
	private int board_idx;

	// 기본 생성자
	public BoardComment() {
	}

	// 매개변수 포함된 생성자
	public BoardComment(int comment_idx, String comment_content, String writer_id, int board_idx) {
		this.comment_idx = comment_idx;
		this.comment_content = comment_content;
		this.writer_id = writer_id;
		this.board_idx = board_idx;
	}

	// getter & setter
	public int getComment_idx() {
		return comment_idx;
	}

	public void setComment_idx(int comment_idx) {
		this.comment_idx = comment_idx;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
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
		return "Board_comment [comment_idx=" + comment_idx + ", comment_content=" + comment_content + ", writer_id="
				+ writer_id + ", board_idx=" + board_idx + "]";
	}

}
