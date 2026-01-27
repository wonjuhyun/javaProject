package com.blog.main.controller;

import com.blog.member.controller.MemberController;
import com.blog.member.vo.Login;
import com.blog.util.io.In;

public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("⋆｡˚ ☁︎ ˚｡⋆｡˚☽˚｡⋆⋆｡˚ ☁︎ ˚｡⋆｡˚☽˚｡⋆⋆｡˚ ☁︎ ˚｡⋆");
		System.out.println(" <<스크램블 에그에 오신 것을 환영합니다>> ");
		System.out.println("⋆｡˚ ☁︎ ˚｡⋆｡˚☽˚｡⋆⋆｡˚ ☁︎ ˚｡⋆｡˚☽˚｡⋆⋆｡˚ ☁︎ ˚｡⋆\n");

		try {
			Class.forName("com.blog.util.db.DB");
		} catch (ClassNotFoundException e) {
			System.out.println("DB 클래스가 존재하지 않아 시스템을 종료합니다.");
			System.exit(1);
		}

		while (true) {
			Login.loginPrint();
			try {
				// 1. 메인 메뉴 출력
				System.out.println("<<< 메인 메뉴 >>>");
				System.out.println("=======================================================");
				if (!Login.isLogin()) // 로그인을 하지 않은 경우
					System.out.println(" 1. 로그인  2. 회원가입  3. 아이디 찾기 4. 비번 찾기 ");
				else { // 로그인을 한 경우
					System.out.println(" 1. 개인 정보 수정  2. 로그아웃  3. 블로그 방문하기 4. 나의 블로그 보기 ");
				} // 메뉴 출력 끝
				System.out.println(" 0. 프로그램 종료");
				System.out.println("=======================================================");

				// 2. 메뉴 선택
				String menu = In.getStr("메뉴 입력");
				System.out.println(); // 줄바꿈

				// 3. 메뉴 처리
				switch (menu) {

				case "1":
					if (!Login.isLogin()) { // 로그인을 하지 않은 경우 - 로그인
						new LoginController().execute();
					} else { // 로그인 한 경우 - 개인 정보 수정
						

					}
					break;

				case "2":
					if (!Login.isLogin()) { // 로그인을 하지 않은 경우 - 회원가입
						new MemberController().execute();
					} else { // 로그인 한 경우 - 로그아웃

					}
					break;
				} // switch(menu) 끝

			} catch (Exception e) {
				e.printStackTrace();
			} // try~catch 끝
		} // while (true) 끝

	} // static void 끝
} // Main() 끝
