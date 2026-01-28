package com.blog.like.controller;

import com.blog.board.vo.CurrentBoard;
import com.blog.like.dao.LikeDAO;
import com.blog.like.service.LikeDeleteService;
import com.blog.like.service.LikeInsertService;
import com.blog.like.vo.LikeVO;
import com.blog.main.service.Execute;
import com.blog.member.vo.Login;


public class LikeController {

	public void execute() throws Exception {
		LikeVO vo = new LikeVO();
		LikeDAO dao = new LikeDAO();

		int postNo = CurrentBoard.getBoardVO().getPostNo();
		vo.setPostNo(postNo); 
		vo.setLikerId(Login.getId());

		Boolean isLiked = dao.isLiked(vo);
		
		String id = CurrentBoard.getBoardVO().getWriterId();
		
		if(isLiked) {
			Execute.execute(new LikeDeleteService(), vo);
			System.out.println("*** '" + id + "'님의 게시글에 공감을 취소하셨습니다. ***");
		} else {
			Execute.execute(new LikeInsertService(), vo);
			System.out.println("*** '" + id + "'님의 게시글에 공감을 누르셨습니다. ***");
		}
		System.out.println(" 공감 수 : " + postNo);

	}
	
}
