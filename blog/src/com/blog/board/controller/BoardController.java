package com.blog.board.controller;

import java.util.List;

import com.blog.board.service.BoardListService;
import com.blog.board.service.Execute;
import com.blog.board.vo.In;
import com.blog.board.vo.BoardVO;

public class BoardController {
    
	public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("========= 게시글 메뉴 =========");
            System.out.println("============================");
            System.out.println("1. 게시글 리스트 2. 게시글 글보기");
            System.out.println("3. 게시글 글등록 4. 게시글 글삭제");
            System.out.println("0. 나가기");
            System.out.println("============================");

            String menu = In.getStr(); 
            System.out.println();
            switch (menu) {
                case "1":
                    System.out.println("게시글 리스트");
                    @SuppressWarnings("unchecked")
					List<BoardVO> list = (List<BoardVO>) Execute.execute(new BoardListService(), null);
                    System.out.println();
                    break;
                    
                case "2":
                    System.out.println("게시글 글보기");
                    break;

                case "3":
                    System.out.println("게시글 글등록");
                    break;

                case "4":
                    System.out.println("게시글 글삭제");
                    break;

                case "0":
                    System.out.println("프로그램을 종료합니다.");
                    return; 

                default:
                    System.out.println("잘못된 메뉴 선택입니다.");
                    break;
            }
        }
        
    }
}
