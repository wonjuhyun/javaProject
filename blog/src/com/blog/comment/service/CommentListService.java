package com.blog.comment.service;

import java.util.List;

import com.blog.comment.dao.CommentDAO;
import com.blog.comment.vo.Comment;
import com.blog.main.service.Service;

// Main -> BoardController -> BoardListService -> CommentDAO
public class CommentListService implements Service {
	
	public List<Comment> service(Object obj) throws Exception {
		// Controller에서 보낸 vo (postNo가 들어있음)
		Comment vo = (Comment) obj;
				
		CommentDAO dao = new CommentDAO();
		// DAO의 list 메서드에 게시글 번호를 넘김
		return dao.list(vo.getPostNo());
	}

}
