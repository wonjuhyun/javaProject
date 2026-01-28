package com.blog.comment.controller;

import java.util.List;

import com.blog.board.vo.CurrentBoard;
import com.blog.comment.service.CommentDeleteService;
import com.blog.comment.service.CommentListService;
import com.blog.comment.service.CommentUpdateService;
import com.blog.comment.service.CommentViewService;
import com.blog.comment.service.CommentWriteService;
import com.blog.comment.vo.Comment;
import com.blog.main.service.Execute;
import com.blog.member.vo.Login;
import com.blog.util.io.CommentPrint;
import com.blog.util.io.In;

public class CommentController {

	public void execute() {
		while (true) {
			try {
				System.out.println();
				System.out.println("<< 댓글 메뉴 >>");
				System.out.println("=============================================");
				System.out.println(" 1. 댓글 리스트  2. 댓글 작성  3. 나의 댓글");
				System.out.println(" 4. 댓글 수정  5. 댓글 삭제  0. 이전");
				System.out.println("=============================================");
				
				String menu = In.getStr("메뉴 입력");
				System.out.println("\n");  // 줄바꿈
				
				Comment vo;
				Integer result;
				
				switch (menu) {
				
				// 댓글 리스트
				case "1" :
					vo = new Comment();
					vo.setPostNo(CurrentBoard.getBoardVO().getPostNo());
					
					@SuppressWarnings("unchecked")
					List<Comment> list = (List<Comment>) Execute.execute(new CommentListService(), vo); 
					CommentPrint.print(list);
					break;
					
				// 댓글 작성
				case "2" :
					System.out.println("<< 댓글 작성 >>");
					System.out.println("=================================================================");
					String content = In.getStr("댓글 입력");
					System.out.println("--------------------------------------------");
					System.out.println(" 1. 작성완료  0. 작성취소");
					String write = In.getStr("메뉴 입력");
					System.out.println("=================================================================");
					
					if(write.equals("1")) {
						vo = new Comment();
						vo.setPostNo(CurrentBoard.getBoardVO().getPostNo()); // 메서드명 확인 (getPostNo)
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
					vo = new Comment();
					vo.setPostNo(CurrentBoard.getBoardVO().getPostNo());
					vo.setWriterId(Login.getId());
					// DB에서 내 댓글 가져오기
					Comment myComment = (Comment) Execute.execute(new CommentViewService(), vo);
					CommentPrint.print(myComment);
					break;
					
				// 댓글 수정
				case "4" :
					
					vo = new Comment();
					vo.setPostNo(CurrentBoard.getBoardVO().getPostNo());
					vo.setWriterId(Login.getId());
					
					// 1. 수정할 내 댓글이 있는지 DB에서 가져오기
					Comment checkVO = (Comment) Execute.execute(new CommentViewService(), vo);

					System.out.println("<< 댓글 수정 >>"); 
					System.out.println("=============================================================");
					if (checkVO != null) {
						// 2. 현재 내용 보여주기
						System.out.println(" " + checkVO.getWriterNick() + " : " + checkVO.getContent());
						System.out.println("-------------------------------------------------------------");
						
						// 3. 여기서 직접 입력을 받는다.
						String newContent = In.getStr(" 수정할 내용");
						System.out.println("=============================================================");
						
						System.out.println(" 입력하신 내용으로 수정하시겠습니까?");
						System.out.println("-----------------------------------");
						System.out.println(" 1. 예  0. 아니오");
						String updateMenu = In.getStr("선택");
						System.out.println("===================================");
						
						// 3. 선택에 따른 처리
						if (updateMenu.equals("1")) {
							checkVO.setContent(newContent);
							result = (Integer) Execute.execute(new CommentUpdateService(), checkVO);
							if (result >= 1) System.out.println(" ***** 수정이 완료되었습니다. *****");
							else System.out.println(" ***** 수정 실패 *****");
						} else {  // 0번(취소) 혹은 다른 키를 눌렀을 때
							System.out.println("\n ***** 수정이 취소되었습니다. *****");
						}
					} else {
						System.out.println(" 이 게시글에 작성하신 댓글이 없습니다. ");
						System.out.println("=============================================================");
					}
					break;
					
				// 댓글 삭제
				case "5" :
					
					System.out.println("<< 댓글 삭제 >>");
					System.out.println("============================================");
					
					// 1. 삭제할 대상 정보 세팅 (게시글 번호 + 내 아이디)
					vo = new Comment();
					vo.setPostNo(CurrentBoard.getBoardVO().getPostNo());
					vo.setWriterId(Login.getId());
					
					// 2. 경고 문구 출력 및 메뉴 선택
					System.out.println(" 삭제하시면 복구할 수 없습니다. 삭제하시겠습니까?");
					System.out.println("--------------------------------------------");
					System.out.println(" 1. 삭제  0. 취소");
					String deleteMenu = In.getStr("선택");
					System.out.println("============================================");
					
					// 3. 선택에 따른 처리
					if (deleteMenu.equals("1")) {
						// 1번(삭제)을 눌렀을 때만 DB 삭제 서비스 실행
						result = (Integer) Execute.execute(new CommentDeleteService(), vo);
						
						if (result >= 1) {
							System.out.println("\n ***** 삭제가 완료되었습니다. *****");
						} else {
							// 조건(게시글번호+아이디)에 맞는 데이터가 없어서 삭제 못한 경우
							System.out.println("\n ***** 삭제할 댓글이 없습니다. *****");
						}
					} else {
						// 0번(취소) 혹은 다른 키를 눌렀을 때
						System.out.println("\n ***** 삭제가 취소되었습니다. *****");
					}
					
					break;
				
				// 이전
				case "0" :
					return;
				default :
					System.out.println("***** 잘못된 메뉴입니다. 다시 입력하세요. *****\n");
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
}
