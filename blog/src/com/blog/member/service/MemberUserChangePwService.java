package com.blog.member.service;

import com.blog.main.service.Service;
import com.blog.member.dao.MemberDAO;
import com.blog.member.vo.MemberVO;

public class MemberUserChangePwService implements Service {

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub


        MemberVO vo = (MemberVO) obj;

        // user = 1 → 로그인 상태에서 비밀번호 변경
        return new MemberDAO().changePw(vo, 1);
	}

}
