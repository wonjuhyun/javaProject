package com.blog.board.service;

import com.blog.board.DAO.BoardDAO;
import com.blog.main.service.Service;

public class BoardViewService implements Service {

	@Override
	public Object service(Object obj) throws Exception {
		int[] arrs = (int[]) obj;
		int no = arrs[0];
		int inc = arrs[1]; 
		BoardDAO dao = new BoardDAO();
		if(inc == 1) {
		Integer result =  dao.inc(no);
		if(result != 1 )throw new Exception("");
		}
		return dao.view(no);
	}

}
