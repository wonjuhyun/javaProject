package com.blog.member.vo;


public class Login {public Login() {
	// TODO Auto-generated constructor stub
}
// 어디서나 누구나 접근 가능한 변수 선언 - Login을 생성하지 않고 바로 사용
private static LoginVO loginVO = null;

// loginVO에 데이터 넣기를 하기 setter
public static void setLoginVO(LoginVO loginVO) {
	Login.loginVO = loginVO;
}

// 아이디를 받아가는 메서드
public static String getId() throws Exception{
	return loginVO.getId();
}

// 로그인이 되어 있는가?
public static boolean isLogin() {
	if(loginVO == null) return false;
	return true;
}

// 등급 번호를 받아간다.
public static int getGradeNo() {
	return loginVO.getGradeNo();
}

// 로그인 정보를 출력하는 메서드
public static void loginPrint() {
	System.out.println("+--------------------------------------+");
	if(isLogin()) { // 로그인을 한 경우
		System.out.println("+ " + loginVO.getName() + "(" + loginVO.getId() + ")" 
		+ "님은 " + loginVO.getGradeName() + "으로 로그인하셨습니다." + "+");
	} else System.out.println("+ 환영합니다 . 로그인해 주세요.  +");
	System.out.println("+--------------------------------------+\n");
}


}

