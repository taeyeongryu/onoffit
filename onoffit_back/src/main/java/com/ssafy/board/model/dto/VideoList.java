package com.ssafy.board.model.dto;

import java.util.List;

public class VideoList {
	private List<Video> videoList;
	private int group_id;
	public VideoList(List<Video> videoList, int group_id) {
		super();
		this.videoList = videoList;
		this.group_id = group_id;
	}

	public VideoList() {
	}

	public List<Video> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	@Override
	public String toString() {
		return "VideoList [videoList=" + videoList + ", group_id=" + group_id + "]";
	}
	
	
	
}
