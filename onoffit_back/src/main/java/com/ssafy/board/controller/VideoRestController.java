package com.ssafy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.Video;
import com.ssafy.board.model.dto.VideoList;
import com.ssafy.board.model.service.VideoService;

@RestController
@RequestMapping("/api")
public class VideoRestController {

	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	@Autowired
	VideoService videoService;

	@GetMapping("/video")
	public ResponseEntity<List<Video>> list(@RequestParam("group_id") int group_id) {
		List<Video> result = videoService.selectVideoByGroupId(group_id);
		return new ResponseEntity<List<Video>>(result, HttpStatus.OK);
	}

	@DeleteMapping("/video")
	public ResponseEntity<String> delete(@RequestBody Video video) {
		videoService.deleteVideo(video.getVideo_id());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@PostMapping("/video")
	public ResponseEntity<String> regist(@RequestBody VideoList videoList) {
		
		System.out.println(videoList);
		videoService.registVideo(videoList, videoList.getGroup_id());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
