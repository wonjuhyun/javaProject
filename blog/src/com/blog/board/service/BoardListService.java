package com.blog.board.service;

import java.util.List;

import com.blog.board.DAO.BoardDAO;
import com.blog.board.vo.BoardVO;
import com.blog.main.service.Service;
import com.blog.member.vo.Login;

public class BoardListService implements Service {
	public List<BoardVO> service(Object obj) throws Exception {
		BoardDAO dao = new BoardDAO();
		return dao.list(Login.getId());
	}
}
