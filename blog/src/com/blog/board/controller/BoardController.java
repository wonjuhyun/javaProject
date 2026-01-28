package com.blog.board.controller;

import java.util.List;

import com.blog.board.service.BoardDeleteService;
import com.blog.board.service.BoardListService;
import com.blog.board.service.BoardUpdateService;
import com.blog.board.service.BoardViewService;
import com.blog.board.vo.BoardVO;
import com.blog.board.vo.CurrentBoard;
import com.blog.comment.controller.CommentController;
import com.blog.like.controller.LikeController;
import com.blog.like.dao.LikeDAO;
import com.blog.like.vo.LikeVO;
import com.blog.main.service.Execute;
import com.blog.member.vo.Login;
import com.blog.subscribe.controller.SubscribesController;
import com.blog.util.io.BoardPrint;
import com.blog.util.io.In;

public class BoardController {
	@SuppressWarnings({ "unchecked" })
	public void execute() throws Exception {
        while (true) {
            System.out.println("========= 게시글 메뉴 =========");
            System.out.println("============================");
            System.out.println("1. 게시글 리스트 2. 게시글 글보기");
            System.out.println("3. 게시글 글수정 4. 게시글 글삭제");
            System.out.println("0. 이전화면");
            System.out.println("============================");
            System.out.println("번호를 입력해주세요. >>>");
            String menu = In.getStr(); 
            int no;
            System.out.println();
            switch (menu) {
                case "1":// 게시글 리스트
                    System.out.println("게시글 리스트");
                    List<BoardVO> list = (List<BoardVO>) Execute.execute(new BoardListService(), null);
                    BoardPrint.print(list);

                    break;
                    
                case "2"://게시글 글보기
                	 	System.out.println("게시글 글보기");
                     no = In.getInt("글번호");
                     BoardVO vo = (BoardVO) Execute.execute(new BoardViewService(), new int[]{no, 1});
                     CurrentBoard.setBoardVO(vo);// 현재 글 저장
                     BoardPrint.print(vo);
                     view(vo); // 상세 보기 실행
                     CurrentBoard.setBoardVO(null);// 초기화    
                     break;


                case "3"://게시글 글수정
                    System.out.println("게시글 글수정");
                    no = In.getInt("글번호");
                    vo = new BoardVO();
                    vo.setPostNo(no);
                    vo.setTitle(In.getStr("제목"));
                    vo.setContent(In.getStr("내용"));
                    vo.setCateNo(In.getInt("카테고리"));
                    
                    Integer result = (Integer) Execute.execute(new BoardUpdateService(), vo);

                    if (result == 0) {
                        System.out.println("수정할 글이 없습니다.");
                    } else if (result == 8) {
                        System.out.println("수정이 취소되었습니다.");
                    } else {
                        BoardPrint.print(vo);
                        System.out.println("수정이 완료되었습니다.");
                    }
                    break;
                    	
                case "4"://게시글 글삭제
                    System.out.println("게시글 글삭제");
                    no = In.getInt("글번호 입력");

                    vo = (BoardVO) Execute.execute(new BoardViewService(), new int[]{no, 0}); // 조회만
                    if (vo == null) {
                        System.out.println("삭제할 글이 존재하지 않습니다.");
                        break;
                    }

                    Integer cho = delete(vo); 

                    if (cho == 1) {
                        Integer result1 = (Integer) Execute.execute(new BoardDeleteService(), vo);
                        if (result1 == 0) {
                            System.out.println("삭제할 글이 존재하지 않습니다.");
                        } else {
                            System.out.println("삭제되었습니다");
                        }
                    } else if (cho == 2) {
                        System.out.println("삭제가 취소되었습니다");
                    } else if (cho == 0) {
                        System.out.println("나가기");
                    }
                    break;
                case "0":
                    return; 

                default:
                    System.out.println("잘못된 메뉴 선택입니다.");
                    break;
            }
        }
      
    }
	// 게시글 상세 보기
	public static Integer view(BoardVO vo) throws Exception {
	    while (true) {
	        System.out.println("========= 게시글 보기 =========");
	        System.out.println("제목:" + vo.getTitle());
	        System.out.println("내용:" + vo.getContent());

	        // 공감 기능을 위한 VO 생성
	        LikeVO likeVO = new LikeVO();
	        likeVO.setPostNo(vo.getPostNo());
	        likeVO.setLikerId(Login.getId()); // 로그인한 사용자 ID 
	        LikeDAO likeDao = new LikeDAO();

	        System.out.println("===================================================");
	        if (!likeDao.isLiked(likeVO)) {
	            System.out.println("1. 공감하기 2. 댓글쓰기 3. 구독한 블로그확인  0. 나가기");
	        } else {
	            System.out.println("1. 공감취소 2. 댓글쓰기 3. 구독한 블로그확인  0. 나가기");
	        }
	        System.out.println("===================================================");

	        System.out.println("번호를 입력해주세요. >>>");
	        String menu = In.getStr("선택");

	        switch (menu) {
	            case "1":
	              	// 공감하기/취소
	                LikeController likeController = new LikeController();
	                likeController.execute();

	                // 총 공감 수 표시
	                long count = likeDao.count(vo.getPostNo());
	                System.out.println("현재 공감 수: " + count);
	                break;

	            case "2":// 댓글쓰기
	                CommentController CommentController =new CommentController();
	                CommentController.execute();
	                break;

	            case "3":// 구독 확인
	                SubscribesController SubscribesController = new SubscribesController();
	                SubscribesController.execute(Login.getId());
	                break;

	            case "0":
	                System.out.println("나가기");
	                return 0;

	            default:
	                System.out.println("잘못된 선택입니다.");
	        }
	    }
	}    // 게시글 수정
      public Integer update (BoardVO vo) {
          while(true) {
        	System.out.println("======================= 게시글 수정 =======================");
        	System.out.println("제목:"+vo.getTitle());
        	System.out.println("내용:"+vo.getContent());
            System.out.println("==========================================================");
            System.out.println(" 1. 제목  2. 내용  3.카테고리	 8. 수정 완료  9. 수정 취소 0. 나가기");
            System.out.println("==========================================================");
            System.out.println("번호를 입력해주세요. >>>");
            String menu = In.getStr("선택");
            switch (menu) {
        	case "1":
        		vo.setTitle(In.getStr("제목"));
        		break;
        	case "2":
        		vo.setContent(In.getStr("내용")); 
        		break;
        	case "3":
        		vo.setCateNo(In.getInt("카테고리"));
        		break;
        	case "8":
        		return 8;
        	case "9":
        		return 9;
        	case "0":
        		
        		return 0;
        	default:
               System.out.println("잘못된 선택입니다.");
               break;
            
            	}
            BoardPrint.print(vo);
        }
    }     // 게시글 삭제
      public static Integer delete(BoardVO vo) {
    	    while (true) {
    	        System.out.println("======================= 게시글 삭제 =======================");
    	        System.out.println("제목: " + vo.getTitle());
    	        System.out.println("내용: " + vo.getContent());
    	        System.out.println("==========================================================");
    	        System.out.println("정말로 이 글을 삭제하시겠습니까?");
    	        System.out.println("1.Yes  2.No  0: 나가기");
    	        System.out.println("==========================================================");
    	        System.out.println("번호를 입력해주세요. >>>");

    	        String menu = In.getStr("선택");

    	        switch (menu) {
    	            case "1":
    	                return 1; 
    	            case "2":
    	                return 2; 
    	            case "0":
    	                return 0; 
    	            default:
    	                System.out.println("잘못된 선택입니다.");
    	                break;
    	        }
    	    }
    	}
}
