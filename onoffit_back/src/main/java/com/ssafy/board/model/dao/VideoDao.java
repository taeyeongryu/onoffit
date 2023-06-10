package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Video;

public interface VideoDao {
	// 전체 비디오 조회
	public List<Video> selectAllVideos();

	// 비디오 하나 조회
	public Video selectVideo(String video_id);

	// 비디오 삽입
	public void insertVideo(Video video);

	// 비디오 제거
	public void deleteVideo(String video_id);

	// 비디오 업데이트
	public void updateVideo(Video video);
}
