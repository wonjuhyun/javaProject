package com.blog.board.service;

import java.util.List;

import com.blog.board.DAO.BoardDAO;
import com.blog.board.controller.OtherBoardController;
import com.blog.board.vo.BoardVO;
import com.blog.main.service.Service;

public class OtherBoardListService implements Service {
	public Object service(Object obj) throws Exception {
		// DAO의 checkId 호출 (obj는 String targetId)
		BoardDAO dao = new BoardDAO();
		return new BoardDAO().list(obj);
	}
	

}
