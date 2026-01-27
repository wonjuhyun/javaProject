package com.blog.util.io;

import java.util.List;
import com.blog.board.vo.BoardVO;
public class BoardPrint {
	public static void print(List<BoardVO>list) {
			System.out.println("              [게시글 리스트]                  ");
		    System.out.println("==============================================");
		    System.out.println("번호|제목|작성자|작성일|수정일|조회수|0. 나가기");
	        System.out.println("==============================================");
		
			for (BoardVO vo : list) {
				System.out.print(" | "+ vo.getPostNo());
				System.out.print(" | "+ vo.getTitle());
				System.out.print(" | "+ vo.getWriterId());
				System.out.print(" | "+ vo.getCreatedAt());
				System.out.print(" | "+ vo.getUpdatedAt());
				System.out.print(" | "+ vo.getHit());
				
				System.out.println();
		}
		System.out.println("==================================\n");
	}
	public static void print(BoardVO vo) {
		System.out.println("==============================================");
		System.out.println("              [게시글 글보기]                  ");
		System.out.println("==============================================");
		
			System.out.print(" | "+ vo.getPostNo());
			System.out.print(" | "+ vo.getTitle());
			System.out.print(" | "+ vo.getWriterId());
			System.out.print(" | "+ vo.getCreatedAt());
			System.out.print(" | "+ vo.getUpdatedAt());
			System.out.print(" | "+ vo.getHit());
			System.out.println();
			System.out.println("+==================================+\n");
	
	}
	
}

