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
	public List<Comment> list() throws Exception {
		
//		System.out.println("BoardDAO.list() ---------------------------");
		
		// 리턴 타입과 동일한 변수 선언 - 결과 저장
		List<Comment> list = new ArrayList<>();
		
		// 1. 드라이버 확인 - static으로 선언된 내용이 자동으로 올라간다
		// & 2. 연결 객체 - 정보를 넣고 서버에 다녀온다
		// getConnection() - static
		con = DB.getConnection();
		
		// 3. 실행할 쿼리 작성
		String sql = "select u.nickname, c.content, c.writer_id, c.createdAt, c.updatedAt from comments c, posts p, "
				+ "users u where c.post_no = p.post_no and u.id = c.writer_id order by no desc";
		
		// 4. 준비된 실행 객체
		pstmt = con.prepareStatement(sql);

		// 5. 실행 : select -> rs, insert / update / delete -> Integer
		rs = pstmt.executeQuery();
		
		// 6. DB에서 가져온 데이터 채우기
		if (rs != null) {
			while(rs.next()) {  // 데이터가 있는 만큼 반복 실행
				// 저장할 객체 생성
				Comment vo = new Comment();
				// 데이터 저장
				vo.setWriterNick(rs.getString("nickname"));
				vo.setCommentNo(rs.getInt("content"));
				vo.setWriterId(rs.getString("writer_id"));
				vo.setCreatedAt(rs.getString("created_at"));
				vo.setUpdatedAt(rs.getString("updated_at"));
				// list에 담는다
				list.add(vo);
			}  // while문 끝
		}  // if문 끝
		
		// 7. DB 닫기
		DB.close(con, pstmt, rs);
		
		return list;
	}  // list() 끝
	
	
	// 2. 댓글 작성
	public Integer write(Comment vo) throws Exception {
		Integer result = 0;

		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "insert into comments(comment_no, post_no, content, writer_id, created_at) "
				+ " values(comment_seq.nextval, ?, ?, sysDate)";
		// 4. 실행 객체 & 데이터 세팅
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, vo.getPostNo());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getWriterId());
		// 5. 실행 & 6. 데이터 저장
		// select - executeQuery() : rs, insert, update, delete - executeUpdate() : Integer
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
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL
		String sql = "select u.nickname, c.content, to_char(c.created_at, 'yyyy-mm-dd') c.created_at, to_char(c.updated_at, "
				+ " 'yyyy-mm-dd') c.updated_at from comments c users u where writerId = ? and post_no = ? and u.id = c.writer_id";
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
				vo = new Comment();
				vo.setWriterNick(rs.getString("nickname"));
				vo.setContent(rs.getString("content"));
				vo.setCreatedAt(rs.getString("created_at"));
				vo.setUpdatedAt(rs.getString("updated_at"));
			}  // while() 끝
		}  // if 끝
		// 7. 닫기
		DB.close(con, pstmt, rs);
		return vo;
	}  // view() 끝
	
	
	// 4. 댓글 수정
	public Integer update(Comment vo) throws Exception {
		Integer result = 0;
		
		// 1. 드라이버 확인 & 2. 연결 객체
		con = DB.getConnection();
		// 3. SQL 작성
		String sql = "update comments set content = ?, updated_at = sysDate "
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
