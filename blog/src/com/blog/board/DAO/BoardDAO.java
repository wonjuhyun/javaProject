package com.blog.board.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.blog.board.vo.BoardVO;
import com.blog.util.db.DB;


public class BoardDAO {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 

    final String DRIVER = "oracle.jdbc.OracleDriver";
    final String URL = "jdbc:oracle:thin:@10.15.21.232:1521:xe";
    final String UID = "team2";
    final String UPW = "java";

    // 게시글 목록 조회
    public List<BoardVO> list() throws Exception {
        List<BoardVO> list = new ArrayList<>();
        con=DB.getConnection();
        String sql = "SELECT post_no, writer_id, title, content, hit, cate_no, created_at, updated_at FROM Posts";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            BoardVO vo = new BoardVO();
            vo.setPostNo(rs.getInt("post_no"));
            vo.setWriterId(rs.getString("writer_id"));
            vo.setTitle(rs.getString("title"));
            vo.setContent(rs.getString("content"));
            vo.setHit(rs.getInt("hit"));
            vo.setCateNo(rs.getInt("cate_no"));
            vo.setCreatedAt(rs.getString("created_at"));
            vo.setUpdatedAt(rs.getString("updated_at"));
            list.add(vo);
        }

        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();

        return list;
    }

    // 조회수 증가
    public Integer inc(Integer no) throws Exception {
    		con=DB.getConnection();
        String sql = "UPDATE Posts SET hit = hit + 1 WHERE post_no = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, no);
        int result = pstmt.executeUpdate();
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();
        return result;
    }

    // 단일 게시글 조회
    public BoardVO view(int no) throws Exception {
        BoardVO vo = null;
        con=DB.getConnection();
        String sql = "SELECT post_no, writer_id, title, content, hit, cate_no ,created_at, updated_at FROM Posts WHERE post_no = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, no);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            vo = new BoardVO();
            vo.setPostNo(rs.getInt("post_no"));
            vo.setWriterId(rs.getString("writer_id"));
            vo.setTitle(rs.getString("title"));
            vo.setContent(rs.getString("content"));
            vo.setHit(rs.getInt("hit"));
            vo.setCateNo(rs.getInt("cate_no"));
            vo.setCreatedAt(rs.getString("created_at"));
            vo.setUpdatedAt(rs.getString("updated_at"));
        }

        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();

        return vo;
    }
 
    //수정
    public Integer update(BoardVO vo) throws Exception {
        Integer result = 0;
        con=DB.getConnection();
        String sql = "update Posts set title = ?, content = ?,cate_no = ? WHERE post_no = ?";
        pstmt = con.prepareStatement(sql);
	    	pstmt.setString(1,vo.getTitle());
	    	pstmt.setString(2,vo.getContent());
	    pstmt.setInt(3,vo.getCateNo());
	    pstmt.setInt(4,vo.getPostNo());
        result = pstmt.executeUpdate();
        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();
        return result;
    }
    //삭제
    public Integer delete(BoardVO vo) throws Exception {
        Integer result = 0;
        con=DB.getConnection();
        String sql = "DELETE FROM Posts WHERE post_no = ?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, vo.getPostNo()); 

        result = pstmt.executeUpdate();

        if (pstmt != null) pstmt.close();
        if (con != null) con.close();

        return result;


    }
}
