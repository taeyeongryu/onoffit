package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.BoardLike;


public interface BoardLikeDao {
	//user가 좋아요한 게시판 글 가져오기 => 마이페이지 , 좋아요 눌렀는지 확인 
	public List<BoardLike> selectByWriterId(String writer_id);
	
	//게시글의 전체 좋아요 수 
	public List<BoardLike> selectByBoardIdx(int board_idx);
	
	//좋아요 등록 
	public void insertLike(BoardLike like);
	//좋아요 삭제 
	public void deleteLike(BoardLike like);
	
	
}
