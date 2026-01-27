package com.blog.board.service;

import com.blog.board.DAO.BoardDAO;
import com.blog.board.vo.BoardVO;
import com.blog.main.service.Service;

public class BoardDeleteService implements Service{

	@Override
	public Object service(Object obj) throws Exception {
		BoardDAO dao = new BoardDAO();
		return dao.delete((BoardVO)obj);
	}

}
