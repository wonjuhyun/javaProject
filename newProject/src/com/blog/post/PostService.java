package com.blog.post;

// 게시글 서비스 (연결용)
public class PostService {

    // 카테고리별 게시글 조회
    public void showPostListByCategory(int cateNo) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("카테고리 번호 [" + cateNo + "] 게시글 목록");
        System.out.println("========================================");

        // 실제 게시글 출력은 게시글 담당자가 구현
        System.out.println("(게시글 리스트 출력 영역)");
        System.out.println("(PostService에서 구현 예정)");

        System.out.println("========================================");
    }
}
