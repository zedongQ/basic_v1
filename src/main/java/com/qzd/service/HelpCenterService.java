package org.ieforex.service;

import java.util.List;
import java.util.Map;

import org.ieforex.entity.Dealer;
import org.ieforex.entity.HelpCenter;

public interface HelpCenterService {
	 List<Map<String, Object>> helpMenu();
	 HelpCenter helpRoom(Long classId);
	 HelpCenter firstHelp();
	 List<Dealer> dealerList();
}
