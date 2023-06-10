package com.ssafy.board.model.dto;

public class Video {
	// 변수 생성
	private String video_id;
	private String video_title;
	private String video_url;

	// 기본 생성자
	public Video() {
	}

	// 매개변수 포함된 생성자
	public Video(String video_id, String video_title, String video_url) {
		this.video_id = video_id;
		this.video_title = video_title;
		this.video_url = video_url;
	}

	// getter & setter
	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getVideo_title() {
		return video_title;
	}

	public void setVideo_title(String video_title) {
		this.video_title = video_title;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	// toString()
	@Override
	public String toString() {
		return "Video [video_id=" + video_id + ", video_title=" + video_title + ", video_url=" + video_url + "]";
	}

}
