package com.blog.board.service;

import java.util.List;

import com.blog.board.DAO.BoardDAO;
import com.blog.board.vo.BoardVO;

public class BoardListService implements Service {
	@Override
	public List<BoardVO> service(Object obj) throws Exception {
		BoardDAO dao = new BoardDAO();
		return dao.list();
	}
	

}
