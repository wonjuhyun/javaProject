package com.blog.main.controller;

import com.blog.member.vo.Login;
import com.blog.util.io.In;
import com.blog.member.service.MemberSearchIdService;
import com.blog.member.service.MemberUserChangePwService;
import com.blog.member.service.MemberWriteService;
import com.blog.member.vo.MemberVO;

import com.blog.main.service.Execute;
import com.blog.member.service.LoginService;
import com.blog.member.vo.LoginVO;

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

			System.out.println("<<< 원하는 항목을 입력해주세요 >>>");
			System.out.println("=========================================");
			System.out.println(" 1. 로그인  2. 회원가입  3. 아이디 찾기 4. 비번 찾기 ");
			System.out.println(" 0. 프로그램 종료");
			System.out.println("=========================================");

			String menu = In.getStr("메뉴 입력");
			System.out.println();

			switch (menu) {
			
			case "1":
				System.out.println("로그인 처리");

				MemberVO loginVO = new MemberVO();
				loginVO.setId(In.getStr("아이디"));
				loginVO.setPw(In.getStr("비밀번호"));

				LoginVO resultVO =
						(LoginVO) Execute.execute(new LoginService(), loginVO);

				if (resultVO == null) {
					System.out.println("아이디 또는 비밀번호가 올바르지 않습니다.");
				} else {
					Login.setLoginVO(resultVO);
					System.out.println(resultVO.getName() + "님 환영합니다!");
				}
				break;


			case "2":
				System.out.println("회원가입");
				MemberVO vo1 = new MemberVO();
				vo1.setId(In.getStr("아이디"));
				vo1.setPw(In.getStr("비밀번호"));
				vo1.setName(In.getStr("이름"));
				vo1.setTel(In.getStr("연락처"));
				vo1.setEmail(In.getStr("이메일"));
				Execute.execute(new MemberWriteService(), vo1);
				System.out.println("회원가입이 완료되었습니다.\n");
				break;

			case "3":
				System.out.println("*** 아이디 찾기 ***");
				MemberVO vo11 = new MemberVO();
				vo11.setName(In.getStr("이름"));
				vo11.setEmail(In.getStr("이메일"));
				String id = (String) Execute.execute(new MemberSearchIdService(), vo11);
				System.out.println("찾으시는 아이디 : " + id + "\n");
				break;

			case "4":
				System.out.println("*** 비밀번호 변경 ***");
				MemberVO vo2 = new MemberVO();
				vo2.setId(In.getStr("아이디"));
				vo2.setPw(In.getStr("현재 비밀번호"));
				vo2.setNewPw(In.getStr("새 비밀번호"));
				Integer result =
						(Integer) Execute.execute(new MemberUserChangePwService(), vo2);
				System.out.println(result == 1
						? "비밀번호 변경 완료\n"
								: "비밀번호 변경 실패\n");
				break;

			case "0":
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);

			default:
				invalidMenuPrint();
			}
		}
	}

	public static void invalidMenuPrint() {
		System.out.println("잘못된 메뉴입니다. 다시 입력하세요.");
	}
}
