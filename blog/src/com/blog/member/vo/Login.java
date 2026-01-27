package com.blog.member.vo;

public class Login {

    public static LoginVO loginVO = null;

    // 로그인 여부
    public static boolean isLogin() {
        // loginVO가 null이 아니면 로그인 된 상태
        return loginVO != null;
    }

    // 로그인 정보 출력
    public static void loginPrint() {
        System.out.println("+--------------------------------+");
        if (isLogin()) {
            // loginVO에서 이름과 아이디를 가져옴
            System.out.println("+ " + loginVO.getName() + "(" + loginVO.getId() + ")님 로그인 중 +");
        } else {
            System.out.println("~ 환영합니당. 로그인해 주세요. ~");
        }
        System.out.println("+------------------------------+\n");
    }

    public static void setLoginVO(LoginVO vo) {
        loginVO = vo;
    }

    public static String getId() {
        if(loginVO != null) {
            return loginVO.getId();
        }
        return null;
    }
}