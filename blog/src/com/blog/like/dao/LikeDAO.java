package com.blog.like.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.blog.like.vo.LikeVO;
import com.blog.util.db.DB;

public class LikeDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. 공감 여부 확인 (했으면 true, 안 했으면 false)
		public boolean isLiked(LikeVO vo) throws Exception {
			boolean result = false;
			con = DB.getConnection();
			
			// 내 아이디(liker_id)와 글번호(post_no)가 일치하는 데이터가 있는지 확인
			String sql = "SELECT COUNT(*) FROM likes WHERE post_no = ? AND liker_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPostNo());
			pstmt.setString(2, vo.getLikerId());
			
			rs = pstmt.executeQuery();
			
			if (rs != null && rs.next()) {
				// 카운트가 1 이상이면 이미 공감한 상태
				if (rs.getInt(1) > 0) result = true;
			}
			
			DB.close(con, pstmt, rs);
			return result;
		}
		
		// 2. 공감 하기 (INSERT)
		public int insert(LikeVO vo) throws Exception {
			int result = 0;
			con = DB.getConnection();
			
			String sql = "INSERT INTO likes(post_no, liker_id) VALUES(?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPostNo());
			pstmt.setString(2, vo.getLikerId());
			
			result = pstmt.executeUpdate();
			
			DB.close(con, pstmt);
			return result;
		}
		
		// 3. 공감 취소 (DELETE)
		public int delete(LikeVO vo) throws Exception {
			int result = 0;
			con = DB.getConnection();
			
			String sql = "DELETE FROM likes WHERE post_no = ? AND liker_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPostNo());
			pstmt.setString(2, vo.getLikerId());
			
			result = pstmt.executeUpdate();
			
			DB.close(con, pstmt);
			return result;
		}
		
		// 4. 게시글의 총 공감 수 조회 (COUNT) - 화면 표시용
		public long count(int postNo) throws Exception {
			long count = 0;
			con = DB.getConnection();
			
			String sql = "SELECT COUNT(*) FROM likes WHERE post_no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			
			rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()) {
				count = rs.getLong(1);
			}
			
			DB.close(con, pstmt, rs);
			return count;
		}
}
