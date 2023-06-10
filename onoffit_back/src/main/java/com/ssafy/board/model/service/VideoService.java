package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Video;
import com.ssafy.board.model.dto.VideoList;

public interface VideoService {
	//비디오 등록
	void registVideo(VideoList videoList, int group_id);
	//비디오 삭제
	void deleteVideo(String video_id);
	//그냥 비디오 조회
	List<Video> selectVideoList();
	//ongroup에 맞는 비디오 조회
	List<Video> selectVideoByGroupId(int group_id);
	
	Video selectVideoByVideoId(String video_id);
	
}
