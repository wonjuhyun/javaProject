package com.blog.board.service;

import java.util.List;

import com.blog.board.DAO.BoardDAO;
import com.blog.board.vo.BoardVO;
import com.blog.main.service.Service;

public class BoardlistCategory implements Service{

	@Override
	public Object service(Object obj) throws Exception {
		 Integer cateNo = (Integer) obj;
	      BoardDAO dao = new BoardDAO();
	      List<BoardVO> list = dao.listCategory(cateNo);

	        return list;
	}

}
