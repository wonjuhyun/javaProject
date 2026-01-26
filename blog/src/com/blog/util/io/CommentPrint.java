package com.blog.util.io;

import java.util.List;

import com.blog.comment.vo.Comment;

public class CommentPrint {

	// 1. list - 리스트
	public static void print(List<Comment> list) {
		System.out.println();
		System.out.println("-------------------");
		System.out.println(" 댓글 리스트");
		System.out.println("-------------------");
		System.out.println("+=========================================================+");
		for (Comment vo : list) {
			System.out.println("  " + vo.getWriterNick() + " : " + vo.getContent());
			System.out.print(vo.getCreatedAt() + " / ");
			System.out.print(vo.getUpdatedAt() + " / ");
		}
		System.out.println("+=========================================================+");
	}  // print(list) 끝

	// 2. vo - 글 보기 : print() 오버로드 - 파라미터가 다르면 다르게 인식한다
	public static void print(Comment view) {
		System.out.println();
		System.out.println("-------------------");
		System.out.println(" 내 댓글 보기");
		System.out.println("-------------------");
		System.out.println("+=================================================+");
		System.out.println("  내 댓글 : " + view.getContent());
		System.out.println("  작성일 : " + view.getCreatedAt());
		System.out.println("  수정일 : " + view.getUpdatedAt());
		System.out.println("+=================================================+");
	}  // print(view) 끝
	
	
}  // 
