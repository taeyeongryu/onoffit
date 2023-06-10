package com.ssafy.board.model.service;

import java.util.List;

import com.ssafy.board.model.dto.OnGroup;

public interface OnGroupService {
	// 그룹 등록
	OnGroup registOnGroup(OnGroup onGroup);

	// 그룹 수정
	void updateOnGroup(OnGroup onGroup);

	// 그룹 삭제
	void deleteOnGroup(int group_id);

	// 그룹 셀렉트
	List<OnGroup> selectOnGroupList();

	// 유저 아이디랑, 그룹아이디를 넘겨받아서 이 유저가 이 그룹에 참여를 눌렀
	// 는지 boolean으로 반환하는 메서드
	// 반환값을 boolean으로 해야되는지는 확실하지 않다.
	boolean isJoin(String user_id, int group_id);

	// 조인 버튼을 누른 메서드
	void putJoin(String user_id, int group_id);
	//유저 아이디로 참가한 그룹 가져오기
	List<OnGroup> getMyGroup(String user_id);
	// 그룹에 참가버튼을 누른 사람이 몇명인지 반환하는 메서드
	int countJoin(OnGroup onGroup);
}
