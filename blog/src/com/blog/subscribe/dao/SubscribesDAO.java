package com.blog.subscribe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.blog.member.vo.LoginVO;
import com.blog.subscribe.vo.SubscribesVO;
import com.blog.util.db.DB;

public class SubscribesDAO {

	Connection con = null;
	// 쿼리를 실행하는 객체
	PreparedStatement pstmt = null;
	// 결과를 저장하는 객체 - select 일때만 사용되는 객체
	ResultSet rs = null;

	// 5-1. 내가 구독한 사람 리스트
	public List<SubscribesVO> followingList(String loginId) throws Exception {
		List<SubscribesVO> list = new ArrayList<>();

		try { // try~catch : 에러방지
				// 드라이버 확인+연결 객체
			con = DB.getConnection();

			// 실행할 쿼리 작성
			String sql = " select following_id from subscribes where follower_id = ? order by following_id asc";

			// 실행 객체+데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);

			// 실행
			rs = pstmt.executeQuery();

			// DB에서 가져온 데이터 채우기
			if (rs != null) {
				while (rs.next()) {
					SubscribesVO vo = new SubscribesVO();
					vo.setFollowingId(rs.getString("following_id"));
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			DB.close(con, pstmt, rs);
		}
		return list;
	}

	// 5-1-1. 구독하기
	public int insert(SubscribesVO vo) throws Exception {
		int result = 0;

		try {
			con = DB.getConnection();

			String sql = " insert into subscribes(sub_no, following_id, follower_id) "
					+ " values(subscribes_seq.nextval, ?, ?) ";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, vo.getFollowingId());
			pstmt.setString(2, vo.getFollowerId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt);
		}

		return result;
	}

	// 5-1-2. 구독 취소하기
	public int delete(SubscribesVO vo) throws Exception {
		int result = 0;

		try {
			con = DB.getConnection();

			String sql = " delete from subscribes where following_id = ? and follower_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, vo.getFollowingId());
			pstmt.setString(2, vo.getFollowerId());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt);
		}

		return result;
	}

	// 5-2. 나를 구독한 사람 리스트
	public List<SubscribesVO> followerList(String loginId) throws Exception {
		List<SubscribesVO> list = new ArrayList<>();

		try { // try~catch : 에러방지
				// 드라이버 확인+연결 객체
			con = DB.getConnection();

			// 실행할 쿼리 작성
			String sql = " select follower_id from subscribes where following_id = ? order by follower_id asc";

			// 실행 객체+데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginId);

			// 실행
			rs = pstmt.executeQuery();

			// DB에서 가져온 데이터 채우기
			if (rs != null) {
				while (rs.next()) {
					SubscribesVO vo = new SubscribesVO();
					vo.setFollowerId(rs.getString("follower_id"));
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;

		} finally {
			DB.close(con, pstmt, rs);
		}

		return list;
	}

}
