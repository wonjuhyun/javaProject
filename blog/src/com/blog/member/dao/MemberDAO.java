package com.blog.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.blog.member.vo.LoginVO;
import com.blog.member.vo.MemberVO;
import com.blog.util.db.DB;

public class MemberDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// ===================== 로그인 ===================== //
	public LoginVO login(LoginVO obj) throws Exception {

		LoginVO vo = null;

		try {
			con = DB.getConnection();

			String sql = "select id, nickname, name " + "from users " + "where id = ? and pw = ? and active = 'Y'";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, obj.getId());
			pstmt.setString(2, obj.getPw());

			rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				vo = new LoginVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setNickname(rs.getString("nickname"));
			}

		} finally {
			DB.close(con, pstmt, rs);
		}

		return vo;
	}

	// ===================== 아이디 찾기 =====================
	public String searchId(MemberVO vo) throws Exception {

		String id = null;

		try {
			con = DB.getConnection();

			String sql = "select id from users where name = ? and email = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());

			rs = pstmt.executeQuery();

			if (rs != null && rs.next()) {
				id = rs.getString("id");
			}

		} finally {
			DB.close(con, pstmt, rs);
		}

		return id;
	}

	public Object changePw(MemberVO obj, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}