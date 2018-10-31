package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.Dealer;
import org.ieforex.entity.LitchiClassroom;

public interface LitchiClassroomService {
	 List<Map<String, Object>> litchiMenu();
	 LitchiClassroom classRoom(Long classId);
	 LitchiClassroom firstClassroom();
	 List<Dealer> dealerList();
}
