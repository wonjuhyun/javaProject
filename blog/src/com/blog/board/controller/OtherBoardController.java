package com.blog.board.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.blog.board.service.BoardViewService;
import com.blog.board.service.OtherBoardListService;
import com.blog.board.vo.BoardVO;
import com.blog.board.vo.CurrentBoard;
import com.blog.main.service.Execute;
import com.blog.util.db.DB;
import com.blog.util.io.BoardPrint;
import com.blog.util.io.In;

public class OtherBoardController {

	@SuppressWarnings("unchecked")
	public void execute() throws Exception {
        while (true) {
            System.out.println("\n<< 아이디 검색 >>");
            System.out.println("=============================================");
            System.out.println(" 방문하고 싶은 아이디를 입력하세요 (0. 나가기)");
            System.out.println("---------------------------------------------");
            String targetId = In.getStr(" 아이디 입력");
            System.out.println("=============================================");

            if (targetId.equals("0")) return;

            if (!isUserExist(targetId)) {
                System.out.println("\n***** '" + targetId + "'은(는) 존재하지 않는 아이디입니다. *****");
                continue; // 다시 아이디 입력으로 돌아감
            }
            
            // 1. 해당 아이디의 글 목록 가져오기 (Service -> DAO)
            List<BoardVO> list = (List<BoardVO>) Execute.execute(new OtherBoardListService(), targetId);

            // 2. 리스트 출력
            System.out.println("\n<< '" + targetId + "'의 게시글 리스트 >>");
            BoardPrint.print(list);

            // 3. 게시글 선택 루프
            while (true) {
                System.out.println("==============================");
                System.out.println(" 1. 게시글 상세 보기  0. 이전");
                System.out.println("==============================");
                String menu = In.getStr("선택");

                if (menu.equals("1")) {
                	System.out.println();
                    int no = In.getInt("글 번호");

                    // ★ 여기에 try-catch가 반드시 있어야 Main까지 에러가 안 날아갑니다!
                    try {
                        // 1. 서비스 실행 (여기서 에러가 나면 바로 catch로 점프함)
                        BoardVO vo = (BoardVO) Execute.execute(new BoardViewService(), new int[]{no, 1});

                        // 2. 결과 처리
                        if (vo == null) {
                            System.out.println("***** 해당 번호의 글이 없습니다. *****");
                        } else if (!targetId.equals(vo.getWriterId())) { 
                            System.out.println("***** 해당 글은 '" + targetId + "'님의 글이 아닙니다. *****");
                        } else {
                            // 정상일 때만 실행
                            CurrentBoard.setBoardVO(vo);
                            BoardPrint.print(vo);
                            BoardController.view(vo); 
                            CurrentBoard.setBoardVO(null);
                        }
                    } catch (Exception e) {
                        System.out.println("\n***** 존재하지 않는 글이거나 삭제된 게시글입니다. *****\n");
                    }
                } else if (menu.equals("0")) {
                    break; // 내부 반복문 탈출 -> 다시 아이디 입력 받으러 감
                } else {
                    System.out.println("잘못된 선택입니다.");
                }
            }
        }
    }
	
	private boolean isUserExist(String id) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = DB.getConnection();
            // users 테이블에 해당 아이디가 있는지 딱 한 줄만 체크
            String sql = "SELECT 1 FROM users WHERE id = ?";
            
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            
            rs = pstmt.executeQuery();
            
            // 결과가 있으면(true) 아이디가 존재하는 것
            if (rs != null && rs.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(con, pstmt, rs);
        }
        
        return result;
    }
}
