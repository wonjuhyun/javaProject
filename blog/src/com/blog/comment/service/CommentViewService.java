package com.blog.comment.service;

import com.blog.comment.dao.CommentDAO;
import com.blog.comment.vo.Comment;
import com.blog.main.service.Service;

// Main -> BoardController -> BoardListService -> BoardDAO
public class CommentViewService implements Service {
	
	public Comment service(Object obj) throws Exception {
		CommentDAO dao = new CommentDAO();
		// 번호에 맞는 데이터를 가져와서 리턴해 준다
		return dao.view((Comment) obj);
	}

}
