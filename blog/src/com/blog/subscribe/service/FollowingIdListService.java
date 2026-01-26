package com.blog.subscribe.service;

import com.blog.main.service.Service;
import com.blog.subscribe.dao.SubscribesDAO;

public class FollowingIdListService implements Service {

	@Override
	public Object service(Object obj) throws Exception {
		String loginId = (String) obj; // 넘어온 obj를 다시 String 타입으로 변환
		SubscribesDAO dao = new SubscribesDAO();
		return dao.followingList(loginId);
	}
}