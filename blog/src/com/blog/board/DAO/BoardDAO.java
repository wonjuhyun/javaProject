package com.blog.board.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.blog.board.vo.BoardVO;


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
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, UID, UPW);

        String sql = "SELECT post_no, writer_id, title, content, hit, created_at, updated_at FROM Posts";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            BoardVO vo = new BoardVO();
            vo.setPostNo(rs.getInt("post_no"));
            vo.setWriterId(rs.getString("writer_id"));
            vo.setTitle(rs.getString("title"));
            vo.setContent(rs.getString("content"));
            vo.setHit(rs.getInt("hit"));
            vo.setCreatedAt(rs.getTimestamp("created_at"));
            vo.setUpdatedAt(rs.getTimestamp("updated_at"));
            list.add(vo);
        }

        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();

        return list;
    }

    // 조회수 증가
    public Integer inc(Integer no) throws Exception {
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, UID, UPW);

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
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, UID, UPW);

        String sql = "SELECT post_no, writer_id, title, content, hit, created_at, updated_at FROM Posts WHERE post_no = ?";
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
            vo.setCreatedAt(rs.getTimestamp("created_at"));
            vo.setUpdatedAt(rs.getTimestamp("updated_at"));
        }

        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();

        return vo;
    }//게시글 번호 자동 생성 메서드
    public int getNextPostNo() throws Exception {
        int nextNo = 0;
        Class.forName(DRIVER);
        con = DriverManager.getConnection(URL, UID, UPW);

        String sql = "SELECT NVL(MAX(post_no),0) + 1 AS nextNo FROM Posts";
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            nextNo = rs.getInt("nextNo");
        }

        if (rs != null) rs.close();
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();

        return nextNo;
    }
}
