package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.SearchCondition;

public interface BoardDao {
	//게시판 글 등록
	public void insertBoard(Board board);
	//게시판 글 삭제 
	public void deleteBoard(int board_idx);
	//게시판 글 수정
	public void updateBoard(Board board);
	//게시판 글 상세보기 
	public Board selectOneBoard(int board_idx);
	//게시판 글 전체 조회
	public List<Board> selectAllBoard();
	//게시판 글 검색
	public List<Board> search(SearchCondition condition);
	//게시판 글 조회수 증가
	public void updateViewCnt(int board_idx);
	//게시판 글 좋아요 증가 
	public void updateLikeCnt(int board_idx);
}
