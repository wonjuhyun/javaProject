package com.blog.main.controller;

import com.blog.member.vo.Login;
import com.blog.util.io.In;

public class Main {

    public static void main(String[] args) {

        System.out.println("---------------------------------");
        System.out.println(" 환영합니다 ");
        System.out.println("---------------------------------\n");

        // DB 로딩
        try {
            Class.forName("com.blog.main.util.db.DB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("DB 클래스가 존재하지 않아 시스템을 종료합니다.");
            System.exit(1);
        }

        // 메인 반복문
        while (true) {

            // 로그인 정보 출력
            Login.loginPrint();

            // 1. 메인 메뉴 출력
            System.out.println("<<< 메인 메뉴 >>>");
            System.out.println("=========================================");
            System.out.println(" 1. 로그인  2. 회원가입  3. 아이디 찾기 4. 비번 찾기 ");
            System.out.println(" 0. 프로그램 종료");
            System.out.println("=========================================");

            // 2. 메뉴 입력
            String menu = In.getStr("메뉴 입력");
            System.out.println();

            // 3. 메뉴 처리
            switch (menu) {
                case "1":
                    System.out.println("로그인 처리");
                    // new MemberController().login();
                    break;

                case "2":
                    System.out.println("회원가입");
                    break;

                case "3":
                    System.out.println("아이디 찾기");
                    break;

                case "4":
                    System.out.println("비번 찾기");
                    break;

                case "0":
                    System.out.println("+--------------------------------------+");
                    System.out.println(" 프로그램을 종료합니다.");
                    System.out.println("+--------------------------------------+");
                    System.exit(0);

                default:
                    invalidMenuPrint();
            }

            System.out.println();
        }
    }

    //  반드시 main 밖
    public static void invalidMenuPrint() {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(" 잘못된 메뉴를 입력하셨습니다. 다시 입력해 주세요.");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }
}
