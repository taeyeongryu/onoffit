package com.ssafy.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.SearchCondition;
import com.ssafy.board.model.service.BoardService;

@RestController
@RequestMapping("/api")
public class BoardRestController {
	@Autowired
	BoardService boardService;

	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	// 게시판 글 불러오는 요청, mode와 keyword로 전부 불러올지
	// 조건에 따라 검색할지 나뉜다.
	@GetMapping("/board")
	public ResponseEntity<List<HashMap<String, Object>>> list(
			@RequestParam(value = "key", defaultValue = "none") String key,
			@RequestParam(value = "word", defaultValue = "") String word, @RequestParam(value = "user_id", defaultValue = "") String user_id) {

		System.out.println(key);
		System.out.println(word);
		// 모든 검색 이요청으로 끝낸다.
		// 전체글 긁어오는 것은 key가 none 이면 된다.
		SearchCondition searchCondition = new SearchCondition(key, word);
		List<Board> list = boardService.searchBoard(searchCondition);
		// 이거 확실하지 않은데 더 나은 방법 모르겠엄
		// 프론트로 보낼때 이 게시글이 좋아요가 표시되어있는지 같이 넣어서 보내야 돼
		// 만약 로그인 되어있다면
		List<HashMap<String, Object>> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Board board = list.get(i);
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 더미데이터
			// 실제로는 로그인한 유저의 아이디가 들어가야 한다.
			boolean islike = boardService.isLike(user_id, board.getBoard_idx());
//            boolean islike = false;
			map.put("board_title", board.getBoard_title());
			map.put("board_idx", board.getBoard_idx());
			map.put("board_content", board.getBoard_content());
			map.put("writer_id", board.getWriter_id());
			map.put("board_created", board.getBoard_created());
			map.put("board_updated", board.getBoard_updated());
			map.put("board_view", board.getBoard_view());
			map.put("board_like", board.getBoard_like());
			map.put("liked", islike);
			result.add(map);
		}
		return new ResponseEntity<List<HashMap<String, Object>>>(result, HttpStatus.OK);
	}

	@PostMapping("/board")
	public ResponseEntity<String> write(Board board) {
		System.out.println(board.toString());
		boardService.registBoard(board);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}

	@PutMapping("/board")
	public ResponseEntity<String> update(Board board) {
		// 게시판을 업데이트 한다.
		System.out.println(board.toString());
		boardService.updateBoard(board);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 프론트에서 좋아요버튼 누르면 이 요청이 실행된다.
	// 게시판의 좋아요 관련된것을 수정해 줘야 해 ok?
	@PutMapping("/board/putlike")
	public ResponseEntity<String> putLike(@RequestBody Map<String, Object> requestData) {
		System.out.println(requestData.get("board"));
		ObjectMapper objectMapper = new ObjectMapper();
		Board board = objectMapper.convertValue(requestData.get("board"), Board.class);
		String user_id = (String) requestData.get("user_id");
		System.out.println("-----------------------");

		// 이하 로직은 그대로 유지

		System.out.println(board.toString());
		System.out.println(user_id);
		boardService.putLike(user_id, board.getBoard_idx());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@DeleteMapping("/board")
	public ResponseEntity<String> delete(Board board) {
		System.out.println("삭제");
		System.out.println(board.toString());
		boardService.deleteBoard(board.getBoard_idx());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@GetMapping("/getliked")
	public ResponseEntity<List<Board>> getLikedlist(@RequestParam("id") String id) {
		List<Board> result = boardService.likedboards(id);
		return new ResponseEntity<List<Board>>(result, HttpStatus.OK);
	}

	@GetMapping("/board/one")
	public ResponseEntity<HashMap<String, Object>> selectOne(@RequestParam String user_id,
			@RequestParam int board_idx) {

		Board board = boardService.selectOne(board_idx);
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 더미데이터
		// 실제로는 로그인한 유저의 아이디가 들어가야 한다.
		boolean islike = boardService.isLike(user_id, board_idx);
//        boolean islike = false;
		map.put("board_title", board.getBoard_title());
		map.put("board_idx", board.getBoard_idx());
		map.put("board_content", board.getBoard_content());
		map.put("writer_id", board.getWriter_id());
		map.put("board_created", board.getBoard_created());
		map.put("board_updated", board.getBoard_updated());
		map.put("board_view", board.getBoard_view());
		map.put("board_like", board.getBoard_like());
		map.put("liked", islike);
		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
	}
}