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
import com.ssafy.board.model.dto.OffGroup;
import com.ssafy.board.model.dto.OnGroup;
import com.ssafy.board.model.dto.VideoList;
import com.ssafy.board.model.service.OffGroupService;
import com.ssafy.board.model.service.OnGroupService;

@RestController
@RequestMapping("/api")
public class GroupRestController {
	@Autowired
	OnGroupService onGroupService;
	@Autowired
	OffGroupService offGroupService;

	private static final String SUCCESS = "succes";
	private static final String FAIL = "fail";

	@GetMapping("group/myoffgroup")
	public ResponseEntity<?> getMyOffGroup(@RequestParam("user_id") String user_id) {
		List<OffGroup> list = offGroupService.getMyGroup(user_id);
		List<Map<String, Object>> result = new ArrayList<>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				OffGroup group = list.get(i);

				Map<String, Object> map = new HashMap<String, Object>();

				// 실제로는 로그인한 유저의 아이디가 들어가야 한다.
				boolean isjoin = onGroupService.isJoin(user_id, group.getGroup_id());

				map.put("group_id", group.getGroup_id());
				map.put("group_title", group.getGroup_title());
				map.put("group_message", group.getGroup_message());
				map.put("group_description", group.getGroup_description());
				map.put("group_cnt", group.getGroup_cnt());
				map.put("group_leader_user_id", group.getGroup_leader_user_id());
				map.put("group_image", group.getGroup_image());
				map.put("group_orgimage", group.getGroup_orgimage());
				map.put("joined", isjoin);
				result.add(map);
			}
			return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("group/myongroup")
	public ResponseEntity<?> getMyOnGroup(@RequestParam("user_id") String user_id) {
		System.out.println(user_id);
		List<OnGroup> list = onGroupService.getMyGroup(user_id);
		System.out.println(list);
		List<Map<String, Object>> result = new ArrayList<>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				OnGroup group = list.get(i);

				Map<String, Object> map = new HashMap<String, Object>();

				// 실제로는 로그인한 유저의 아이디가 들어가야 한다.
				boolean isjoin = onGroupService.isJoin(user_id, group.getGroup_id());

				map.put("group_id", group.getGroup_id());
				map.put("group_title", group.getGroup_title());
				map.put("group_message", group.getGroup_message());
				map.put("group_description", group.getGroup_description());
				map.put("group_cnt", group.getGroup_cnt());
				map.put("group_leader_user_id", group.getGroup_leader_user_id());
				map.put("group_image", group.getGroup_image());
				map.put("group_orgimage", group.getGroup_orgimage());
				map.put("joined", isjoin);
				result.add(map);
			}
			return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	// 목적 : user_id 값을 매개변수로 넘기면 그 에 맞는 ongroup 반환, user가 참가했는지 check해서 반환해준다.
	@GetMapping("/group/on")
	public ResponseEntity<List<Map<String, Object>>> getOnGroup(@RequestParam("user_id") String user_id) {
		List<OnGroup> list = onGroupService.selectOnGroupList();
		List<Map<String, Object>> result = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			OnGroup group = list.get(i);

			Map<String, Object> map = new HashMap<String, Object>();

			// 실제로는 로그인한 유저의 아이디가 들어가야 한다.
			boolean isjoin = onGroupService.isJoin(user_id, group.getGroup_id());

			System.out.println(user_id);
			System.out.println(group.getGroup_id());
			System.out.println(isjoin);
			System.out.println(group.getGroup_leader_user_id());

			map.put("group_id", group.getGroup_id());
			map.put("group_title", group.getGroup_title());
			map.put("group_message", group.getGroup_message());
			map.put("group_description", group.getGroup_description());
			map.put("group_cnt", group.getGroup_cnt());
			map.put("group_leader_user_id", group.getGroup_leader_user_id());
			map.put("group_image", group.getGroup_image());
			map.put("group_orgimage", group.getGroup_orgimage());
			map.put("joined", isjoin);
			result.add(map);
		}
		return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
	}

	@PostMapping("/group/on")
	public ResponseEntity<OnGroup> registOnGroup(@RequestBody OnGroup onGroup) {
		System.out.println(onGroup);
		OnGroup ongroup = onGroupService.registOnGroup(onGroup);
		return new ResponseEntity<OnGroup>(ongroup,HttpStatus.OK);
	}

	// 매개변수로 넘겨주는 onGroup에 모든 필드가 전부 채워져있어야 한다.
	@PutMapping("/group/on")
	public ResponseEntity<String> updateOnGroup(@RequestBody OnGroup onGroup) {
		onGroupService.updateOnGroup(onGroup);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}


	// 프론트에서 참여버튼 누르면 이 요청이 실행된다.
	@PutMapping("/group/on/putjoin")
	public ResponseEntity<String> onputJoin(@RequestBody Map<String, Object> requestData) {
		ObjectMapper objectMapper = new ObjectMapper();
		OnGroup onGroup = objectMapper.convertValue(requestData.get("onGroup"), OnGroup.class);
		String user_id = (String) requestData.get("user_id");
		System.out.println(user_id);
		System.out.println(onGroup);
		onGroupService.putJoin(user_id, onGroup.getGroup_id());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// group_id만 제대로 넣어도 작동 한다.
	@DeleteMapping("/group/on")
	public ResponseEntity<String> deleteOnGroup(@RequestBody String group_id) {
		System.out.println(group_id);
		OnGroup onGroup = new OnGroup();
		onGroup.setGroup_id(Integer.parseInt(group_id));
		onGroupService.deleteOnGroup(onGroup.getGroup_id());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@GetMapping("/group/off")
	public ResponseEntity<List<HashMap<String, Object>>> getOffGroup(@RequestParam("user_id") String user_id) {
		System.out.println(123);
		List<OffGroup> list = offGroupService.selectOffGroupList();
		List<HashMap<String, Object>> result = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			OffGroup group = list.get(i);

			HashMap<String, Object> map = new HashMap<String, Object>();

			// 실제로는 로그인한 유저의 아이디가 들어가야 한다.
			boolean isjoin = offGroupService.isJoin(user_id, group.getGroup_id());
			map.put("group_id", group.getGroup_id());
			map.put("group_title", group.getGroup_title());
			map.put("group_message", group.getGroup_message());
			map.put("group_description", group.getGroup_description());
			map.put("group_cnt", group.getGroup_cnt());
			map.put("group_leader_user_id", group.getGroup_leader_user_id());
			map.put("group_address", group.getGroup_address());
			map.put("group_image", group.getGroup_image());
			map.put("group_orgimage", group.getGroup_orgimage());
			map.put("joined", isjoin);
			result.add(map);
		}
		return new ResponseEntity<List<HashMap<String, Object>>>(result, HttpStatus.OK);
	}

	@PostMapping("/group/off")
	public ResponseEntity<String> registOffGroup(@RequestBody OffGroup offGroup) {
		System.out.println(offGroup);
		offGroupService.registOffGroup(offGroup);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@PutMapping("/group/off")
	public ResponseEntity<String> updateOffGroup(@RequestBody OffGroup offGroup) {
		System.out.println(offGroup);
		offGroupService.updateOffGroup(offGroup);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 프론트에서 참여버튼 누르면 이 요청이 실행된다.
	@PutMapping("/group/off/putjoin")
	public ResponseEntity<String> offputJoin(@RequestBody Map<String, Object> requestData) {
		// 이 메서드는 참여 상태를 파악해 스위치를 알아서 껐다가 키는 메서드
		// 더미데이터 로그인한 유저가 들어가야함
		ObjectMapper objectMapper = new ObjectMapper();
		OffGroup offGroup = objectMapper.convertValue(requestData.get("offGroup"), OffGroup.class);
		String user_id = (String) requestData.get("user_id");
		System.out.println(user_id);
		System.out.println(offGroup);
		offGroupService.putJoin(user_id, offGroup.getGroup_id());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	@DeleteMapping("/group/off")
	public ResponseEntity<String> deleteOffGroup(@RequestBody String group_id) {
		System.out.println(group_id);
		OffGroup offGroup = new OffGroup();
		offGroup.setGroup_id(Integer.parseInt(group_id));
		offGroupService.deleteOffGroup(offGroup.getGroup_id());
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

}
