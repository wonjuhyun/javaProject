package com.blog.member.controller;

import com.blog.member.service.MemberWriteService;
import com.blog.member.vo.Login;
import com.blog.member.vo.MemberVO;
import com.blog.main.service.Execute;
import com.blog.util.io.In;

public class MemberController {

    public void execute() {

        while (true) {
            Login.loginPrint();

            try {
                System.out.println("<<< 회원가입 메뉴 >>>");
                System.out.println(" 1. 회원가입");
                System.out.println(" 0. 이전 메뉴");

                String menu = In.getStr("메뉴 입력");
                System.out.println();

                switch (menu) {

                case "1":
                    MemberVO vo = new MemberVO();
                    vo.setId(In.getStr("아이디"));
                    vo.setPw(In.getStr("비밀번호"));
                    vo.setName(In.getStr("이름"));
                    vo.setGender(In.getStr("성별"));
                    vo.setBirth(In.getStr("생년월일"));
                    vo.setTel(In.getStr("연락처"));
                    vo.setEmail(In.getStr("이메일"));

                    Execute.execute(new MemberWriteService(), vo);

                    System.out.println("*****************************************");
                    System.out.println(" 축하드립니다. 회원가입이 되셨습니다. 로그인하세요.");
                    System.out.println("*****************************************\n");
                    break;

                case "0":
                    System.out.println("이전 메뉴로 이동합니다.\n");
                    return; // MemberController 종료

                default:
                    System.out.println("잘못된 메뉴입니다.\n");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                System.out.println(" 회원 관리 처리 중 오류가 발생되었습니다.");
                System.out.println(" 오류 : " + e.getMessage());
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
            }
        }
    }
}
