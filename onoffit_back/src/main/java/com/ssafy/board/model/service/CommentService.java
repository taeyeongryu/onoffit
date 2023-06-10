package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.BoardComment;

public interface CommentService {
	//게시글에 맞는 댓글 다 불러오는 것
	List<BoardComment> selectAllComment(int board_idx);
	//댓글 등록 하는 것
	void registComment(BoardComment boardcomment);
	//댓글 수정 하는 것
	void updateComment(BoardComment boardcomment);
	//댓글 삭제 하는 것
	void deleteComment(int comment_idx);
}
