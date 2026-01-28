package com.blog.board.service;

import java.util.List;

import com.blog.board.DAO.BoardDAO;
import com.blog.board.vo.BoardVO;
import com.blog.main.service.Service;

public class OtherBoardListService implements Service {
	public List<BoardVO> service(Object obj) throws Exception {
		// DAO의 checkId 호출 (obj는 String targetId)
		return new BoardDAO().list((String) obj);
	}
	

}
