package com.blog.comment.service;

import com.blog.comment.dao.CommentDAO;
import com.blog.comment.vo.Comment;
import com.blog.main.service.Service;

public class CommentWriteService implements Service {
	
	// list - List<BoardVO>, view - BoardVO, insert, update, delete - Integer
	@Override
	public Integer service(Object obj) throws Exception {
		CommentDAO dao = new CommentDAO();
		return dao.write((Comment) obj);
	}
	
}
