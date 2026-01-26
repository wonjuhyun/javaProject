package com.blog.comment.service;

import java.util.List;

import com.blog.comment.dao.CommentDAO;
import com.blog.comment.vo.Comment;
import com.blog.main.service.Service;

// Main -> BoardController -> BoardListService -> CommentDAO
public class CommentListService implements Service {
	
	// Service를 실행하면 리턴 타입은 무엇이 나올까요?
	// 일반 게시판 리스트의 리턴 타입 : List<Comment>
	// 일반 게시판 글 보기의 리턴 타입 : Comment
	// 글 등록, 글 수정, 글 삭제 : Integer
	// 모든 타입의 데이터를 리턴하여면 Object로 한다
	public List<Comment> service(Object obj) throws Exception {
		CommentDAO dao = new CommentDAO();
		return dao.list();
	}

}
