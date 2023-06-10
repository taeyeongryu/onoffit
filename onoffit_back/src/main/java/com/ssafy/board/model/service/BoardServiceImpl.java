package com.ssafy.board.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dao.BoardLikeDao;
import com.ssafy.board.model.dto.Board;
import com.ssafy.board.model.dto.BoardLike;
import com.ssafy.board.model.dto.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDao boardDao;

	@Autowired
	BoardLikeDao boardLikeDao;

	@Override
	public List<Board> selectAllBoard() {
		return boardDao.selectAllBoard();
	}

	@Override
	public void updateBoard(Board board) {
		boardDao.updateBoard(board);

	}

	@Override
	public void deleteBoard(int board_idx) {
		boardDao.deleteBoard(board_idx);

	}

	@Override
	public void registBoard(Board board) {
		boardDao.insertBoard(board);

	}

	@Override
	public List<Board> searchBoard(SearchCondition condition) {
		return boardDao.search(condition);
	}

	@Override
	public boolean isLike(String user_id, int board_idx) {
		List<BoardLike> likelist = boardLikeDao.selectByWriterId(user_id);
		for (int i = 0; i < likelist.size(); i++) {
			BoardLike like = likelist.get(i);
			if (like.getBoard_idx() == board_idx) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void putLike(String user_id, int board_idx) {
		// 좋아요 인덱스
		int like_idx = 0;
		// 좋아요가 눌러져있는지확인하고
		// 좋아요가 눌러져 있으면
		if (isLike(user_id, board_idx)) {
			List<BoardLike> likelist = boardLikeDao.selectByWriterId(user_id);
			for (int i = 0; i < likelist.size(); i++) {
				BoardLike like = likelist.get(i);
				if (like.getBoard_idx() == board_idx) {
					like_idx = like.getLike_idx();
					break;
				}
			}
			// 좋아요 삭제해 준다.
			boardLikeDao.deleteLike(new BoardLike(like_idx, user_id, board_idx));
			// 보드에 좋아요 개수 업데이트 해줘야 한다.
			Board board = boardDao.selectOneBoard(board_idx);
			board.setBoard_like(board.getBoard_like() - 1);
			boardDao.updateBoard(board);
		}
		// 좋아요가 눌러져 있지 않으면
		else {
			// 좋아요 테이블 만들어 준다.
			BoardLike boardLike = new BoardLike();
			boardLike.setBoard_idx(board_idx);
			boardLike.setWriter_id(user_id);
			boardLikeDao.insertLike(boardLike);
			// 보드에 좋아요 개수 업데이트 해줘야 한다.
			Board board = boardDao.selectOneBoard(board_idx);
			board.setBoard_like(board.getBoard_like() + 1);
			boardDao.updateBoard(board);
		}
	}

	@Override
	public int countLike(Board board) {
		return boardLikeDao.selectByBoardIdx(board.getBoard_idx()).size();
	}

	@Override
	public List<Board> likedboards(String id) {
		List<BoardLike> tmp=boardLikeDao.selectByWriterId(id);
		List<Board> result = new ArrayList<Board>();
		for(BoardLike a : tmp) {
			result.add(boardDao.selectOneBoard(a.getBoard_idx()));
		}
		return result;
	}

	@Override
	public Board selectOne(int board_idx) {
		boardDao.updateViewCnt(board_idx);
		return boardDao.selectOneBoard(board_idx);
	}

}
