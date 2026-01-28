package com.blog.util.io;

import java.util.List;
import com.blog.board.vo.BoardVO;

public class BoardPrint {
    
    // 게시글 리스트 출력
    public static void print(List<BoardVO> list) {
        System.out.println("              [게시글 리스트]                  ");
        System.out.println("==============================================");
        System.out.println("번호|제목|작성자|작성일|수정일|조회수|0. 나가기");
        System.out.println("==============================================");
        
        // 리스트에 담긴 게시글들을 한 줄씩 출력
        for (BoardVO vo : list) {
            System.out.print(" | " + vo.getPostNo());     // 글번호
            System.out.print(" | " + vo.getTitle());      // 제목
            System.out.print(" | " + vo.getWriterId());   // 작성자 ID
            System.out.print(" | " + vo.getCreatedAt());  // 작성일
            System.out.print(" | " + vo.getUpdatedAt());  // 수정일
            System.out.print(" | " + vo.getHit());        // 조회수
            
            System.out.println();
        }
        System.out.println("==================================\n");
    }
    
    // 단일 게시글 출력
    public static void print(BoardVO vo) {
        System.out.println("==============================================");
        System.out.println("              [게시글 글보기]                  ");
        System.out.println("==============================================");
        
        // 게시글 상세 정보 출력
        System.out.print(" | " + vo.getPostNo());     // 글번호
        System.out.print(" | " + vo.getTitle());      // 제목
        System.out.print(" | " + vo.getWriterId());   // 작성자 ID
        System.out.print(" | " + vo.getCreatedAt());  // 작성일
        System.out.print(" | " + vo.getUpdatedAt());  // 수정일
        System.out.print(" | " + vo.getHit());        // 조회수
        System.out.println();
        
        System.out.println("+==================================+\n");
    }
}

