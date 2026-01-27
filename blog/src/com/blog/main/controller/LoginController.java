package com.blog.main.controller;

import com.blog.main.service.Execute;
import com.blog.member.service.LoginService;
import com.blog.member.vo.Login;
import com.blog.member.vo.LoginVO;
import com.blog.util.io.In;

public class LoginController {

	public void execute() throws Exception {

		System.out.println("로그인 처리");
		LoginVO loginVO = new LoginVO();
		loginVO.setId(In.getStr("아이디"));
		loginVO.setPw(In.getStr("비밀번호"));
		LoginVO resultVO = (LoginVO) Execute.execute(new LoginService(), loginVO);
		if (resultVO == null) {
			System.out.println("아이디 또는 비밀번호가 올바르지 않습니다.");
		} else {
			Login.setLoginVO(resultVO);
			System.out.println(resultVO.getName() + " 님 환영합니다!");
			
			return;
		}

	}

}
