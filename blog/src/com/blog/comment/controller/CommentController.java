package com.blog.comment.controller;

import java.util.List;

import com.blog.comment.service.CommentDeleteService;
import com.blog.comment.service.CommentListService;
import com.blog.comment.service.CommentUpdateService;
import com.blog.comment.service.CommentViewService;
import com.blog.comment.service.CommentWriteService;
import com.blog.comment.vo.Comment;
import com.blog.main.controller.Main;
import com.blog.main.service.Execute;
import com.blog.member.vo.Login;
import com.blog.util.io.CommentPrint;
import com.blog.util.io.In;

import a.Post;


public class CommentController {

	public void execute() {
		// 종료를 선택하기 전까지 무한반복
		// while(~) - ~ 조건이 true인 동안 반복 처리한다
		//   - 빠져나가는 방법 : break;
		while (true) {
			try {
				// 일반 게시판 처리
				// 1. 메뉴 출력 - 일반 게시판이 가지고 있는 기능
				System.out.println();
				System.out.println("<< 댓글 >>");
				System.out.println("=============================================");
				System.out.println(" 1. 댓글 리스트  2. 댓글 작성  3. 나의 댓글");
				System.out.println(" 4. 댓글 수정  5. 댓글 삭제  0. 이전");
				System.out.println("=============================================");
				
				// 2. 메뉴 입력
				String menu = In.getStr("메뉴 입력");
				System.out.println();  // 줄바꿈
				
				// 3. 메뉴 처리 - switch
				// 사용 변수 선언
				Comment vo;
				Integer result;
				
				switch (menu) {
				
				// 댓글 리스트
				case "1" :
					@SuppressWarnings("unchecked")
					List<Comment> list = (List<Comment>) Execute.execute(new CommentListService(), null); 
					CommentPrint.print(list);
					break;
					
				// 댓글 작성
				case "2" :
					System.out.println("============================================");
					String content = In.getStr("댓글 입력");
					System.out.println("--------------------------------------------");
					System.out.println(" 1. 작성완료  0. 작성취소");
					String sel = In.getStr("메뉴 입력");
					System.out.println("============================================");
					
					if(sel.equals("1")) {
						vo = new Comment();
						vo.setPostNo(Post.getPostNo()); // 메서드명 확인 (getPostNo)
						vo.setWriterId(Login.getId());
						vo.setContent(content);
						
						result = (Integer) Execute.execute(new CommentWriteService(), vo);
						if(result == 1) System.out.println("\n ***** 댓글이 등록되었습니다. *****");
					} else {
						System.out.println("\n ***** 작성이 취소되었습니다. *****");
					}
					break;
					
				// 내 댓글 보기
				case "3" :
					// [나의 댓글]
					vo = new Comment();
					vo.setPostNo(Post.getPostNo());
					vo.setWriterId(Login.getId());
					
					// DB에서 내 댓글 가져오기
					Comment myComment = (Comment) Execute.execute(new CommentViewService(), vo);
					
					if (myComment != null) {
						CommentPrint.print(myComment);
					} else {
						System.out.println("\n ***** 작성하신 댓글이 없습니다. *****");
					}
					break;
					
				// 댓글 수정
				case "4" :
					
					// [댓글 수정]
					vo = new Comment();
					vo.setPostNo(Post.getPostNo());
					vo.setWriterId(Login.getId());
					
					// 1. 수정할 내 댓글이 있는지 먼저 확인
					Comment checkVO = (Comment) Execute.execute(new CommentViewService(), vo);
					
					if (checkVO != null) {
						// 2. 현재 내용 보여주기 (제목을 '댓글 수정'으로 보이게 하려면 print 메서드 수정 필요하지만 여기선 재활용)
						System.out.println("<< 댓글 수정 >>"); 
						System.out.println("============================================");
						System.out.println(" " + checkVO.getWriterNick() + " : " + checkVO.getContent());
						System.out.println("--------------------------------------------");
						
						// 3. 내용 수정 입력 (메서드 호출)
						update(checkVO);
						
						// 4. DB 업데이트 실행
						result = (Integer) Execute.execute(new CommentUpdateService(), checkVO);
						
						if (result >= 1) System.out.println("\n ***** 수정이 완료되었습니다. *****");
						else System.out.println("\n ***** 수정 실패 *****");
						
					} else {
						System.out.println("\n ***** 이 게시글에 작성하신 댓글이 없습니다. *****");
					}
					break;
					
				// 댓글 삭제
				case "5" :
					
					System.out.println("<< 댓글 삭제 >>");
					vo = new Comment();
					vo.setPostNo(Post.getPostNo());
					vo.setWriterId(Login.getId());
					
					result = (Integer) Execute.execute(new CommentDeleteService(), vo);
					if (result >= 1) System.out.println("\n ***** 삭제가 완료되었습니다. *****");
					else System.out.println("\n ***** 삭제할 댓글이 없습니다. *****");
					break;
				
				// 이전
				case "0" :
					return;
				default :
					Main.invalidMenuPrint();
				}  // switch 끝
				System.out.println();  // 화면을 구분하는 빈 줄 출력
			}  // try 정상처리 끝
			catch (Exception e) {  // 예외 처리
				e.printStackTrace();  // 개발자를 위한 예외 상세 출력
				// 사용자를 위한 예외 코드 작성
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println(" 일반 게시판 처리 중 오류가 발생되었습니다.");
				System.out.println(" 오류 : " + e.getMessage());
				System.out.println(" 다시 한 번 실행해 주시고, 오류가 계속되면 문의를 남겨주세요.");
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
			}  // catch 끝
		}  // while(true) 끝
	}  // execute() 끝
	
	// 데이터를 수정하는 메서드
	// 전달된 vo는 주소가 전달된다. 안에서 수정하면 밖에서도 수정이 같이 된다
	public Integer update (Comment vo) {
		while(true) {
			// 수정 메뉴 입력
			vo.setContent(In.getStr("내용"));
			// 수정된 내용 출력
			CommentPrint.print(vo);
		}  // while(true) 끝
	}  // update() 끝
}
