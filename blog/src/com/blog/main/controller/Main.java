package com.blog.main.controller;

import com.blog.board.controller.BoardController;
import com.blog.board.controller.OtherBoardController;
import com.blog.category.controller.CategoryController;
import com.blog.main.service.Execute;
import com.blog.member.controller.MemberController;
import com.blog.member.service.MemberChangePwService;
import com.blog.member.service.MemberSearchIdService;
import com.blog.member.service.MemberUserChangePwService;
import com.blog.member.vo.Login;
import com.blog.member.vo.MemberVO;
import com.blog.subscribe.controller.SubscribesController;
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
					System.out.println(" 1. 비밀번호 변경  2. 로그아웃  3. 블로그 방문하기 4. 나의 블로그 보기 ");
				} // 메뉴 출력 끝
				System.out.println(" 0. 프로그램 종료");
				System.out.println("=======================================================");

				// 2. 메뉴 선택
				String menu = In.getStr("메뉴 입력");
				System.out.println(); // 줄바꿈

				// 3. 메뉴 처리
				switch (menu) {

				// ==== 1. 로그인 / 비번 변경
				case "1":
					if (!Login.isLogin()) { // 1. 로그인을 하지 않은 경우 - 로그인
						new LoginController().execute();
					} else { // 로그인 한 경우 - 개인 정보 수정
						System.out.println("1. 비번 변경 \n");
						System.out.println("*** 비밀번호 변경 ***");
						MemberVO vo2 = new MemberVO();
						vo2.setId(In.getStr("아이디"));
						vo2.setPw(In.getStr("현재 비밀번호"));
						vo2.setNewPw(In.getStr("새 비밀번호"));
						Integer result = (Integer) Execute.execute(new MemberUserChangePwService(), vo2);

						if (result == 1) {
							System.out.println("비밀번호 변경 완료 \n");
						} else {
							System.out.println("비밀번호 변경 실패 \n");

						}
					}
					break;

				// ==== 2. 회원가입 / 로그아웃
				case "2":
					if (!Login.isLogin()) { // 로그인을 하지 않은 경우 - 회원가입
						new MemberController().execute();
					} else { // 로그인 한 경우 - 로그아웃
						Login.setLoginVO(null);
						System.out.println("로그아웃 되셨습니다 \n");
					}
					break;

				// ==== 3. 아이디찾기 / 블로그 방문하기
				case "3":
					if (!Login.isLogin()) { // 로그인을 하지 않은 경우 - 아이디 찾기
						System.out.println("*** 아이디 찾기 ***");
						MemberVO vo11 = new MemberVO();
						vo11.setName(In.getStr("이름"));
						vo11.setEmail(In.getStr("이메일"));
						String id = (String) Execute.execute(new MemberSearchIdService(), vo11);
						System.out.println("찾으시는 아이디 : " + id + "\n");
					} else { // 로그인을 한 경우 - 블로그 방문하기 ===============================
						new OtherBoardController().execute();
					}
					break;

				// ==== 4. 비번 찾기 / 나의 블로그 방문하기 ==========================================
				case "4":
					if (!Login.isLogin()) { // 로그인을 하지 않은 경우 - 비밀번호 찾기
						System.out.println("*** 비밀번호 찾기 ***");
						MemberVO vo11 = new MemberVO();
						vo11.setName(In.getStr("이름"));
						vo11.setEmail(In.getStr("이메일"));
						String pw = (String) Execute.execute(new MemberChangePwService(), vo11);
						System.out.println("찾으시는 비밀번호 : " + pw + "\n");
					} else { // 로그인을 한 경우 - 나의 블로그 보기
						while (true) {
							System.out.println("===========================================");
							System.out.println("<<< 나의 블로그 방문하기 >>>");
							System.out.println(" 1. 카테고리  2. 게시글 메뉴  3. 구독  0. 이전");
							System.out.println("===========================================");

							String menu1 = In.getStr("메뉴 입력");
							System.out.println(); // 줄바꿈

							switch (menu1) {
							case "1": // 카테고리 리스트로 이동
								new CategoryController().execute();
								break;

							case "2": // 게시글 메뉴로 이동
								new BoardController().execute();
								break;

							case "3":
								new SubscribesController().execute(Login.getLoginVO().getId());
								break;

							case "0":
								break;

							} // switch 닫기
							break;
						}
					}
					break;

				case "0":
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);

				default:
					invalidMenuPrint();

				} // switch 끝

			} catch (Exception e) {
				e.printStackTrace();
			} // try~catch 끝
		}

	}

	public static void invalidMenuPrint() {
		System.out.println("잘못된 메뉴입니다. 다시 입력하세요. \n");
	}
} // Main 끝