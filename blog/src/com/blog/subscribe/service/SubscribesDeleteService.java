package com.blog.subscribe.service;

import com.blog.main.service.Service;

public class SubscribesDeleteService implements Service {

	@Override
	public Object service(Object obj) throws Exception {
		Subscribes vo = (Subscribes) obj;
		SubscribesDAO dao = new SubscribesDAO();
		return dao.delete(vo);
	}
}
