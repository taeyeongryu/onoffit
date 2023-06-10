package com.ssafy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.BoardComment;
import com.ssafy.board.model.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentRestController {
	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	@Autowired
	private CommentService commentService;
	
	//댓글 조회
	@GetMapping("/comment/{board_idx}")
	public ResponseEntity<List<BoardComment>> list(@PathVariable int board_idx) {
		List<BoardComment> list = commentService.selectAllComment(board_idx);
		return new ResponseEntity<List<BoardComment>>(list, HttpStatus.OK);
	}
	//댓글 등록
	@PostMapping("/comment")
	public ResponseEntity<String> write(@RequestBody BoardComment comment) {
		commentService.registComment(comment);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	//댓글 수정
	@PutMapping("/comment")
	public ResponseEntity<String> update(@RequestBody BoardComment comment){
		commentService.updateComment(comment);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	//댓글 삭제
	@DeleteMapping("/comment")
	public ResponseEntity<String> delete(@RequestBody BoardComment comment){
		commentService.deleteComment(comment.getComment_idx());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
