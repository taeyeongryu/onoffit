package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.BoardLike;
import com.ssafy.board.model.dto.SearchCondition;

public interface BoardService {
	// 게시판 글 전체 긁어오는 것
	List<Board> selectAllBoard();

	// 검색조건으로 게시판 긁어오는 것
	List<Board> searchBoard(SearchCondition condition);

	// 게시판 글 등록하는 것
	void registBoard(Board board);

	// 게시판 글 수정하는 것
	void updateBoard(Board board);

	// 게시판 글 삭제하는 것
	void deleteBoard(int board_idx);

	// 특정유저가 특정 게시글 좋아요 눌렀는지 확인하는 메서드
	boolean isLike(String user_id, int board_idx);

	// 좋아요 버튼 누른것
	// 즉 꺼져있으면 키고 커져있으면 꺼야한다.
	// 여기서 게시글의 총 좋아요 카운터도 조정해줘야 한다.
	void putLike(String user_id, int board_idx);

	// 게시글의 좋아요 버튼이 몇개인지 확인하는 메서드
	int countLike(Board board);

	// 특정인물이 좋아요 누른 게시글 찾기
	List<Board> likedboards(String id);

	//하나 가져오고 뷰카운터 하나 올린다.
	Board selectOne(int board_idx);
}
