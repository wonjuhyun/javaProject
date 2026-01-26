package com.blog.board.controller;

import java.util.List;


import com.blog.board.service.BoardListService;
import com.blog.board.service.BoardViewService;
import com.blog.board.vo.BoardVO;
import com.blog.main.service.Execute;
import com.blog.util.io.BoardPrint;
import com.blog.util.io.In;

public class BoardController {
    
	@SuppressWarnings("unchecked")
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
                    List<BoardVO> list = (List<BoardVO>) Execute.execute(new BoardListService(), null);
                    BoardPrint.print(list);

                    break;
                    
                case "2":
                	 	System.out.println("게시글 글보기");
                     int no = In.getInt("글번호");
                     BoardVO vo = (BoardVO) Execute.execute(new BoardViewService(), new int[]{no, 1});
                     view(vo); 
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
public static void view (BoardVO vo) {
      while(true) {
    	System.out.println("========= 게시글 보기 =========");
    	System.out.println("제목:"+vo.getTitle());
    	System.out.println("내용:"+vo.getContent());
        System.out.println("===================================================");
        System.out.println("1. 공감 2. 댓글쓰기 3. 구독한 블로그확인  0. 나가기");
        System.out.println("===================================================");
        String menu = In.getStr("선택");
        switch (menu) {
    	case "1":
    		System.out.println("공감을 누르셨습니다.");
    		break;
    	case "2":
    		System.out.println("댓글쓰기를 누르셨습니다.");
    		break;
    	case "3":
    		System.out.println("구독한 블로그 확인을 누르셨습니다.");
    		break;
    	case "0":
    		System.out.println("나가기");
    		return;
    	default:
            System.out.println("잘못된 선택입니다.");

        
        	}
      }
   }
      public Integer writer (BoardVO vo) {
          while(true) {
        	System.out.println("======================= 게시글 수정 =======================");
        	System.out.println("제목:"+vo.getTitle());
        	System.out.println("내용:"+vo.getContent());
            System.out.println("==========================================================");
            System.out.println(" 1. 제목  2. 내용  3.카테고리	 8. 수정 완료  9. 수정 취소 0. 나가기");
            System.out.println("==========================================================");
            String menu = In.getStr("선택");
            switch (menu) {
        	case "1":
        		vo.setTitle(In.getStr("제목"));
        		break;
        	case "2":
        		vo.setContent(In.getStr("제목")); 
        		break;
        	case "3":
        		vo.setCateNo(In.getInt("카테고리"));
        		break;
        	case "8":
        		return 8;
        	case "9":
        		return 9;
        	case "0":
        		
        		return 0;
        	default:
                System.out.println("잘못된 선택입니다.");

            
            	}
        }
    }
}
