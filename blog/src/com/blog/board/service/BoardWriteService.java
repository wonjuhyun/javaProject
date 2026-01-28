package com.blog.board.service;

import com.blog.board.DAO.BoardDAO;
import com.blog.board.vo.BoardVO;
import com.blog.main.service.Service;

public class BoardWriteService implements Service{
		//list  - list<Board> view - BoardVO,insert,update,delete -Integer
		public Integer service(Object obj) throws Exception{
			BoardDAO dao = new BoardDAO();
			return dao.write((BoardVO)obj);
		}
}
