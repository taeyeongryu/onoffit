package com.ssafy.board.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.VideoDao;
import com.ssafy.board.model.dao.VideoMembershipDao;
import com.ssafy.board.model.dto.Video;
import com.ssafy.board.model.dto.VideoList;
import com.ssafy.board.model.dto.VideoMembership;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	VideoDao videoDao;
	@Autowired
	VideoMembershipDao videoMembershipDao;

	@Override
	public void registVideo(VideoList videoList, int group_id) {
		for (int i = 0; i < videoList.getVideoList().size(); i++) {
			Video video = videoList.getVideoList().get(i);
			if (selectVideoByVideoId(video.getVideo_id()) == null) {
				videoDao.insertVideo(video);
			}
			VideoMembership membership = new VideoMembership();
			membership.setGroup_id(group_id);
			membership.setVideo_id(video.getVideo_id());
			videoMembershipDao.insertMembership(membership);
		}
	}
//	@Override
//	public void registVideo(Video video, int group_id) {
//		videoDao.insertVideo(video);
//		VideoMembership membership = new VideoMembership();
//		membership.setGroup_id(group_id);
//		membership.setVideo_id(video.getVideo_id());
//		videoMembershipDao.insertMembership(membership);
//	}

	@Override
	public void deleteVideo(String video_id) {
		videoDao.deleteVideo(video_id);
	}

	@Override
	public List<Video> selectVideoList() {
		return videoDao.selectAllVideos();
	}

	@Override
	public List<Video> selectVideoByGroupId(int group_id) {
		List<VideoMembership> membershipList = videoMembershipDao.selectAllMemberships(group_id);
		List<Video> result = new ArrayList<Video>();
		for (int i = 0; i < membershipList.size(); i++) {
			result.add(videoDao.selectVideo(membershipList.get(i).getVideo_id()));
		}
		return result;
	}

	@Override
	public Video selectVideoByVideoId(String video_id) {
		return videoDao.selectVideo(video_id);
	}

}
