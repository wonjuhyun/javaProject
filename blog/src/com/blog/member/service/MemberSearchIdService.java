package com.blog.member.service;

import com.blog.main.service.Service;
import com.blog.member.dao.MemberDAO;
import com.blog.member.vo.MemberVO;

public class MemberSearchIdService implements Service{

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub

		MemberDAO dao = new MemberDAO();
		return dao.searchId((MemberVO) obj);	}

}
