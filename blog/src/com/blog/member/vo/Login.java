package com.blog.member.vo;

public class Login {

    // 로그인한 회원 정보
    public static MemberVO loginUser = null;

    // 로그인 여부
    public static boolean isLogin() {
        return loginUser != null;
    }

    // 로그인 정보 출력
    public static void loginPrint() {
        System.out.println("+--------------------------------+");
        if (isLogin()) {
            System.out.println("+ " + loginUser.getName() + "(" + loginUser.getId() + ")님 로그인 중 +");
        } else {
            System.out.println("~ 환영합니당. 로그인해 주세요. ~");
        }
        System.out.println("+------------------------------+\n");
    }

	public static void setLoginVO(LoginVO vo) {
		// TODO Auto-generated method stub
		
	}

	public static String getId() {
		// TODO Auto-generated method stub
		return null;
	}
}
