package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.BoardComment;

public interface CommentDao {
	//게시글의 댓글 전체조회
	public List<BoardComment> selectAllCommentsList(int board_idx);
	//게시글의 댓글 하나조회
	public BoardComment selectComment(int comment_idx);
	//댓글 추가
	public void insertComment(BoardComment comment);
	//댓글 삭제
	public void deleteComment(int comment_idx);
	//댓글 수정
	public void updateComment(BoardComment comment);
	
	
}
