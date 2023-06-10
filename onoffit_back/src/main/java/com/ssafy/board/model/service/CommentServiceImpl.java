package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.CommentDao;
import com.ssafy.board.model.dto.BoardComment;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<BoardComment> selectAllComment(int board_idx) {
		return commentDao.selectAllCommentsList(board_idx);
	}

	@Override
	public void registComment(BoardComment boardcomment) {
		commentDao.insertComment(boardcomment);
	}

	@Override
	public void updateComment(BoardComment boardcomment) {
		commentDao.updateComment(boardcomment);
	}

	@Override
	public void deleteComment(int comment_idx) {
		commentDao.deleteComment(comment_idx);
	}
}
