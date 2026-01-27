package com.blog.member.service;

import com.blog.main.service.Service;
import com.blog.member.dao.MemberDAO;
import com.blog.member.vo.LoginVO;

public class LoginService implements Service {

	@Override
	public LoginVO service(Object obj) throws Exception {
		MemberDAO dao = new MemberDAO();
		return dao.login((LoginVO) obj);
	}
	
}
