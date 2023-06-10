package com.ssafy.board.model.dto;

public class Board {
	// 변수 생성
	private String board_title;
	private int board_idx;
	private String board_content;
	private String writer_id;
	private String board_created;
	private String board_updated;
	private int board_view;
	private int board_like;
	private boolean liked;

	// 기본 생성자
	public Board() {
	}

	// 매개변수 포함된 생성자
	public Board(String board_title, int board_idx, String board_content, String writer_id, String board_created,
			String board_updated, int board_view, int board_like, boolean liked) {
		super();
		this.board_title = board_title;
		this.board_idx = board_idx;
		this.board_content = board_content;
		this.writer_id = writer_id;
		this.board_created = board_created;
		this.board_updated = board_updated;
		this.board_view = board_view;
		this.board_like = board_like;
		this.liked = liked;
	}
	

	// getter & setter
	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public int getBoard_idx() {
		return board_idx;
	}

	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getBoard_created() {
		return board_created;
	}

	public void setBoard_created(String board_created) {
		this.board_created = board_created;
	}

	public String getBoard_updated() {
		return board_updated;
	}

	public void setBoard_updated(String board_updated) {
		this.board_updated = board_updated;
	}

	public int getBoard_view() {
		return board_view;
	}

	public void setBoard_view(int board_view) {
		this.board_view = board_view;
	}

	public int getBoard_like() {
		return board_like;
	}

	public void setBoard_like(int board_like) {
		this.board_like = board_like;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	@Override
	public String toString() {
		return "Board [board_title=" + board_title + ", board_idx=" + board_idx + ", board_content=" + board_content
				+ ", writer_id=" + writer_id + ", board_created=" + board_created + ", board_updated=" + board_updated
				+ ", board_view=" + board_view + ", board_like=" + board_like + ", liked=" + liked + "]";
	}

}
