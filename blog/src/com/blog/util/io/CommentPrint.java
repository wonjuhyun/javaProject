package com.blog.util.io;

import java.util.List;

import com.blog.comment.vo.Comment;

public class CommentPrint {

	// 1. list - 리스트
	public static void print(List<Comment> list) {
		
		System.out.println("<< 댓글 리스트 >>");
		System.out.println("=============================================================");
		
		if (list == null || list.isEmpty()) {
			System.out.println(" 등록된 댓글이 없습니다. 첫 댓글을 작성해 주세요.");
		} else {
			for (Comment vo : list) {
				// 출력 예시: "AAA : 퍼가요~"
				System.out.println(" " + vo.getWriterNick() + " : " + vo.getContent());
				System.out.print(" (작성일 : " + vo.getCreatedAt());
				if (vo.getUpdatedAt() != null) {
					System.out.print(" / 수정일 : " + vo.getUpdatedAt());
				}
				System.out.println(")");
			}
		}
		System.out.println("=============================================================");
		
	}  // print(list) 끝

	// 2. vo - 글 보기 : print() 오버로드 - 파라미터가 다르면 다르게 인식한다
	public static void print(Comment view) {

		System.out.println();
		System.out.println("<< 나의 댓글 >>");
		System.out.println("+=================================================+");
		
		if (view != null) {
			System.out.println("  " + view.getWriterNick() + " : " + view.getContent());
			System.out.println("  작성일 : " + view.getCreatedAt());
			if (view.getUpdatedAt() != null) {
				System.out.println("  수정일 : " + view.getUpdatedAt());
			}
		} else System.out.println(" 등록된 댓글이 없습니다. 댓글을 작성해 주세요.");
		System.out.println("+=================================================+");
		
	}  // print(view) 끝
	
	
}  // 
