package com.blog.comment.service;

import com.blog.comment.dao.CommentDAO;
import com.blog.comment.vo.Comment;
import com.blog.main.service.Service;

public class CommentUpdateService implements Service {

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		CommentDAO dao = new CommentDAO();
		return dao.update((Comment)obj);
	}
}
