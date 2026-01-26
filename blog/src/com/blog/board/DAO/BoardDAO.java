package com.blog.board.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.blog.board.vo.BoardVO;


public class BoardDAO {
	Connection con =null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	final String DRIVER = "oracle.jdbc.OracleDriver";
	final String URL = "jdbc:oracle:thin:@10.15.21.232:1521:xe";
	final String UID= "team2";
	final String UPW= "java";
	
	public List<BoardVO>list() throws Exception{
		List<BoardVO> list = new ArrayList<>();
		
		Class.forName(DRIVER);
		con = DriverManager.getConnection(URL, UID, UPW);
		String sql = "SELECT post_no, writer_id, title, content, hit, created_at, updated_at FROM Posts";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs != null) {
			while (rs.next()) {
		        BoardVO vo = new BoardVO();
		        vo.setPostNo(rs.getInt("post_no"));          // 컬럼명 맞추기
		        vo.setWriterId(rs.getString("writer_id"));
		        vo.setTitle(rs.getString("title"));
		        vo.setContent(rs.getString("content"));
		        vo.setHit(rs.getInt("hit"));
		        vo.setCreatedAt(rs.getTimestamp("created_at"));
		        vo.setUpdatedAt(rs.getTimestamp("updated_at"));
		        list.add(vo);
		    }
		}
		if (rs != null) rs.close();
        if (pstmt != null) pstmt.close(); 
        if (con != null) con.close();

		return list;
	}
	
}
