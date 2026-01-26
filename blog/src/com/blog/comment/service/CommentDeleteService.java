package com.blog.comment.service;

import com.blog.comment.dao.CommentDAO;
import com.blog.comment.vo.Comment;
import com.blog.main.service.Service;

public class CommentDeleteService implements Service {

	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		CommentDAO dao = new CommentDAO();
		return dao.delete((Comment) obj);
	}

	
}
