package com.blog.subscribe.service;

import com.blog.main.service.Service;
import com.blog.subscribe.dao.SubscribesDAO;
import com.blog.subscribe.vo.SubscribesVO;

public class SubscribesInsertService implements Service {

	@Override
	public Object service(Object obj) throws Exception {
		SubscribesVO vo = (SubscribesVO) obj;
		SubscribesDAO dao = new SubscribesDAO();
		return dao.insert(vo);
	}
}