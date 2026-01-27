package com.blog.board.vo;

public class CurrentBoard {
   
    // 어디서나 접근 가능한 게시글 정보 보관함 (static)
    private static BoardVO currentBoard = null;

    // 게시글 정보를 저장하는 메서드 (Setter)
    public static void setBoardVO(BoardVO vo) {
        currentBoard = vo;
    }

    // 현재 저장된 게시글 정보를 통째로 가져오는 메서드
    public static BoardVO getBoardVO() {
        return currentBoard;
    }

    // 현재 게시글이 선택되어 있는지 확인
    public static boolean isSet() {
        return currentBoard != null;
    }

    // 게시글 번호를 바로 가져오는 메서드
    public static int getPostNo() {
        if (!isSet()) return 0; // 혹은 예외처리
        return currentBoard.getPostNo();
    }

    // 제목을 바로 가져오는 메서드
    public static String getTitle() {
        if (!isSet()) return null;
        return currentBoard.getTitle();
    }

    // 작성자를 바로 가져오는 메서드
    public static String getWriterId() {
        if (!isSet()) return null;
        return currentBoard.getWriterId();
    }

    // 현재 보고 있는 게시글 정보를 간단히 출력하는 메서드 (Login.loginPrint 참고)
    public static void printCurrentBoardInfo() {
        System.out.println("+--------------------------------------+");
        if(isSet()) {
            System.out.println("+ 현재 선택된 글: " + currentBoard.getPostNo() + "번");
            System.out.println("+ 제목: " + currentBoard.getTitle());
            System.out.println("+ 작성자: " + currentBoard.getWriterId());
        } else {
            System.out.println("+ 선택된 게시글이 없습니다. +");
        }
        System.out.println("+--------------------------------------+\n");
    }
}