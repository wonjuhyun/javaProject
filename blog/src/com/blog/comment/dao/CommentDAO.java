package com.blog.comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.blog.comment.vo.Comment;
import com.blog.util.db.DB;

public class CommentDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. 댓글 리스트 - 내용, 작성자, 작성일, 수정일
	public List<Comment> list(int postNo) throws Exception {
		List<Comment> list = new ArrayList<>();
		con = DB.getConnection();
		
		// 1. WHERE절에 c.post_no = ? 추가 (해당 글의 댓글만)
		// 2. AND 조건으로 테이블 조인 (comments와 users)
		// 3. ORDER BY c.comment_no DESC (최신 댓글이 위로)
		String sql = "SELECT u.nickname, c.content, c.writer_id, "
				   + " to_char(c.created_at, 'yyyy-mm-dd hh:mm:ss') created_at, "
				   + " to_char(c.updated_at, 'yyyy-mm-dd hh:mm:ss') updated_at "
				   + " FROM comments c, users u "
				   + " WHERE c.post_no = ? "      // 이 게시글의 댓글만
				   + " AND c.writer_id = u.id " // 작성자 닉네임 가져오기 위한 조인
				   + " ORDER BY created_at DESC";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, postNo); // 게시글 번호 세팅
		
		rs = pstmt.executeQuery();
		
		if (rs != null) {
			while(rs.next()) {
				Comment vo = new Comment();
				vo.setWriterNick(rs.getString("nickname"));
				vo.setContent(rs.getString("content"));
				vo.setWriterId(rs.getString("writer_id"));
				vo.setCreatedAt(rs.getString("created_at"));
				vo.setUpdatedAt(rs.getString("updated_at"));
				list.add(vo);
			}
		}
		
		DB.close(con, pstmt, rs);
		return list;
	}  // list() 끝
	
	
	// 2. 댓글 작성
	public Integer write(Comment vo) throws Exception {
		Integer result = 0;

		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "insert into comments(post_no, content, writer_id, created_at, updated_at) "
				+ " values(?, ?, ?, sysdate, null)";
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, vo.getPostNo());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getWriterId());
		// 5. 실행 & 6. 데이터 저장
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}
	
	
	public boolean checkDoubleComment(int postNo, String writerId) throws Exception {
		boolean isExist = false;
		
		try {
			con = DB.getConnection();
			// 해당 게시글(post_no)에 내 아이디(writer_id)로 쓴 글의 개수를 센다
			String sql = "SELECT COUNT(*) FROM comments WHERE post_no = ? AND writer_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postNo);
			pstmt.setString(2, writerId);
			
			rs = pstmt.executeQuery();
			
			if (rs != null && rs.next()) {
				// 개수가 0보다 크면 이미 쓴 것
				if (rs.getInt(1) > 0) {
					isExist = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return isExist;
	}
	
	
	// 3. 내 댓글 보기 - 내용, 작성일, 수정일
	public Comment view(Comment vo) throws Exception {
		Comment result = null;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL
		String sql = "select u.nickname, c.content, to_char(c.created_at, 'yyyy-mm-dd hh:mm:ss') created_at, "
				+ " to_char(c.updated_at, 'yyyy-mm-dd hh:mm:ss') updated_at from comments c, users u "
				+ " where c.writer_id = ? and c.post_no = ? and u.id = c.writer_id";
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getWriterId());
		pstmt.setInt(2, vo.getPostNo());
		// 5. 실행
		rs = pstmt.executeQuery();
		// 6. 데이터 저장
		if (rs != null) {
			while(rs.next()) {  // 데이터가 있는 만큼 반복 실행
				// 저장할 객체 생성
				result = new Comment();
				result.setPostNo(vo.getPostNo());
				result.setWriterId(vo.getWriterId());
				result.setWriterNick(rs.getString("nickname"));
				result.setContent(rs.getString("content"));
				result.setCreatedAt(rs.getString("created_at"));
				result.setUpdatedAt(rs.getString("updated_at"));
			}  // while() 끝
		}  // if 끝
		// 7. 닫기
		DB.close(con, pstmt, rs);
		return result;
	}  // view() 끝
	
	
	// 4. 댓글 수정
	public Integer update(Comment vo) throws Exception {
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "update comments set content = ?, updated_at = sysdate "
				+ " where writer_id = ? and post_no = ?";
		// 4. 실행객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getContent());
		pstmt.setString(2, vo.getWriterId());
		pstmt.setInt(3, vo.getPostNo());
		// 5. 실행 & 6. 결과 저장
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}  // update() 끝
	
	
	// 5. 댓글 삭제
	public Integer delete(Comment vo) throws Exception {
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "delete from comments where writer_id = ? and post_no = ?";
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vo.getWriterId());
		pstmt.setInt(2, vo.getPostNo());
		// 5. 실행 & 6. 결과 저장
		result = pstmt.executeUpdate();
		// 7. 닫기
		DB.close(con, pstmt);
		
		return result;
	}
	
}
