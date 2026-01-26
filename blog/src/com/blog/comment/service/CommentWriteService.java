package com.blog.comment.service;

import com.blog.comment.dao.CommentDAO;
import com.blog.comment.vo.Comment;
import com.blog.main.service.Service;

public class CommentWriteService implements Service {
	
	// list - List<BoardVO>, view - BoardVO, insert, update, delete - Integer
	@Override
	public Integer service(Object obj) throws Exception {
		Comment vo = (Comment) obj;
		CommentDAO dao = new CommentDAO();
		// 1. 중복 댓글 확인
				// 게시글 번호와 작성자 아이디를 넘겨서 확인
		if (dao.checkDoubleComment(vo.getPostNo(), vo.getWriterId())) {
					// 이미 댓글이 존재하면 강제로 예외를 발생시킴 -> Controller의 catch로 이동
			throw new Exception("이미 이 게시물에 댓글을 작성하셨습니다.");
		}
		
		return dao.write((Comment) obj);
	}
	
}
