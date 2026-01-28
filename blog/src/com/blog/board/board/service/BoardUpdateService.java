package com.blog.board.service;

import com.blog.board.DAO.BoardDAO;
import com.blog.board.vo.BoardVO;
import com.blog.main.service.Service;

public class BoardUpdateService implements Service{

	@Override
	public Object service(Object obj) throws Exception {
		BoardDAO dao = new BoardDAO();
		return dao.update((BoardVO)obj);
	}

}
