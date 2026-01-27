package com.blog.like.service;

import com.blog.like.dao.LikeDAO;
import com.blog.like.vo.LikeVO;
import com.blog.main.service.Service;

public class LikeDeleteService implements Service {

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		LikeDAO dao = new LikeDAO();
		return dao.delete((LikeVO) obj);
	}
	
}
