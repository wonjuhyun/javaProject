package com.blog.member.service;

import com.blog.main.service.Service;
import com.blog.member.dao.MemberDAO;
import com.blog.member.vo.MemberVO;

public class LoginService implements Service {

    @Override
    public Object service(Object obj) throws Exception {
        MemberVO vo = (MemberVO) obj;
        return new MemberDAO().login(vo);
    }
}
